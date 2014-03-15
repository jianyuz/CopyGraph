package com.z2.t13;

import java.util.LinkedHashMap;
import java.util.Map;

import com.z2.t8.BNode;

public class ContructBinaryTree {
	//map binary tree node value to index in in order array.
	static Map<Integer, Integer> indexMap = new LinkedHashMap<Integer, Integer>();
	
	public static void main(String[] args){
		int[] preorder = {7, 10, 4, 3, 1, 2, 8, 11};
		int[] inorder = {4, 10, 3, 1, 7, 11, 8, 2};
		
		//value map to index.
		for(int i=0; i< inorder.length; i++){
			indexMap.put(inorder[i], i);
		}
		
		int len = preorder.length;
		BNode root = constructBTree(preorder, 0, inorder, 0,  len);
		System.out.println(root);
	}
	
	public static BNode constructBTree(int[] preorder, int preStart, int[] inorder, int inStart, int len){
		if(len == 0) return null;  //terminal condition
		BNode root = new BNode(preorder[preStart]);
		int inorderIndex = indexMap.get(preorder[preStart]);
		BNode left = constructBTree(preorder, preStart+1, inorder, inStart, inorderIndex - inStart);
		BNode right = constructBTree(preorder, preStart+1+ inorderIndex-inStart, inorder, inorderIndex + 1, len - (inorderIndex -inStart) -1);
		root.setLeft(left);
		root.setRight(right);
		return root;
	}

}
