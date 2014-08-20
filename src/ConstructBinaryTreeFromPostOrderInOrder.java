import java.util.HashMap;
import java.util.Map;

/**
 * construct search tree from postorder traversal and inorder traversal or trees.
 * preorder visit root first
 * post order visit the left tree right tree first.
 * 
 * 
 * idea is posorder element last is alreay tree.
 * then for each next element in post order array
 * check if it is on the left or right of existing treenodes.
 * and if it is on the left, create left node and added it to the parent
 * otherwise add it to the right, 
 * 
 * cause the tree could be skewed.
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
	 *  T(n) = 2T(n/2) + f(n)
	 *  a = 2 b = 2 c= 0
	 *  
	 *  (fn nc logkn
	 *  
	 *  if(c == logba) ...)
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
	
	/**
	 * recursive solution
	 * from in order tree to find relevant postorder region.
	 * need a index map to map element to index  in inorder array.
	 * 
	 * @param inorder
	 * @param istart
	 * @param iend
	 * @param postorder
	 * @param pstart
	 * @param pend
	 * @param indexMap
	 * @return
	 */
	public TreeNode helper(int[] inorder, int istart, int iend, 
			int[] postorder, int pstart, int pend,  Map<Integer, Integer> indexMap){
		
		if(istart > iend) return null;
		
		TreeNode root = new TreeNode(postorder[pend]);
		int iIndex = indexMap.get(postorder[pend]);
		//divide the array further.
		int len = iIndex - istart; //length of left subtree.
		
		TreeNode left = helper(inorder, istart, iIndex -1, 
				postorder, pstart, pstart + len -1,indexMap);
		TreeNode right = helper(inorder, iIndex +1, iend,
				postorder, pstart + len, pend -1, indexMap);
		                                 //eliminate the end of the range.
		
		root.left = left;
		root.right = right;
		return root;
	}

	public static TreeNode buildTree(int[] inorder, int[] postorder) {
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
    
    public static void insertIntoTree(TreeNode root, int element, Map<Integer, Integer> indexMap){
        
        
        while(root != null){//the loop got the traversal function to find the right root.
            int indexE = indexMap.get(element);
            int indexR = indexMap.get(root.val);
            if(indexE < indexR){
                if(root.left == null){
                    root.left = new TreeNode(element);
                    //still need current root. //return here.
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
	    
	    public static void inorderTraverse(TreeNode root){
	    	if(root == null) return;
	    	inorderTraverse(root.left);
	    	System.out.println(root.val);
	    	inorderTraverse(root.right);
	    }
	    public static void main(String[] args){
	    	int[] inorder = {2, 4, 5, 6, 7};
	    	int[] postorder = {2, 5, 4, 7, 6};
	    	
	    	TreeNode root = buildTree(inorder, postorder);
	    	
	    	inorderTraverse(root);
	    }
}
