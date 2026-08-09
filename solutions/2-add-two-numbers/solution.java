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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res=new ListNode(l1.val+l2.val);
        ListNode t1=l1.next,t2=l2.next,tres=res;
        while(t1!=null && t2!=null){
            tres.next=new ListNode(t1.val+t2.val);
            if(tres.val>9){
                tres.val%=10;
                tres.next.val+=1;
            }
            tres=tres.next;
            t1=t1.next;
            t2=t2.next;
        }
        while(t1!=null){
            tres.next=new ListNode(t1.val);
            if(tres.val>9){
                tres.val%=10;
                tres.next.val+=1;
            }
            tres=tres.next;
            t1=t1.next;
        }
        while(t2!=null){
            tres.next=new ListNode(t2.val);
            if(tres.val>9){
                tres.val%=10;
                tres.next.val+=1;
            }
            tres=tres.next;
            t2=t2.next;
        }
        if(tres.val>9){
            tres.val%=10;
            tres.next=new ListNode(1);
        }

        return res;
    }
}
