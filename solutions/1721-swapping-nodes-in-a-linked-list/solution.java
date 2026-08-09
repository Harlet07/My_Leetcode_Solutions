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
    public int noOfNodes(ListNode head){
        if(head==null) return 0;
        ListNode t=head;
        int c=0;
        while(t!=null) {
            t=t.next;
            c++;    
        }
        return c;
    }

    public ListNode swapNodes(ListNode head, int k) {
        if(head.next==null) return head;
        ListNode t=head,kt=null,lkt=null;
        int i=1;
        int n=noOfNodes(head);
        int kl=n-k+1;

        while(i<=n || (kt==null && lkt==null)){
            if(i==k) kt=t;
            if(i==kl) lkt=t;
            t=t.next;
            i++;
        }
        
        int tmp=kt.val;
        kt.val=lkt.val;
        lkt.val=tmp;

        return head;
    }
}
