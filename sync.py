#!/usr/bin/env python3
"""
leetcode-sync

Pulls your Accepted LeetCode submissions and writes each one to disk as
    solutions/<number>-<slug>/solution.<ext>
    solutions/<number>-<slug>/README.md

Designed to be run repeatedly (e.g. via a scheduled GitHub Action). It keeps
a small state file (.leetcode_sync_state.json) so each run only has to look
at submissions newer than the last run, instead of re-scanning your whole
history every time.

Auth: LeetCode has no official public API for this, so we use the same
internal endpoints the leetcode.com website itself calls. You authenticate
by copying two cookie values from a logged-in browser session:
    LEETCODE_SESSION
    csrftoken

See SETUP.md for how to get these. Both are read from environment
variables so nothing sensitive is hard-coded here.
"""

import os
import re
import sys
import json
import time
import argparse
import requests

LEETCODE_BASE = "https://leetcode.com"
GRAPHQL_URL = f"{LEETCODE_BASE}/graphql/"
SUBMISSIONS_URL = f"{LEETCODE_BASE}/api/submissions/"
STATE_FILE = ".leetcode_sync_state.json"
SOLUTIONS_DIR = "solutions"

# LeetCode's "lang" value -> file extension
LANG_EXT = {
    "python": "py",
    "python3": "py",
    "c": "c",
    "cpp": "cpp",
    "csharp": "cs",
    "java": "java",
    "javascript": "js",
    "typescript": "ts",
    "php": "php",
    "swift": "swift",
    "kotlin": "kt",
    "dart": "dart",
    "golang": "go",
    "ruby": "rb",
    "scala": "scala",
    "rust": "rs",
    "racket": "rkt",
    "erlang": "erl",
    "elixir": "ex",
    "mysql": "sql",
    "mssql": "sql",
    "oraclesql": "sql",
    "pythondata": "py",
    "bash": "sh",
}

SUBMISSION_DETAILS_QUERY = """
query submissionDetails($submissionId: Int!) {
  submissionDetails(submissionId: $submissionId) {
    code
    lang { name }
    question {
      questionId
      questionFrontendId
      title
      titleSlug
      difficulty
    }
  }
}
"""


def log(msg):
    print(msg, flush=True)


def make_session():
    session_cookie = os.environ.get("LEETCODE_SESSION")
    csrf_token = os.environ.get("LEETCODE_CSRF_TOKEN")
    if not session_cookie or not csrf_token:
        log("ERROR: LEETCODE_SESSION and LEETCODE_CSRF_TOKEN env vars are required.")
        sys.exit(1)

    s = requests.Session()
    s.cookies.set("LEETCODE_SESSION", session_cookie, domain="leetcode.com")
    s.cookies.set("csrftoken", csrf_token, domain="leetcode.com")
    s.headers.update({
        "Content-Type": "application/json",
        "X-CSRFToken": csrf_token,
        "Referer": "https://leetcode.com/",
        "Origin": "https://leetcode.com",
        "User-Agent": "Mozilla/5.0 (leetcode-sync script)",
    })
    return s


def load_state():
    if os.path.exists(STATE_FILE):
        with open(STATE_FILE) as f:
            return json.load(f)
    return {"last_synced_id": 0}


def save_state(state):
    with open(STATE_FILE, "w") as f:
        json.dump(state, f, indent=2)


def fetch_submission_pages(session, last_synced_id, max_pages):
    """Yield accepted submissions newer than last_synced_id, newest first."""
    offset = 0
    limit = 20
    pages = 0
    found = []

    while pages < max_pages:
        resp = session.get(
            SUBMISSIONS_URL,
            params={"offset": offset, "limit": limit},
        )
        if resp.status_code != 200:
            log(f"WARNING: submissions list request failed ({resp.status_code}); stopping pagination.")
            break

        data = resp.json()
        dump = data.get("submissions_dump", [])
        if not dump:
            break

        hit_known = False
        for item in dump:
            if item["id"] <= last_synced_id:
                hit_known = True
                break
            if item.get("status_display") == "Accepted":
                found.append(item)

        pages += 1
        if hit_known or not data.get("has_next"):
            break

        offset += limit
        time.sleep(0.5)  # be polite to LeetCode's servers

    return found


def fetch_submission_code(session, submission_id):
    resp = session.post(
        GRAPHQL_URL,
        json={
            "operationName": "submissionDetails",
            "query": SUBMISSION_DETAILS_QUERY,
            "variables": {"submissionId": submission_id},
        },
    )
    resp.raise_for_status()
    payload = resp.json()
    details = payload.get("data", {}).get("submissionDetails")
    if not details:
        log(f"WARNING: no submissionDetails returned for {submission_id}: {payload}")
        return None
    return details


def slug_from_url(url):
    m = re.search(r"/problems/([a-z0-9\-]+)/", url or "")
    return m.group(1) if m else None


def write_solution(details, submission):
    question = details["question"]
    frontend_id = question.get("questionFrontendId") or question.get("questionId")
    slug = question["titleSlug"]
    title = question["title"]
    difficulty = question.get("difficulty", "Unknown")
    lang_name = details["lang"]["name"].lower()
    ext = LANG_EXT.get(lang_name, "txt")
    code = details["code"]

    folder = os.path.join(SOLUTIONS_DIR, f"{frontend_id}-{slug}")
    os.makedirs(folder, exist_ok=True)

    solution_path = os.path.join(folder, f"solution.{ext}")
    with open(solution_path, "w") as f:
        f.write(code.rstrip() + "\n")

    readme_path = os.path.join(folder, "README.md")
    with open(readme_path, "w") as f:
        f.write(f"# {frontend_id}. {title}\n\n")
        f.write(f"- **Difficulty:** {difficulty}\n")
        f.write(f"- **Link:** https://leetcode.com/problems/{slug}/\n")
        f.write(f"- **Language:** {lang_name}\n")
        f.write(f"- **Last synced submission:** {submission['id']}\n")

    return frontend_id, title


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--max-pages", type=int, default=int(os.environ.get("MAX_PAGES", 200)),
                         help="Safety cap on how many pages of submission history to scan in one run.")
    parser.add_argument("--dry-run", action="store_true",
                         help="Fetch and print what would be synced without writing files or state.")
    args = parser.parse_args()

    session = make_session()
    state = load_state()
    last_synced_id = state.get("last_synced_id", 0)

    log(f"Scanning submissions newer than id {last_synced_id} (max {args.max_pages} pages)...")
    accepted = fetch_submission_pages(session, last_synced_id, args.max_pages)

    if not accepted:
        log("No new accepted submissions found.")
        return

    log(f"Found {len(accepted)} new accepted submission(s). Processing oldest first...")
    accepted.sort(key=lambda s: s["id"])  # oldest first, so newest wins on disk

    max_id_seen = last_synced_id
    synced_count = 0
    for submission in accepted:
        max_id_seen = max(max_id_seen, submission["id"])
        details = fetch_submission_code(session, submission["id"])
        if not details:
            continue

        if args.dry_run:
            log(f"[dry-run] Would write submission {submission['id']} ({submission.get('title')})")
            continue

        frontend_id, title = write_solution(details, submission)
        log(f"Wrote {frontend_id}. {title}")
        synced_count += 1
        time.sleep(0.5)

    if not args.dry_run:
        state["last_synced_id"] = max_id_seen
        save_state(state)
        log(f"Done. Synced {synced_count} solution(s). State updated to id {max_id_seen}.")
    else:
        log("Dry run complete. No files or state were written.")


if __name__ == "__main__":
    main()
