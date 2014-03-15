
public class PartitionList {

	/**
	 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	 * You should preserve the original relative order of the nodes in each of the two partitions.
	 * @author zhouzhou
	 *
	 */
	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
	 }
	
	 public ListNode partition(ListNode head, int x) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(head == null) return null;
	        
	        ListNode curNode = head;
	        ListNode preNode = null;
	        ListNode markNode = null;
	        
	        boolean found = false; //found irregular
	        
	        while(curNode != null){
	            if(curNode.val < x ){
	                if(found){
	                    //delete the node.
	                    ListNode temp = curNode;
	                    preNode.next = curNode.next;
	                    curNode = preNode.next;
	                    if(markNode != null){
	                        //insert right after markNode;
	                        ListNode temp1 = markNode.next;
	                        markNode.next = temp;
	                        temp.next = temp1;
	                        markNode = temp;
	                    }else{
	                        //insert at the head.
	                        ListNode temp1 = head;
	                        head = temp;
	                        temp.next = temp1;
	                        markNode = temp;
	                    }
	                    continue;
	                }
	            }else{
	                if(!found){ //mark the node that precede the bigger nodes.
	                    found = true;
	                    markNode = preNode;
	                }
	            }
	            preNode = curNode;
	            curNode = curNode.next;
	            
	        }
	        
	        return head;
	    }
}
