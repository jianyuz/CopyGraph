
public class ConvertListToBST {

	/**
	 * Complexity
	 * master theorem
	 * 
	 * T(n) = 2 T(n/2) + O(n)
	 * 
	 * a = 2 b =2 c = 1
	 * 
	 * c = log b A
	 * 
	 * general form
	 * f(n) - N pow c logn pow k
	 * T(n) = N pow c logn pow (K+1)
	 * in this case O(nlogn)
	 * 
	 * if c < Log B A
	 * O(N pow logBA)
	 * 
	 * if c > log b A 
	 * O( N to c)
	 * 
	 * 
	 * 
	 * @param head
	 * @return
	 */
	public TreeNode sortedListToBST(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head == null) return null;
        if(head.next == null){
            return new TreeNode(head.val);
        }
        return convertHelper(head);
        
    }
    
	/**
	 * recursively build up left and subtree.
	 * @param head
	 * @return
	 */
    public TreeNode convertHelper(ListNode head){
        if(head == null) return null;
        ListNode midNode = findMidNode(head);
        
        TreeNode mid = new TreeNode(midNode.val);
        if(midNode != head){ //head may be the same as midNode.
        	//then we don't consider head for left tree.
            TreeNode left  = convertHelper(head);
            mid.left = left;
        }
        
        TreeNode right = convertHelper(midNode.next);
        mid.right = right;
        return mid;
    }
    
    /**
     * find middle node using fast and slow pointer.
     * keep track of previous node to break the link.
     * 
     * @param head
     * @return
     */
    public ListNode findMidNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while(slow != null && fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if(prev != null)
            prev.next = null; //break the prev's link to midNode.
        return slow;
    }
    
    static class ListNode {
    	      int val;
    	      ListNode next;
    	      ListNode(int x) { val = x; next = null; }
    }
    
    static class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    }
    
    
    /**
     * O(n) solution. bottom up.
     * recursive solution
     * java doesn't pass reference use a wrapper class
     * 
     */
    
    public TreeNode sortedListToBST1(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head == null) return null;
        if(head.next == null){
            return new TreeNode(head.val);
        }
        int n = length(head); //count the length of the list.
        NodePointer np = new NodePointer();
        np.node = head;
        return helper(np, 0, n-1);
        
    }
    
    public TreeNode helper(NodePointer np, int start , int end){
        if(start > end) return null;
        
        int mid = start + (end-start)/2;
        
        TreeNode left  = helper(np, start, mid -1);
        TreeNode parent = new TreeNode(np.node.val);
        np.node = np.node.next;//for right subtree, update the node pointer.
        
        TreeNode right = helper(np, mid+1, end);
        parent.left = left;
        parent.right = right;
        return parent;
        
    }
    
    public int length(ListNode head){
        int counter = 0;
        while(head != null){
            counter ++;
            head = head.next;
        }
        return counter;
    }
    
    static class NodePointer{
        ListNode node;
    }
    
    
    /**
     * convert sorted array to Binary search tree
     * this is easier since the reference pointer is inherent
     * in array.
     * 
     */
    
    public TreeNode sortedArrayToBST(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(num == null || num.length == 0) return null;
     
        
        return helper (num, 0, num.length -1);
    }
    
    public TreeNode helper(int[] num, int start, int end){
        if(start > end) return null;
        
        int mid = start + (end -start)/2;
        
        TreeNode parent = new TreeNode(num[mid]);
        
        TreeNode left = helper(num, start , mid -1);
        TreeNode right = helper(num, mid +1 , end);
        
        parent.left = left;
        parent.right = right;
        return parent;
        
    }
    
    public static void main(String[] args){
    	int[] num = new int[0];
    	
    	
    }
}
