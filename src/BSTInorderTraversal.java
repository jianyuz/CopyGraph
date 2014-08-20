import java.util.ArrayList;
import java.util.List;


/** inorder traversal
 * to get all the node values.
 * @author zhouzhou
 *
 */
public class BSTInorderTraversal {

	/**
	 *    1
	 * 2     3
	 *   4 5
	 * @param args
	 */
	
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(5);
		
		List<Integer> res = morrisWalk(root);
		System.out.println(res);
	}
	public static ArrayList<Integer> inorderTraversal(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }
    
    public static void helper(TreeNode node, ArrayList<Integer> res){
        if(node == null) return;
        helper(node.left, res);
        res.add(node.val);
        helper(node.right, res);
    }
    
    
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    
    
    //morris walk in order traversal without stack
	/**
	 * A binary tree is threaded by making every left child pointer (that would otherwise be null) point to the in-order predecessor 
	 * of the node (if it exists) and every right child pointer (that would otherwise be null) point to the in-order successor of the node (if it exists).
	 * 
	 * 
	 * 1. Initialize current as root 
	2. While current is not NULL
   If current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Make current as right child of the rightmost node in current's left subtree
      b) Go to this left child, i.e., current = current->left
	 * 
	 * @param root
	 * @return
	 */
    public static ArrayList<Integer> inorderTraversal1(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        TreeNode curNode = root; //keep track of current node.
        
        TreeNode preNode = null; //find the left prenode.
        
        while(curNode != null){
            if(curNode.left == null){
                res.add(curNode.val);
                curNode = curNode.right;//this right pointer keeps in order traversal all the way to parent.
            }else{//left node is not null.
                //find the rightmost child of left child.
                preNode = curNode.left;
                //make sure right node is not pointing back to curNode yet.
                while(preNode.right != null && preNode.right != curNode){
                    preNode = preNode.right;
                }
                
                if(preNode.right == null){
                    //point preNode.right to curnode to estabilish inorder link.
                    preNode.right = curNode;
                    curNode = curNode.left; //continue to left child.
                }else{
                    preNode.right = null; //reset the link to point to null. since we found it goes back curNode.
                    res.add(curNode.val);
                    curNode = curNode.right; 
                }
                
            }
        }
        
        return res;
    }
    
    /**
     * mostly adjust the right pointer.
     * after traversal change the pointer back.
     * @param root
     * @return
     */
    public static List<Integer> morrisWalk(TreeNode root){
    	List<Integer> res = new ArrayList<Integer>();
    	if(root == null) return res;
    	
    	TreeNode curNode = root;
    	TreeNode preNode = null;
    	
    	while(curNode != null){
    		if(curNode.left == null){
    			res.add(curNode.val);
    			curNode = curNode.right;
    		}else{
    			preNode = curNode.left;
    			while(preNode.right != null && preNode.right != curNode){//not pointer back.
    				preNode = preNode.right;
    			}
    			
    			if(preNode.right == null){//not point back, set up the predecessor link
    				preNode.right = curNode;
    				curNode = curNode.left;
    			}else{
    				//it it pointing right back to curNode; second time traverse reset the predecessor link.
    				res.add(curNode.val);
    				preNode.right = null;
    				curNode = curNode.right;
    			}
    		}
    	}
    	return res;
    }
    
}
