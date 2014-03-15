package com.z2.t8;

/**
 * Binary search tree node.
 * @author zhouzhou
 *
 */
public class BNode {

	int data;
	BNode left;
	BNode right;
	
	public BNode(int data){
		this(data, null, null);
	}
	
	public BNode(int data, BNode left, BNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public BNode getLeft() {
		return left;
	}
	public void setLeft(BNode left) {
		this.left = left;
	}
	public BNode getRight() {
		return right;
	}
	public void setRight(BNode right) {
		this.right = right;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(left != null && right != null){
			sb.append("[" + left.toString() + "]").append(data).append("[" + right.toString() + "]");
		}else if(left == null && right != null){
			sb.append("[]").append(data).append("[" + right.toString() + "]");
		}else if(right == null && left != null){
			sb.append("[" + left.toString() + "]").append(data).append("[]");
		}else {
			sb.append(data);
		}
		return sb.toString();
	}

}
