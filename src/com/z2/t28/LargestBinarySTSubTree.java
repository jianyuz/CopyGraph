package com.z2.t28;

/**
 * native approach, check if each node is a binary search tree.
 * if root is not.
 * then check left and right children.
 * if both are
 * compare their size.
 * the run time complexity is O(nLogn)
 * O(n) is check if a root is BST.
 * traverse all node at different level of the trees
 * 
 * isBST,
 * left tree key < than parent
 * right tree key > than parent.
 * left and right are also binary tree.
 * takes o(n) 
 * 
 * @author zhouzhou
 *
 */
public class LargestBinarySTSubTree {
	
	public static void main(String[] args){
		
	}
	
	public static SubtreeRes largestBST(Node node, SubtreeRes res){
		
		return null;
	}
	
	class SubtreeRes{
		public Node node;
		public int min;
		public int max;
	}

}
