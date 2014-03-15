package com.z2.sbst;

public class Node {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	int data;
	Node left;
	Node right;
	
	Node(int data){
		this.data = data;
	}
	
	Node(int data, Node left, Node right){
		this.data = data;
		this.left = left;
		this.right = right;
	}

}
