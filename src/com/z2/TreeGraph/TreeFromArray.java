package com.z2.TreeGraph;

public class TreeFromArray {

	
	public static Node toBinaryTree(int[] a, int start, int end){
		if(end < start){
			return null;
		}
		
		int mid = start + (end -start)/2;
		Node root = new Node(a[mid]);
		root.left = toBinaryTree(a, start, mid-1);
		root.right = toBinaryTree(a, mid+1, end);
		
		return root;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
		Node root = toBinaryTree(array, 0, array.length-1);
		
	}

}
