import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CorrectBinaryTree {

	
	/**
     * use Msorris in order tree walk.
     * O(nLog(n)) complexity, but constant space.
     */
    public void recoverTree1(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(root == null) return;
        
        TreeNode curNode = root;
        TreeNode parNode = null;
        TreeNode preNode = null;
        ArrayList<TreeNode> swaps = new ArrayList<TreeNode>();
        //morris in order tree walk,
        //modify right point link while traversal
        //remove it second time.
        
        while(curNode != null){
            if(curNode.left == null){
                //when left child pointer is null. move to right.
                //we maybe moving up now.
                if(parNode != null && parNode.val > curNode.val){
                    fillInSwaps(swaps, parNode, curNode);
                }
                parNode = curNode;
                curNode = curNode.right;
            }else{
                preNode = curNode.left;
                //go all the way to the right most child of left child.
                while(preNode.right != null && preNode.right != curNode){
                    preNode = preNode.right;
                }
                
                //if the right pointer is empty. link the right pointer to current node.
                //then move to the left.
                if(preNode.right == null){
                    preNode.right = curNode;
                    curNode = curNode.left;
                }else{ //after moving up. if we found the right pointers of left child linked back to ourselves.
                //we need to break the link to unmodify the tree and print currnt node.
                //then move to the right.
                    preNode.right = null;
                    
                    if(parNode != null && parNode.val > curNode.val){
                        fillInSwaps(swaps, parNode, curNode);
                    }
                    parNode = curNode;
                    curNode = curNode.right;
                }
            }
        }
        
        int temp = swaps.get(0).val;
        swaps.get(0).val = swaps.get(1).val;
        swaps.get(1).val = temp;
        return;
    }
    
    public void fillInSwaps(ArrayList<TreeNode> swaps, TreeNode parNode, TreeNode curNode){
        if(swaps.size() == 0){
            swaps.add(parNode);
            swaps.add(curNode);
        }else
            swaps.set(1, curNode);
    }
    
    
	public void recoverTree(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null) return;
        List<TreeNode> swaps = new LinkedList<TreeNode>();
        
        helper(root, null, swaps);
        
        int temp = swaps.get(0).val;
        swaps.get(0).val = swaps.get(1).val;
        swaps.get(1).val = temp;
    }
    
	/**
	 * pass previous node around.
	 * pass swap node list around
	 * stack uses space.
	 * 
	 * 
	 * @param node
	 * @param prev
	 * @param swaps
	 * @return
	 */
    public TreeNode helper(TreeNode node, TreeNode prev, List<TreeNode> swaps){
        if(node == null) return prev ;
        prev = helper(node.left, prev, swaps);
        if(prev != null && node.val < prev.val) {
            if(swaps.size() == 0){
                swaps.add(prev);
                swaps.add(node);
            }else{
                swaps.set(1, node);
            }
        }
        return helper(node.right, node, swaps);
    }
    
    
    static class TreeNode {
    	     int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
     }
}
