package com.z2.t27;

public class ConvertSortedArrayToBST {
	public static void main(String[] args){
		int[] sorted ={ 1, 3, 5, 6, 7, 8, 10};
		BNode root = convertArrayToBST(sorted, 0, sorted.length-1);
		printBNode(root);
		
		System.out.println("----");
		int[] sorted5 ={ 1, 3, 5, 6};
		BNode root5 = convertArrayToBST(sorted5, 0, sorted5.length-1);
		printBNode(root5);
		
		System.out.println("----");
		int[] sorted1 ={ 1, 3};
		BNode root1 = convertArrayToBST(sorted1, 0, sorted1.length-1);
		printBNode(root1);
		System.out.println("----");
		int[] sorted2 ={ 1};
		BNode root2 = convertArrayToBST(sorted2, 0, sorted2.length-1);
		printBNode(root2);
		System.out.println("----");
		int[] sorted3 ={};
		BNode root3 = convertArrayToBST(sorted3, 0, sorted3.length-1);
		printBNode(root3);
		System.out.println("----");
		int[] sorted4 ={1, 3, 5};
		BNode root4 = convertArrayToBST(sorted4, 0, sorted4.length-1);
		printBNode(root4);
	}
	
	/**
	 * return the root node of the balanced search tree.
	 * input is the sorted int array.
	 * @param sorted
	 * @return
	 */
	public static BNode convertArrayToBST(int[] sorted, int start, int end){
		if(sorted == null || sorted.length == 0) return null;
		
		if(end == start){
			return new BNode(sorted[start]);
		}
		if(end - start == 1){
			BNode child = new BNode(sorted[start]);
			BNode parent = new BNode(sorted[end]);
			parent.left = child;
			return parent;
		}
		
		int mid = start + (end-start)/2;
		
		BNode left = convertArrayToBST(sorted, start, mid-1);
		BNode right = convertArrayToBST(sorted, mid+1, end);
		
		BNode midParent = new BNode(sorted[mid]);
		midParent.left = left;
		midParent.right = right;
		return midParent;
	}
	
	public static void printBNode(BNode root){
		if(root == null) return;
		printBNode(root.left);
		System.out.println(root.data);
		printBNode(root.right);
	}

}
