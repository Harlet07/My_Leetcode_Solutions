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
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode t=head,tn=head.next;
        while(tn!=null){
            if(t.val==tn.val){
                t.next=tn.next;
                tn=t.next;
            }
            else{
                t=tn;
                tn=tn.next;
            }
        }

        return head;

    }
}
