package com.z2.t20;

public class EdgeNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BNode root = initTree();
		edgeNode(root);
	}
	
	public static BNode initTree(){
		BNode node5 = new BNode(5);
		BNode node15 = new BNode(15);
		BNode node10 = new BNode(10);
		node10.left = node5;
		node10.right = node15;
		BNode node28 = new BNode(28);
		BNode node25 = new BNode(25);
		BNode node20 = new BNode(20);
		node25.right = node28;
		node20.left = node10;
		node20.right = node25;
		
		BNode node35 = new BNode(35);
		BNode node40 = new BNode(40);
		node40.left = node35;
		BNode node50 = new BNode(50);
		BNode node41 = new BNode(41);
		node50.left = node41;
		node40.right = node50;
		
		BNode node30 = new BNode(30);
		node30.left = node20;
		node30.right = node40;
		
		return node30;
		
	}

	/**
	 * print the edge node of a tree.
	 */
	public static void edgeNode(BNode root){
		if(root == null) return;
		System.out.print(root.data + " ");
		leftTree(root.left, true);
		rightTree(root.right, true);
	}
	
	public static void leftTree(BNode root, boolean print){
		if(root == null) return;
		if(print || (root.left == null && root.right == null)){
			System.out.print(root.data + " " );
		}
		leftTree(root.left, (print && root.right != null)?true:false);
		leftTree(root.right, false);
	}
	
	public static void rightTree(BNode root, boolean print){
		if(root == null) return;
		rightTree(root.left, false);
		rightTree(root.right, (print && root.left != null)?true: false);
		if(print || (root.left == null && root.right == null)){
			System.out.print(root.data + " ");
		}
		
	}
}
