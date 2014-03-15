import java.util.HashMap;
import java.util.Map;

/**
 * construct binary tree from postorder traversal and inorder traversal or trees.
 * 
 * idea is posorder element last is alreay tree.
 * then for each next element in post order array
 * check if it is on the left or right of existing treenodes.
 * and insert it into the correct places.
 * 
 * O(n2) complexity.
 * 
 * @author zhouzhou
 *
 */
public class ConstructBinaryTreeFromPostOrderInOrder {
	
	 public TreeNode buildTree2(int[] preorder, int[] inorder) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(preorder == null || inorder == null) return null;
	        if(preorder.length == 0 || inorder.length == 0) return null;
	        
	        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
	        //preprocess
	        for(int i=0; i< inorder.length; i++){
	            indexMap.put(inorder[i], i);
	        }
	        
	        return helper(preorder, 0, preorder.length -1, inorder, 0, inorder.length -1, indexMap);
	    }
	    
	    public TreeNode helper2(int[] preorder, int pstart, int pend, 
	                    int[] inorder, int istart, int iend, Map<Integer,Integer> indexMap){
	        
	        if(pstart > pend){
	            return null;
	        }
	        
	        int iIndex = indexMap.get(preorder[pstart]);
	        
	        int len = iIndex - istart;
	        
	        TreeNode mid = new TreeNode(preorder[pstart]);
	        
	        TreeNode left = helper(preorder, pstart +1, pstart + len, 
	            inorder, istart, iIndex -1, indexMap);
	        TreeNode right = helper(preorder, pstart + len + 1, pend,
	            inorder, iIndex + 1, iend, indexMap);
	            
	        mid.left = left;
	        mid.right = right;
	        return mid;
	        
	    } 
	    
	/** O(n) solution
	 *  T(n) = 2T(n/2) + C
	 *  a = 2 b = 2 c= 0
	 *  
	 *  c < logba
	 *  O(n logba)
	 *  = (O(n)
	 *  master theorem
	 *  
	 *  worst case
	 *  skewed. still O(n)
	 *  
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public TreeNode buildTree1(int[] inorder, int[] postorder){
		
		if(postorder == null || inorder == null) return null;
        if(postorder.length == 0 || inorder.length == 0) return null;
        
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        
        //preprocess to get index of post order element in in order array.
        for(int j=0; j < inorder.length; j++){
             indexMap.put(inorder[j], j);
        }
        
        
        return helper(inorder, 0, inorder.length-1, postorder, 
        		0, postorder.length -1, indexMap);
	}
	
	public TreeNode helper(int[] inorder, int istart, int iend, 
			int[] postorder, int pstart, int pend,  Map<Integer, Integer> indexMap){
		
		if(istart > iend) return null;
		
		TreeNode root = new TreeNode(postorder[pend]);
		int iIndex = indexMap.get(postorder[pend]);
		//divide the array further.
		int len = iIndex - istart;
		
		TreeNode left = helper(inorder, istart, iIndex -1, 
				postorder, pstart, pstart + len -1,indexMap);
		TreeNode right = helper(inorder, iIndex +1, iend,
				postorder, pstart + len, pend -1, indexMap);
		
		root.left = left;
		root.right = right;
		return root;
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(postorder == null || inorder == null) return null;
        if(postorder.length == 0 || inorder.length == 0) return null;
        
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        
        //preprocess to get index of post order element in in order array.
            for(int j=0; j < inorder.length; j++){
                    indexMap.put(inorder[j], j);
            }
        
        
        TreeNode root = new TreeNode(postorder[postorder.length -1]);
        
        if(postorder.length > 1){
            for(int i = postorder.length -2; i>=0; i--){
                insertIntoTree(root, postorder[i], indexMap);
            }
        }
        
        return root;
        
    }
    
    public void insertIntoTree(TreeNode root, int element, Map<Integer, Integer> indexMap){
        
        
        while(root != null){
            int indexE = indexMap.get(element);
            int indexR = indexMap.get(root.val);
            if(indexE < indexR){
                if(root.left == null){
                    root.left = new TreeNode(element);
                    return;
                }
                root = root.left;
            }else{
                if(root.right ==null){
                    root.right = new TreeNode(element);
                    return;
                }
                root = root.right;
            }
        }
        
        return;
    }
	    
	    static  class TreeNode {
	    	     int val;
	    	      TreeNode left;
	    	      TreeNode right;
	    	      TreeNode(int x) { val = x; }
	    }
}
