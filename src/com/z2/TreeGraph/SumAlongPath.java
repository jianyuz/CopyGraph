package com.z2.TreeGraph;

import java.util.ArrayList;

/**
 * find path that sums all the node get the value of sum.
 * clone arrayList at end level
 * at each level back trace to see if any sum == 0
 * O(n lgn) space and time complexity.
 * 
 * @author zhouzhou
 *
 */
public class SumAlongPath {

	
	public static void sumAlongPath(Node node, int sum, ArrayList<Integer> list, int level){
		if(node == null){
			return;
		}
		list.add(node.data);
		int lSum = sum;
		for(int i= level; i>=0; i--){
			lSum -= list.get(i);
			if(lSum == 0){
				printSum(list, i, level);
			}
		}
		ArrayList<Integer> left = (ArrayList<Integer>)(list.clone());
		ArrayList<Integer> right = (ArrayList<Integer>)list.clone();
		sumAlongPath(node.left, sum, left, level +1);
		sumAlongPath(node.right, sum, right, level +1);
		return;
	}
	
	public static void printSum(ArrayList<Integer> l, int s, int e){
		for(int i = s; i<=e; i++){
			System.out.print(l.get(i) + " ");
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = testTree();
		sumAlongPath(root, 4, new ArrayList(), 0 );
	}
	
	public static Node testTree(){
		Node root = new Node(2);
		root.left = new Node(3);
		root.right = new Node(-1);
		root.left.left = new Node(1);
		root.left.right = new Node(-1);
		root.right.left = new Node(5);
		return root;
	}

}
