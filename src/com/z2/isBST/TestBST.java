package com.z2.isBST;

import com.z2.zigzagprint.Node;

public class TestBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isBST(constructTestTree(), Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println(isBST(constructTestTree1(), Integer.MIN_VALUE, Integer.MAX_VALUE));
		
	}
	
	public static Node constructTestTree(){
		Node root = new Node(3);
		root.left = new Node(9);
		root.right = new Node(20);
		root.right.left = new Node(15);
		root.right.right = new Node(7);
		return root;
	}
	
	public static Node constructTestTree1(){
		Node root = new Node(10);
		root.left = new Node(5);
		root.right = new Node(15);
		root.right.left = new Node(12);
		root.right.right = new Node(20);
		return root;
	}
	
	public static boolean isBST(Node root, int min, int max){
		if(root == null) return true;
		if(root.data >= min && root.data <= max){
			return isBST(root.left, min, root.data ) &&
					isBST(root.right, root.data, max);
		}
		return false;
	}

}
