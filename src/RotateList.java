
public class RotateList {

	
	/**
	 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

k may be longer than n.
	 * @author zhouzhou
	 *
	 */
	
	static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
	}
	
	public ListNode rotateRight(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(head == null) return null;
        
        ListNode p1 = head;
        
        int count = 0; //p1 goes n steps first 
        while(count < n){
            if(p1.next != null){
                p1 = p1.next;
            }else{
                p1 = head; //rotate over
            }
            count ++;
        }
        
        
        ListNode p2 = head;
        //iterate p1 and p2 together.
        while(p1.next != null){
            if(p2.next != null)
                p2 = p2.next;
            else
                p2 = head; //rotate over.
            p1 = p1.next;
        }
        
        //rotate the list.
        p1.next = head;
        head = p2.next;
        p2.next = null;
        
        return head;
    }
	
	
	/**
	 * improved the version.
	 * calculate list length first.
	 * don't need to circle around the list.
	 * if n >>> length of the list.
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode rotateRigh1t(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(head == null) return null;
        
        int len = getLength(head);
        
        n = n % len;
        
        ListNode p1 = head;
        
        int count = 0; //p1 goes n steps first 
        while(count < n && p1.next != null){
            p1 = p1.next;
            count ++;
        }
        
        
        ListNode p2 = head;
        //iterate p1 and p2 together.
        while(p1.next != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        
        //rotate the list.
        p1.next = head;
        head = p2.next;
        p2.next = null;
        
        return head;
    }
    
    public int getLength(ListNode head){
        int count = 0;
        while(head != null){
            count ++;
            head = head.next;
        }
        return count;
    }
}
