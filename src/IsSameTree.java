import java.util.LinkedList;


public class IsSameTree {

	 public boolean isSameTree(TreeNode p, TreeNode q) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(p == null && q == null) return true;
	        if(p == null || q == null) return false;
	        if(p.val != q.val) return false;
	        
	        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	    }
	 
	 /**
	  * iterative solution using breadth first search.
	  * @param p
	  * @param q
	  * @return
	  */
	 public boolean isSameTree1(TreeNode p, TreeNode q) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        LinkedList<TreeNode> ll = new LinkedList<TreeNode>();
	        LinkedList<TreeNode> lr = new LinkedList<TreeNode>();
	        
	        ll.add(p);
	        lr.add(q);
	        
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
	            lr.add(curRight.left);
	            lr.add(curRight.right);
	            
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
