import java.util.ArrayList;


public class NumberOfUniqueBST {

	/**
	 * recursive solution.
	 * @param n
	 * @return
	 */
	public int numTrees1(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(n==0 || n==1) return 1;
        
        int sum = 0;
        for(int i=0; i<n; i++){
            sum += numTrees(i) * numTrees(n-1-i);
        }
        return sum;
    }
	
	/**
	 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

		For example,
		Given n = 3, there are a total of 5 unique BST's.
		
		O(n2)
		space O(n)
	 * @param n
	 * @return
	 */
	 public int numTrees(int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(n <=0) return 0;
	        
	        int[] dp = new int[n+1];
	        
	        //dp[i] number of nodes (ordered) to create a sub BST tree;
	        dp[0] = 1; //just 0 node, unqiue trees 1. this is a guardian item.
	        dp[1] = 1; //just 1 node, unique trees 1.

	        for(int i=2; i<= n; i++){
	            for(int j=0; j< i; j++){
	            	//pick the middle node and calculate the number of left substree
	            	//right substree then multiply them.
	            	//cumulate the sum.
	                dp[i] += dp[j] * dp[i-1-j]; 
	            }
	        }
	        return dp[n];
	    }
	 
	 
	 public ArrayList<TreeNode> generateTrees(int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        
	        if(n <= 0) {
	            ArrayList<TreeNode> res = new ArrayList<TreeNode>();
	            res.add(null);
	            return res;
	        }
	        return helper(1, n);
	        
	    }
	    
	    /**
	     * print out all the unique binary tree
	     * from 1 to n nodes.
	     * recursive solution.
	     * O(n3)?
	     */
	    public ArrayList<TreeNode> helper(int i, int j){
	    	 ArrayList<TreeNode> res = new ArrayList<TreeNode>();
	        if( i >  j) {
	           
	            res.add(null);
	            return res;
	        }
	        if( i == j){
	            res.add(new TreeNode(i));
	            return res;
	        }
	        
	        
	        for(int k = i ; k <= j; k++){
	           
	           ArrayList<TreeNode> resLeft = helper(i, k-1);
	           ArrayList<TreeNode> resRight = helper(k +1, j);
	           for(TreeNode nodeLeft: resLeft){
	                   for(TreeNode nodeRight: resRight){
	                       TreeNode root = new TreeNode(k);
	                       root.left = nodeLeft;
	                       root.right = nodeRight;
	                       res.add(root);
	                   }
	           }
	        }
	        
	        return res;
	    }
	    
	    public class TreeNode {
	    	     int val;
	    	      TreeNode left;
	    	      TreeNode right;
	    	      TreeNode(int x) { val = x; left = null; right = null; }
	    }
}
