
public class MinDepthBinaryTree {

	/**
	 * Given a binary tree, find its minimum depth.

	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(root == null) return 0; //guardian condition. don't want special case to spoil the logic below.
        return doMinDepth(root);
    }
    
    public int doMinDepth(TreeNode root){
        if(root == null) return Integer.MAX_VALUE; //this is not the mindepth.
        
        if(root.left == null && root.right == null){
            return 1; //left node;
        }
        
        return Math.min(doMinDepth(root.left), doMinDepth(root.right)) + 1;
    }
    
    static class TreeNode{
    	int val;
    	TreeNode left;
    	TreeNode right;
    }
}
