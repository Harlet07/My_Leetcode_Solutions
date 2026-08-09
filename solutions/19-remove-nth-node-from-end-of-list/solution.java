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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;
        if(head.next==null) return null;
        int s=0;
        ListNode t=head;
        while(t!=null){
            t=t.next;
            s++;
        }
        System.out.println("s="+s+" n="+n);
        if(s-n==0) return head.next;
        t=head;
        int i=1;
        while(i<s-n){
            t=t.next;
            i++;
        }
        t.next=t.next.next;
        return head;
    }
}
