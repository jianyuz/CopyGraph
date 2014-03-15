package com.z2.TreeGraph;

public class BNode {

	int data;
	BNode left;
	BNode right;
	BNode parent;
	
	public BNode(int data){
		this.data = data;
	}
	
	public void setLeft(BNode left){
		this.left = left;
		left.parent = this;
	}
	
	public void setRight(BNode right){
		this.right = right;
		right.parent = this;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
