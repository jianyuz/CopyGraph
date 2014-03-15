package com.z2.TreeGraph;

import java.util.ArrayList;
import java.util.LinkedList;


public class LevelTree {

	public static ArrayList<LinkedList<Node>> levelTreeList(Node root){
		if(root == null){
			return null;
		}
		int level = 0;
		ArrayList<LinkedList<Node>> res = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(root);
		res.add(level, list);
		
		while (true) {
			list = new LinkedList<Node>();
			for (Node n : res.get(level)) {
				if (n != null) {
					if (n.left != null) {
						list.add(n.left);
					}
					if (n.right != null) {
						list.add(n.right);
					}
				}
			}
			if (list.size() > 0) {
				res.add(level + 1, list);
			}else{
				break;
			}
			level ++;
		}
		return res;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = testTree();
		ArrayList<LinkedList<Node>> res = levelTreeList(root);
		System.out.println(res.size());
	}
	
	public static Node testTree(){
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		return root;
	}

}
