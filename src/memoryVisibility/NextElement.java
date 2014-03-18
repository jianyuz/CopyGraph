package memoryVisibility;

public class NextElement {
	
	public static Node next (Node n){
		if(n == null) return null;
		
		if(n.right != null){
			return leftMost(n.right);
		}
		
		//right node is empty. find the parent who is next value.
		Node p = n.parent;
		while(p != null && p.right == n){
			n = p;
			p = p.parent;
		}
		return p;
		
		
	}
	
	public static Node leftMost(Node n){
		if(n == null) return null;
		
		while(n.left != null){
			n = n.left;
		}
		return n;
	}
	
	static class Node{
		int data;
		Node left;
		Node right;
		Node parent;
	}

}
