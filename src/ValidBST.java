/*
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/
public class ValidBST {
	
	  public boolean isValidBST(TreeNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	    }
	    
	    
	    public boolean helper(TreeNode root, int min, int max){
	        if(root == null) return true;
	        
	        boolean leftRes = helper(root.left, min, root.val);
	        boolean rightRes = helper(root.right, root.val, max);
	        return root.val > min && root.val < max && leftRes && rightRes;
	    }//here we don't consider the equals sign;
	    
	    public class TreeNode {
	    	      int val;
	    	      TreeNode left;
	    	      TreeNode right;
	    	      TreeNode(int x) { val = x; }
	    }

}
