# Setup

## 1. Drop these files into your repo

Copy `sync.py`, `requirements.txt`, `SETUP.md`, and the `.github/workflows/sync.yml`
file into the root of the GitHub repo you want your solutions pushed to. Commit and
push them once (manually, this one time).

## 2. Get your LeetCode session cookie

These two values authenticate the script as you. They are **not your password** —
they're temporary session cookies, but treat them as sensitive (anyone with them
can act as you on leetcode.com until they expire, ~1-2 weeks).

1. Log in to https://leetcode.com in your browser.
2. Open DevTools (F12) → **Application** tab (Chrome) or **Storage** tab (Firefox)
   → **Cookies** → `https://leetcode.com`.
3. Copy the **Value** of the cookie named `LEETCODE_SESSION`.
4. Copy the **Value** of the cookie named `csrftoken`.

## 3. Add them as GitHub repo secrets

In your repo: **Settings → Secrets and variables → Actions → New repository secret**

- `LEETCODE_SESSION` → paste the session cookie value
- `LEETCODE_CSRF_TOKEN` → paste the csrftoken value

The workflow already has `permissions: contents: write`, so no extra PAT is
needed for it to push commits — it uses the default `GITHUB_TOKEN`.

## 4. Test it locally first (recommended)

```bash
pip install -r requirements.txt
export LEETCODE_SESSION="paste value here"
export LEETCODE_CSRF_TOKEN="paste value here"
python sync.py --dry-run
```

If that prints your accepted submissions without errors, you're good. Run it
for real (drops the `--dry-run`) to backfill your existing accepted solutions
and commit them locally, or just let the GitHub Action handle it.

## 5. Let it run

The workflow runs every 30 minutes automatically (`.github/workflows/sync.yml`),
and you can also trigger it manually from the **Actions** tab → **Sync LeetCode
solutions** → **Run workflow**.

## Notes / things to know

- **First run backfills everything.** Since there's no state file yet, the first
  run scans your full submission history (capped at `--max-pages`, default 200
  pages = ~4000 submissions). If you have a huge history, you may need to bump
  `MAX_PAGES` or run it a couple of times.
- **One file per problem.** If you've solved a problem more than once, only the
  most recent accepted submission is kept (it overwrites the file).
- **Cookies expire.** LeetCode session cookies typically last 1-2 weeks. When
  the script starts failing with auth errors, just repeat step 2-3 with fresh
  values.
- **This uses LeetCode's internal, undocumented API** (the same one their own
  website uses) since there's no official public API for submission history.
  It could break if LeetCode changes their site internals — if that happens,
  the fix is usually just updating the GraphQL query in `sync.py`.
