
public class ReverseKGroupList {

	static class ListNode{
		int val;
		ListNode next;
		ListNode(int v){
			val = v;
			next = null;
		}
	}
	
	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(head == null) return null; //special condition.
        if(k == 1) return head; //if only 1 element in group. no reverse.
        
        //recursive solution
        int count =0;
        ListNode cur = head;
        while(cur != null) {
            cur = cur.next;
            count ++;
            if (count >=k){//longer than k.
                //reverse the list after first k.
                ListNode after = reverseKGroup(cur, k);
                
                //reverse the first k.
                //start from head, end is cur.
                ListNode c = head;
                ListNode newHead = after;
                ListNode tmp = null;
                
                while(c != cur){
                    tmp = c.next;
                    c.next = newHead;
                    newHead = c;
                    c = tmp;
                }
                
                return newHead;
            }                
        }
        //not longer than k in group.
        //return left over.
        return head;
    }
	
	/**
	 * this is a version without recursion.
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup1(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head == null || k<=0) return null;
        if(k== 1) return head;
        
        int count = 0;
        //use dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy; //keep track of start of group.
        ListNode cur = head; //keep track of the end of the group
        while(cur != null){
            count ++;
            if(count % k == 0){
                //reverse the node between pre and cur.next.
                pre = reverseGroup(pre, cur.next); //returned pre is last of the group.
                cur = pre.next;
            }else{
                cur = cur.next; //otherwise, preoceed.
            }
        }
        
        return dummy.next; //return revered list.
    }
    
    
    /**
     * pre node pre the group
     * next the node after the group.
     * 
     */
    public ListNode reverseGroup(ListNode pre, ListNode next){
        //reverse the group and return the last node.
        
        ListNode end = pre.next;//new pre is the end element of reversed list.
        //but the start of the list.
        
        ListNode cur = pre.next;
        ListNode newHead = next;//new head point to next.
        ListNode tmp = null;
        while(cur != next){
            tmp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = tmp;
        }
        pre.next = newHead;
        return end;
    }
}
