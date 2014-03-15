package com.z2.closestPointInBST;


public class ClosestPointInBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = testTree();
		System.out.println(closestPTInBST(root, 22, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println(closestPTInBST(root, 6, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println(closestPTInBST(root, 10, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println(closestPTInBST(root, 37, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println(closestPTInBST(null, 37, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
	
	public static Node testTree(){
		Node root = new Node(20);
		root.left = new Node(10);
		root.right = new Node(30);
		root.left.left = new Node(5);
		root.left.right = new Node(15);
		root.right.left = new Node(24);
		root.right.right = new Node(35);
		return root;
	}
	
	public static int closestPTInBST(Node node, int input, int min, int max){
		if(node == null){
			int a = Math.abs(input - min);
			int b = Math.abs(input- max);
			if(a < b){
				return min;
			}else{
				return max;
			}
		}
		
		if(node.data == input){
			return node.data;
		}else if(input < node.data){
			return closestPTInBST(node.left, input, min, node.data);
		}else{
			return closestPTInBST(node.right, input, node.data, max);
		}
	}

}
