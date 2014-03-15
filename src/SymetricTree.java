import java.util.LinkedList;
import java.util.Stack;


/**
 * check if a tree is symetric
 * O(n)
 * 
 * @author zhouzhou
 *
 */
public class SymetricTree {

	 public boolean isSymmetric(TreeNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(root == null) return true;
	        return helper(root.left, root.right);
	        
	    }
	    
	    public boolean helper(TreeNode left, TreeNode right){
	        if(left == null && right == null) return true;
	        if(left == null || right == null) return false;
	        
	        if(left.val != right.val) return false;
	        
	        return helper(left.left, right.right) && helper(left.right, right.left);
	    }
	    
	    
	    /**
	     * iterative solution using depth-first search.
	     * @param root
	     * @return
	     */
	    public boolean isSymmetric1(TreeNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(root == null) return true;
	        return helper(root.left, root.right);
	    }
	    
	    
	    public boolean helper1(TreeNode left, TreeNode right){
	        Stack<TreeNode> sl = new Stack<TreeNode>();
	        Stack<TreeNode> sr = new Stack<TreeNode>();
	        
	        if(left == null && right == null) return true;
	        if(left == null || right == null) return false;
	        
	        TreeNode curNode = left;
	        while(curNode != null){
	            sl.push(curNode);
	            curNode = curNode.left;
	        }
	        
	        TreeNode curNode1 = right;
	        while(curNode1 != null){
	            sr.push(curNode1);
	            curNode1 = curNode1.right;
	        }
	        
	        while(!sl.isEmpty() && !sr.isEmpty()){
	            curNode = sl.pop();
	            curNode1 = sr.pop();
	            if(curNode.val != curNode1.val) return false;
	            
	            curNode = curNode.right;
	            if(curNode != null){
	                sl.push(curNode);
	            }
	            
	            curNode1 = curNode1.left;
	            if(curNode1 != null){
	                sr.push(curNode1);
	            }
	            while(curNode != null){
	                sl.push(curNode);
	                curNode = curNode.left;
	            }
	            
	            while(curNode1 != null){
	                sr.push(curNode1);
	                curNode1 = curNode1.right;
	            }
	        }
	        
	        if(sl.isEmpty() && sr.isEmpty()) return true;
	        else return false;
	        
	    }
	    
	    
	    public boolean isSymmetric2(TreeNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(root == null) return true;
	        
	        return helper(root.left, root.right);
	        
	    }
	    
	    /* breath first search */
	    
	    public boolean helper2(TreeNode left, TreeNode right){
	        LinkedList<TreeNode> ll = new LinkedList<TreeNode>();
	        LinkedList<TreeNode> lr = new LinkedList<TreeNode>();
	        
	        ll.add(left);
	        lr.add(right);
	        
	        TreeNode curLeft = null;
	        TreeNode curRight = null;
	        while(!ll.isEmpty() && !lr.isEmpty()){
	            curLeft = ll.poll();
	            curRight = lr.poll();
	            
	            if(curLeft == null && curRight == null) continue;
	            
	            if(curLeft == null || curRight == null) return false;
	            
	            if(curLeft.val != curRight.val) return false;
	            
	            ll.add(curLeft.left);
	            ll.add(curLeft.right);
	            lr.add(curRight.right);
	            lr.add(curRight.left);
	        }
	        return true;
	    }
	    static class TreeNode {
	    	      int val;
	    	      TreeNode left;
	    	      TreeNode right;
	    	      TreeNode(int x) { val = x; }
	    }
}
