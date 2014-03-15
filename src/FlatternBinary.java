
/**
 * flatten binary tree into linked list.
 * 
 * @author zhouzhou
 *
 */
public class FlatternBinary {

	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		flatten(root);
		
		TreeNode curr = root;
		while(curr != null){
			System.out.print(curr.data + " ");
			curr = curr.right;
		}
	}
	
	
	//pure recursive solution.
    //preorder traversal
	//concept of left tail and right tail.
	//if one tail is empty, 
	//replace it with a fallback
	//left tail fallback is middle node.
	//right tail fall back is lefttail.
	
	//point node.right to node left.
	//leftTail to node right
	//node.left to empty.
	
    public TreeNode flattenWithTailReturn(TreeNode node){
   
        if(node == null) return null;
        
        TreeNode leftTail = flattenWithTailReturn(node.left);
        if(leftTail == null){
            leftTail = node;
        }
        
        TreeNode rightTail = flattenWithTailReturn(node.right);
        if(rightTail == null){
            rightTail = leftTail;
        }
        
        TreeNode tmp = node.right;
        node.right = node.left;
        leftTail.right = tmp;
        node.left = null;
        return rightTail;
    }
    
    
	public static void flatten(TreeNode root) {
        if(root == null) return;
        linkNode(null, root);
        
    }
    
	/**
	 * link the node in reverse pre-order traversal
	 * right first followed by left then middle.
	 * link the node visited with its previous node.
	 */
	public static void linkNode(TreeNode previous, TreeNode node){
        
        if(node.right !=null){
            if(node.left != null){
                TreeNode rightMost = findRightMost(node.left);
                linkNode(rightMost, node.right);
            }else{
                linkNode(node, node.right);
            }
        }
        
        if(node.left != null){
            linkNode(node, node.left); 
        }
        
        if(previous != null){
            previous.right = node;
            previous.left = null;
        }
        
        return;
        
    }
    
	//find the rightmost child of the subtree under the node.
    public static TreeNode findRightMost(TreeNode node){
        while(node != null){
            if(node.left == null && node.right == null){
                return node;
            }else if(node.right != null){
                node = node.right;
            }else if(node.left != null){
                node = node.left;
            }
        }
        return null;
    }
    
    static class TreeNode{
    	int data;
    	TreeNode left;
    	TreeNode right;
    	
    	public TreeNode(int data){
    		this.data = data;
    	}
    }
}
