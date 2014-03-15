package com.z2.sbst;

/**
 * find second largest element in BST.
 * 
 * find the largest element in BST.
 * remove it. then find the largest element again.
 * 
 * rebalance of the tree. too much trouble.
 * 
 * use a pointer to mark the parent.
 * second largest could be parent (no left node) or 
 * largest in the left child tree.
 * or doesn't exist.
 * 
 * reverse in-order traversal
 * 
 * @author zhouzhou
 *
 */
public class SecondLargestInBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = getTestTree();
		System.out.println(getSecondLargestEle(root, null).data);
		printKthLargestEle(root, 2, 0);

		root = getTestTree1();
		System.out.println(getSecondLargestEle(root, null).data);
		printKthLargestEle(root, 2, 0);


		root = getTestTree2();
		System.out.println(getSecondLargestEle(root, null).data);
		printKthLargestEle(root, 2, 0);

		root = getTestTree3();
		System.out.println(getSecondLargestEle(root, null) == null);
		printKthLargestEle(root, 2, 0);

		System.out.println(getSecondLargestEle(null, null) == null);
		printKthLargestEle(root, 2, 0);
	}
	
	public static Node getTestTree(){
		Node root = new Node(5);
		root.left = new Node(3);
		root.right = new Node(7);
		root.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		
		return root;
	}
	
	
	public static Node getTestTree1(){
		Node root = new Node(5);
		root.left = new Node(3);
		root.right = new Node(7);
		root.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(8);
		
		return root;
	}
	
	public static Node getTestTree2(){
		Node root = new Node(5);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(4);
	
		return root;
	}

	public static Node getTestTree3(){
		Node root = new Node(5);
		
		return root;
	}
	
	public static Node getSecondLargestEle(Node node, Node parent){
		if(node == null || node.left == null && node.right == null){
			return null;
		}
		
		while(node.right != null){ //important check node.right is empty. not the node iteself.
			parent = node;
			node = node.right;
		}//to the right most child node.
		
		if(node.left == null && parent != null){
			return parent;
		}else{
			return getLargest(node.left);
		}
		
	}

	private  static Node getLargest(Node node){
		while(node.right != null){ //same here. check node.right != null
			node = node.right;
		}
		return node;
	}
	
	/**
	 * reverse in-order travel, keep counter
	 * we may keep the count as the private data field.
	 * thus don't need to return any value in the method.
	 * @return
	 */
	public static int printKthLargestEle(Node node, int k, int count){
		if(node == null) return count;
		int leftCount = printKthLargestEle(node.right, k, count);
		if(leftCount + 1 == k){
			System.out.println(node.data);
			return Integer.MAX_VALUE; //terminate.
		}
		return printKthLargestEle(node.left, k, leftCount+1 );
	}
}
