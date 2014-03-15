
public class SwapNodeInPari {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int v){
			val = v;
		}
	}
	public ListNode swapPairs(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head == null) return null;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = head;
        
        int count = 0;
        while(cur != null){
            count ++;
            if(count % 2== 0){
                pre = swap(pre, cur.next);
                cur = pre.next;
            }else{
                cur = cur.next;
            }
        }
        
        return dummy.next;
    }
    
    public ListNode swap(ListNode pre, ListNode next){
        ListNode last = pre.next;
        
        ListNode newHead = next;//set newHead to link to next.
        ListNode cur = pre.next;
        ListNode tmp = null;
        while(cur != next){
            tmp = cur.next;
            cur.next = newHead;
            newHead = cur; //update newHead.
            cur = tmp;
        }
        pre.next = newHead; //link preNext to newHead.
        return last;
    }
    
    
    //more straighforward version.
    //since we are only swaping two nodes.
    public ListNode swapPairs1(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(head == null) return null;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode p1 = head;
        
        ListNode tmp = null;
        while(p1 != null && p1.next != null){
            tmp = p1.next;
            p1.next = p1.next.next;
            tmp.next = p1;
            //swap two nodes.
            
            //link with pre.
            pre.next = tmp;
            //update pre and p1.
            pre = p1;
            p1 = pre.next;
        }
        
        return dummy.next;
    }
}
