/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode t=head;
        if(head==null || head.next==null) return head;
        while(t!=null && t.next!=null){
            int tp=t.val;
            t.val=t.next.val;
            t.next.val=tp;
            t=t.next.next;
        }
        return head;
    }
}
