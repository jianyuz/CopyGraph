import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * two elements of binary search tree are swapped by mistake
 * recover the tree without changing its structure
 * @author zhouzhou
 *
 */
public class CorrectBinaryTree {

	
	public static  void morrisWalk(TreeNode root){
		if(root == null) return;
		TreeNode current = root;
		while(current != null){
			if(current.left == null){
				System.out.println(current.val);
				current = current.right;
			}else{
				TreeNode tmp = current.left;
				while(tmp.right != null && tmp.right != current){
					tmp = tmp.right;
				}
				if(tmp.right != current){
					tmp.right = current;
					current = current.left;
				}else{ //have finished the traversal break the right pointer link. print current value and move current pointer.
					tmp.right = null;
					System.out.println(current.val);
					current = current.right;
				}
			}
		}
	}
	/**
     * use Msorris in order tree walk.
     * modify the part that we move current node.
     * 
     * O(nlog(n)) complexity, but constant space.
     */
    public void recoverTree1(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(root == null) return;
        
        TreeNode curNode = root;
        TreeNode parNode = null;//traversal previous node.
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
    
    public static void main(String[] args){
    	//14325
    	TreeNode root = new TreeNode(3);
    	root.left = new TreeNode(1);
    	root.left.right = new TreeNode(4);
    	root.right = new TreeNode(5);
    	root.right.left = new TreeNode(2);
//    	
//    	recoverTree(root);
//    	
//    	printTree(root);
    	
    	morrisWalk(root);
    	
    }
    
    
    public static void printTree(TreeNode root){
    	if(root == null) return;
    	printTree(root.left);
    	System.out.println(root.val);
    	printTree(root.right);
    }
    
	public static void recoverTree(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null) return;
        List<TreeNode> swaps = new LinkedList<TreeNode>();
        
        helper(root, null, swaps);
        
        //after we find the two misplaced nodes,
        //just swap their values.
        
        int temp = swaps.get(0).val;
        swaps.get(0).val = swaps.get(1).val;
        swaps.get(1).val = temp;
    }
    
	/**
	 * 
	 * in order tree traversal
	 * 
	 * think of how many pairs of nodes are in wrong order.
	 * first pair of out of order, the first element is the one we need to swap.
	 * the last pair of out of order, the second element is the one we need to swap.
	 * we need to keep track of the previous node to compare nodes while doing in order traversal.
	 * 
	 * 
	 * pass previous node around. previous node comes from node
	 * pass swap node list around to keep track of which two nodes needs to be swapped.
	 * stack uses space.
	 * 
	 * helper return last visited node.
	 * O(n) solution O(h) space.
	 * 
	 * @param node
	 * @param prev  //passed in previous node
	 * @param swaps
	 * @return last visited node in the tree.
	 */
    public static TreeNode helper(TreeNode node, TreeNode prev, List<TreeNode> swaps){
        if(node == null) return prev ;
        
        prev = helper(node.left, prev, swaps); //the returned node here is the last visited node.
        if(prev != null && node.val < prev.val) {
            if(swaps.size() == 0){
                swaps.add(prev);
                swaps.add(node); //could be the only one pair
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
