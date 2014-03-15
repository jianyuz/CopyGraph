package com.z2.TreeGraph;

/**
 * or calculate min and max depth separately.
 * 
 * @author zhouzhou
 *
 */
public class CheckTreeBalance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = testTree();
		System.out.println(isBalancedTree(root));
		System.out.println(isBalanced1(root));
	}
	
	public static Node testTree(){
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.right.left = new Node(6);
		return root;
	}
	
	public static boolean isBalanced1(Node root){
		return (maxDepth(root) - minDepth(root) <=1);
	}
	public static int maxDepth(Node root){
		if(root == null) return 0;
		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}
	
	public static int minDepth(Node root){
		if(root== null) return 0;
		return 1 + Math.min(minDepth(root.left), minDepth(root.right));
	}
	
	public static boolean isBalancedTree(Node root){
		MinMax mm = checkBalance(root);
		System.out.println(mm.maxD + " " + mm.minD);
		return (mm.maxD - mm.minD <=1);
	}
	public static MinMax checkBalance(Node root){
		if(root == null){
			return new MinMax(0, 0);
		}
		MinMax left = checkBalance(root.left);
		MinMax right = checkBalance(root.right);
		int min = Math.min(left.minD, right.minD) + 1;
		int max = Math.max(left.maxD, right.maxD)+ 1 ;
		return new MinMax(min, max);
		
	}
	static class MinMax{
		int minD;
		int maxD;
		public MinMax(int min, int max){
			this.minD = min;
			this.maxD = max;
		}
	}

}
