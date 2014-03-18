package memoryVisibility;

public class BinaryFromArray {

	public static void main(String[] args){
		int[] input = new int[]{1,2,3,4,5,6,7};
		
		Node root = binaryTree(input);
		
		preOrder(root);
	}
	
	public static void inOrder(Node root){
		if(root == null) return;
		inOrder(root.left);
		System.out.println(root.data);
		inOrder(root.right);
	}
	
	public static void preOrder(Node root){
		if(root == null) return;
		
		System.out.println(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public static Node binaryTree(int[] input){
		return doBinaryTree(input, 0, input.length -1);
	}
	
	public static Node doBinaryTree(int[] input, int l, int r){
		if(l > r){
			return null;
		}
		if(l==r){
			return new Node(input[l]);
		}
		
		int m = l + (r-l)/2;
		Node node = new Node(input[m]);
		
		Node left = doBinaryTree(input, l, m-1);
		Node right = doBinaryTree(input, m+1, r);
		
		node.left = left;
		node.right = right;
		return node;
	}
	
	static class Node{
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
		}
	}
}
