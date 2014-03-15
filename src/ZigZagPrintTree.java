import java.util.ArrayList;
import java.util.LinkedList;

/**
 * level print binary tree.
 * zigzag order 
 * or normal order.
 * @author zhouzhou
 *
 */

public class ZigZagPrintTree {
	
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		zigzagLevelOrder(root);
		
	}

	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) {
            return res;
        }
        
        LinkedList<TreeNode> deQueue = new LinkedList<TreeNode>();
        
        
        deQueue.add(root);
        
        int count = 1;
        
        boolean lrFlow = true;
        
        TreeNode curNode = null;
        
        ArrayList<Integer> levelList = new ArrayList<Integer>();
        
        while(!deQueue.isEmpty()){
            while(count > 0){
                    if(lrFlow){
                        curNode = deQueue.pollFirst();
                        levelList.add(curNode.val);
                        if(curNode.left != null) deQueue.offerLast(curNode.left);
                        if(curNode.right != null)deQueue.offerLast(curNode.right);
                        count --;
                    }else{
                        curNode = deQueue.pollLast();
                        levelList.add(curNode.val);
                        if(curNode.right != null) deQueue.offerFirst(curNode.right);
                        if(curNode.left != null) deQueue.offerFirst(curNode.left);
                        count --;
                    }
                
            }
            lrFlow = !lrFlow;
            count = deQueue.size();
            res.add(levelList);
            levelList = new ArrayList<Integer>();
        }
        return res;
    }
	
	
	 public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(root == null) return res;
	        
	        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	        queue.offer(root);
	        
	        int count = 1;
	        
	        TreeNode curNode = null;
	        ArrayList<Integer> levelList = new ArrayList<Integer>();
	        while(!queue.isEmpty()){
	            while(count > 0){
	                curNode = queue.poll();
	                levelList.add(curNode.val);
	                if(curNode.left != null) queue.add(curNode.left);
	                if(curNode.right != null) queue.add(curNode.right);
	                count --;
	            }
	            
	            count = queue.size();
	            res.add(levelList);
	            levelList = new ArrayList<Integer>();
	        }
	        return res;
	    }
	static class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
	}
}
