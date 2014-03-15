/**
 * merge two sorted list
 * @author zhouzhou
 *
 */
public class MergeSortedList {

	
	static  class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
     }
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
    
        ListNode head = null;
        if(l1.val < l2.val){
            head = l1;
            l1 = l1.next;
        }else{
            head = l2;
            l2 = l2.next;
        }
        
        ListNode cur1 = l1, cur2 = l2, cur = head;
        while(cur1 != null && cur2 != null){
                if(cur1.val < cur2.val){
                    cur.next = cur1;
                    cur1 = cur1.next;
                }else{
                    cur.next = cur2;
                    cur2 = cur2.next;
                }
                cur = cur.next;
        }
        
        if (cur1 != null){
            cur.next = cur1;
        }
        
        if (cur2 != null){
            cur.next = cur2;
        }
        
        return head;
    }
	
	/**
	 * use a dummy head to simplify it.
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
    
        ListNode head = new ListNode(0);
        
        ListNode cur1 = l1, cur2 = l2, cur = head;
        while(cur1 != null && cur2 != null){
                if(cur1.val < cur2.val){
                    cur.next = cur1;
                    cur1 = cur1.next;
                }else{
                    cur.next = cur2;
                    cur2 = cur2.next;
                }
                cur = cur.next;
        }
        
        if (cur1 != null){
            cur.next = cur1;
        }
        
        if (cur2 != null){
            cur.next = cur2;
        }
        
        return head.next;
    }
	
	/**
	 * recursive solution
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
    
        if(l1 != null && l2 != null){
                if(l1.val < l2.val){
                    l1.next = mergeTwoLists2(l1.next, l2);
                    return l1;
                }else{
                    l2.next = mergeTwoLists2(l1, l2.next);
                    return l2;
                }
        }
        
        return null;//must have it to prevent missing return statement.
    }
}
