import java.util.ArrayList;


public class BSTInorderTraversal {

	public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }
    
    public void helper(TreeNode node, ArrayList<Integer> res){
        if(node == null) return;
        helper(node.left, res);
        res.add(node.val);
        helper(node.right, res);
    }
    
    
    public class TreeNode {
    	    int val;
    	    TreeNode left;
    	     TreeNode right;
    	     TreeNode(int x) { val = x; }
     }
    
    
    //morris walk
    public ArrayList<Integer> inorderTraversal1(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        TreeNode curNode = root;
        
        TreeNode preNode = null;
        
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
                    preNode.right = null; //reset the link to point to null. since we found we get back curNode.
                    res.add(curNode.val);
                    curNode = curNode.right; 
                }
                
            }
        }
        
        return res;
    }
    
}
