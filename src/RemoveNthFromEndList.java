
public class RemoveNthFromEndList {

	static class ListNode{
		int val;
		ListNode next;
		ListNode(int v){
			this.val = v;
		}
	}
	public ListNode removeNthFromEnd(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head == null) return null;
        if(n <=0) return null; //n <=0 doesn't make sense.
        
        ListNode dummy = new ListNode(0); //add dummy head.
        dummy.next = head;
        
        ListNode cur = head;
        int count = 0;
        while(cur != null){
            count ++;
            cur = cur.next; //increment cur first
            if(count == n) break; //found the nth  node. 
        }
        if(count != n) return null; //not found, the length of list is shorter.
        
        ListNode nth = dummy; //start from dummy since we need to break the link from the node ahead.
        while(cur != null){//continue iterate through
            cur = cur.next;
            nth = nth.next;
        }
        
        //detach the nth node.
        nth.next = nth.next.next;
        
        return dummy.next;
    }
}
