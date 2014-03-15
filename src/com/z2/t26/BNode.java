package com.z2.t26;

public class BNode {
	
	public BNode left;
	public BNode right;
	public int data;

	public BNode(int data){
		this(data, null, null);
	}
	
	public BNode(int data, BNode left, BNode right){
		this.left = left;
		this.right = right;
		this.data = data;
	}
}
