package com.z2.serializeBT;

public class Node {
	public String data;
	public Node left;
	public Node right;
	
	public Node(String data){
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public Node(int data){
		this("" + data);
	}
	
}
