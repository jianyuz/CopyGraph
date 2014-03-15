
public class RemoveDuplicatefromList {

	/**
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(head == null) return null;
        
        ListNode pre = null;
        ListNode cur = head;
        
        while(cur != null){
            if(pre!=null && pre.val == cur.val){
                pre.next = cur.next;
                cur = pre.next;
                continue; //remove duplicate node. 
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
	
	/*
	Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
		keep pre, cur and unique node plus a flag.
     */
	
	public ListNode deleteDuplicates1(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(head == null) return head;
        if(head.next == null) return head;
        
        ListNode pre = null;
        ListNode cur = head;
        ListNode unique = null;
        
        boolean dupFound = false;
        
        while(cur != null){
            if(pre != null && cur.val != pre.val){
                if(!dupFound){
                    unique = pre;
                }else{
                    //link from unique to cur
                    if(unique != null){
                        unique.next = cur;
                    }else{
                        if(pre != null) pre.next = null; //drop the duplicat segments.
                        head = cur;
                    }
                    dupFound = false;
                }
            }else if(pre!= null && cur.val == pre.val){
                dupFound = true;
            }
            pre = cur;
            cur = cur.next;
        }
        
        if(dupFound){
            if(unique != null){
                unique.next = cur;
            }else{
                head = cur;
            }
        }
        return head;
    }
	
	/**simpler solution.
	 * use sentinel to guard the deletion of head.
	 * use cur and cur.next to compare for the duplicate.
	 * 
	 * @param head
	 * @return
	 */
	 public ListNode deleteDuplicates2(ListNode head) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(head == null) return head;
	        if(head.next == null) return head;
	        
	        ListNode guard = new ListNode(0);
	        guard.next = head;
	        head = guard;
	        
	        ListNode pre = head;
	        ListNode cur = head.next;
	        boolean dupFound = false;
	        while(cur != null){
	            
	            while(cur.next != null && cur.val == cur.next.val){//found all the duplicates.
	                cur = cur.next;
	                dupFound = true;
	            }
	            
	            if(dupFound){//found remove duplicates.
	                pre.next = cur.next;
	                cur = cur.next;
	                dupFound = false;
	            }else{
	            	//progress to check next two nodes.
	                pre = cur;
	                cur = cur.next;
	            }
	        }
	        
	        return head.next; //when return skip the guard.
	    }
	static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
	 }
}
