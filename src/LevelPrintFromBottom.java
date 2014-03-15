import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class LevelPrintFromBottom {

	/**
	 * level print binary tree from bottom to root.
	 * used BFS search 
	 * @param root
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        q.add(root);
        
        int count = 1;
        
        TreeNode curr= null;
        ArrayList<Integer> levelList = new ArrayList<Integer>();
        while(!q.isEmpty()){
            
            curr = q.poll();
            levelList.add(curr.val);
            if(curr.left != null)
                q.offer(curr.left);
            
            if(curr.right != null)
                q.offer(curr.right);
            count --;
            if(count == 0 ){
                res.add(0, levelList);
                if(q.size() > 0){
                    levelList = new ArrayList<Integer>();
                    count = q.size(); //reset count size.
                }
            }
        }
        
        return res;
        
    }
	
	/**
	 * 
	 * @author zhouzhou
	 *
	 */
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
    }
	
	/**
	 * if we use depth first traversal.
	 * under balanced scenario.
	 * O(n) worst case o(n2)
	 * but saved the possible O(n) space.
	 * 
	 */
	
	public ArrayList<ArrayList<Integer>> levelOrderBottom1(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        
        int h = height(root);
        
        for( int i = h; i> 0 ; i--){
            ArrayList<Integer> levelList = new ArrayList<Integer>();
            helper(root, i, levelList);
            res.add(levelList);
        } 
        
        return res;
    }
    
    public void helper(TreeNode node, int height, ArrayList<Integer> levelList){
        if(node == null) return;
        
        if(height ==1){
            levelList.add(node.val);
        }else{
            helper(node.left, height-1, levelList);
            helper(node.right, height -1, levelList);
        }
        return;
    }
    
    public int height (TreeNode root){
        if(root == null) return 0;
        
        return 1 + Math.max( height(root.left) , height(root.right));
    }
}
