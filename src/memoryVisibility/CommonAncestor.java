package memoryVisibility;

public class CommonAncestor {

	/**
	 * one is in left tree, one is in right tree.
	 * one is in left tree or right tree.
	 * one is the root 
	 * @param args
	 */
	public static void main(String[] args){
		Node root = new Node(1);
		Node n1 = new Node(2);
		Node n2 = new Node(3);
		root.left = n1;
		root.right = n2;
		Node common = commonAncestor(root, n2, n1);
		System.out.println(common.data);
	}
	
	//just return L1 or L2 if find them.
	public static Node commonAncestor(Node n, Node n1, Node n2){
		if(n == null) return null;
		if(n == n1 || n == n2) return n;

		Node left = commonAncestor(n.left, n1, n2);
		Node right = commonAncestor(n.right, n1, n2);
		if(left != null && right != null){
			return n;
		}
		
		return (left != null)?left:right;	
	}
	
	static class Node{
		Node left;
		Node right;
		int data;
		Node(int data){
			this.data = data;
		}
	}
}
