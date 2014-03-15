
public class ReverseLinkedList {

	
	public class ListNode {
		     int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
		 }
	
	/**
	 * reverse linked list between m and n.
	 * in place and one pass
	 * O(n)
	 * 
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(head == null) return null;
	        if(m >= n) return head;
	        
	        ListNode startTail = null;
	        ListNode endHead = null;
	        ListNode midHead = null, midTail=null;
	        ListNode tmp;
	        
	        ListNode curNode = head;
	        int count = 0;
	        
	        while(curNode != null){
	            count ++ ;
	            tmp = curNode.next;
	            if(count == (m-1)){
	                startTail = curNode;
	            }else if(count == (n+ 1)){
	                endHead = curNode;
	            }else if(count >=m && count <=n){
	                if(count == m){
	                    midTail = curNode;
	                    midHead = curNode;
	                }else{
	                    curNode.next = midHead;
	                    midHead = curNode;
	                }
	            }
	            curNode = tmp;
	        }
	        if(startTail != null)
	            startTail.next = midHead;
	        
	        if(midTail != null)
	            midTail.next = endHead;
	        
	        if(startTail == null) return midHead;
	        else
	            return head; 
	    }
}
