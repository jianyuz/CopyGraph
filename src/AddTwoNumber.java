
/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 * @author zhouzhou
 *
 */
public class AddTwoNumber {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int v){
			val = v;
		}
	}
	
	
	/**
	 * add node one by one, 
	 * use carry
	 * note carry calculation use division
	 * left use %
	 * 
	 * remember to advance the listNode.
	 * 
	 * last carryover need to be noted too
	 * under the case both l1 and l1 current are null.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //note stored in reversed order
        
        if(l1 == null && l2 == null) return null;
        if(l1 != null && l2 == null) return l1;
        if(l2 != null && l1 == null) return l2;
        
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        int carry = 0;
        int sum = 0;
        while(l1 != null && l2 != null){
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            head.next = new ListNode(sum);
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        //add whatever is left
        if(l1 != null){
            while(l1 != null){
                sum = l1.val + carry;
                carry = sum/10;
                sum = sum%10;
                head.next = new ListNode(sum);
                head = head.next;
                l1 = l1.next;
            }
        }
        
        
        if(l2 != null){
            while(l2 != null){
                sum = l2.val + carry;
                carry = sum/10;
                sum = sum%10;
                head.next = new ListNode(sum);
                head = head.next;
                l2 = l2.next;
            }
        }
        
        if(carry > 0){
            head.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}
