/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.
 * @author zhouzhou
 *
 */
public class SumOfTrees {

	   public int sumNumbers(TreeNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(null == root) return 0;
	        int bsum = 0;
	        int tsum = 0;
	        return sum (root, bsum, tsum);
	        
	    }
	    
	    public int sum (TreeNode node, int bsum, int tsum){
	        bsum = bsum * 10 + node.val;
	        if(node.left == null && node.right == null){
	            return tsum + bsum;
	        }else if(node.left == null){
	            return sum(node.right, bsum, tsum);
	        }else if(node.right == null){
	            return sum(node.left, bsum, tsum);
	        }else{
	            tsum =  sum(node.left, bsum, tsum);
	            return sum(node.right, bsum, tsum);
	        }
	    }
	    
	    
	    static class TreeNode{
	    	int val;
	    	TreeNode left;
	    	TreeNode right;
	    	TreeNode(int val){
	    		this.val = val;
	    	}
	    }
}
