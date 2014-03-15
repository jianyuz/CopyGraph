package com.z2.zigzagprint;

import java.util.ArrayDeque;
import java.util.Deque;

public class Zigzag {
	
	public static void main(String[] args){
		Node root = constructTestTree();
		zigzagPrint(root);
	}

	public static Node constructTestTree(){
		Node root = new Node(3);
		root.left = new Node(9);
		root.right = new Node(20);
		root.right.left = new Node(15);
		root.right.right = new Node(7);
		return root;
	}
	
	/**
	 * use double ended queue
	 * add to either end depending on the level 
	 * 
	 * @param node
	 */
	public static void zigzagPrint(Node node){
		if(null == node) return;
		
		Node curNode = node;
		Deque<Node> dq = new ArrayDeque<Node>();
		int level = 1;
		int count = 1;
		int nextLevelCount = 0;
		dq.addFirst(curNode);
		
		while(!dq.isEmpty()){
			if(level%2 == 1){
				curNode = dq.removeFirst();
				System.out.print(curNode.data + " ");
				//from left to right. add to the end of double ended queue.
				if(curNode.left != null){
					dq.addLast(curNode.left);
					nextLevelCount ++;
				}
				if(curNode.right != null){
					dq.addLast(curNode.right);
					nextLevelCount ++;
				}
				
			}else{
				curNode =dq.removeLast();
				System.out.print(curNode.data + " ");
				//from right to left. add to the start of the double ended queue.
				if(curNode.right != null){
					dq.addFirst(curNode.right);
					nextLevelCount ++;
				}
				if(curNode.left != null){
					dq.addFirst(curNode.left);
					nextLevelCount ++;
				}
			}
			if(--count ==0){
				level ++;
				count = nextLevelCount;
				nextLevelCount = 0;
				System.out.println("");
			}
		}
		
		
	}
}
