package com.z2.levelprint;

import java.util.LinkedList;
import java.util.Queue;

import com.z2.zigzagprint.Node;

public class PrettyPrint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = constructTestTree();
		levelPrint(root);
		System.out.println("height : " + maxHeight(root));
		prettyPrint(root);
		root = constructTestTree1();
		prettyPrint(root);
	}
	
	public static Node constructTestTree(){
		Node root = new Node(3);
		root.left = new Node(9);
		root.right = new Node(20);
		root.right.left = new Node(15);
		root.right.right = new Node(7);
		return root;
	}
	
	
	public static Node constructTestTree1(){
		Node root = new Node(30);
		root.left = new Node(20);
		root.right = new Node(40);
		root.left.left = new Node(10);
		root.left.right = new Node(25);
		root.left.left.left = new Node(5);
		root.left.left.right = new Node(15);
		root.left.right.right = new Node(28);
		root.right.left = new Node(35);
		root.right.right = new Node(50);
		root.right.right.left = new Node(41);
		return root;
	}
	/**
	 * print the tree nodes level by level.
	 * @param node
	 */
	public static void levelPrint(Node node){
		Node curNode = node;
		int counter = 1;
		int nlCounter = 0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(curNode);
		
		while(!q.isEmpty()){
			curNode = q.poll();
			System.out.print(curNode.data + " ");
			if(curNode.left != null){
				q.add(curNode.left);
				nlCounter++;
			}
			if(curNode.right != null){
				q.add(curNode.right);
				nlCounter++;
			}
			
			if(--counter ==0){
				System.out.println("");
				counter = nlCounter;
				nlCounter = 0;
			}
		}
		
	}
	
	public static void prettyPrint(Node node){
		int height = maxHeight(node);
		int indent;
		int start;
		int branch;
		int elemWidth = 2;
		
		Node curNode = node;
		Queue<Node> q = new LinkedList<Node>();
		q.add(curNode);
		StringBuilder sb = new StringBuilder();//keep track of the connecting arm.
		int counter = 1;
		int nlCounter = 0;
		int level = 1;
		while(!q.isEmpty()){
			curNode = q.poll();
			indent =  (int)Math.pow(2, height -level) -1 ; //number of elements to left.
			//start == number of elements to the left of branch.
			start = (int)Math.pow(2,  height-level-1);
			branch = indent -start;//branch width next to the elements.
			for(int i=0; i<start*elemWidth; i++){
				System.out.print(" ");
				sb.append(" ");
			}

			if(sb.length() > 0)
			sb.deleteCharAt(sb.length()-1);
			
			sb.append("/");
			for(int i=0; i< branch *elemWidth; i++){
				System.out.print("_");
				sb.append(" ");
			}
				
			
			if(curNode != null){
				System.out.printf("%2s", curNode.data);
				sb.append("  ");
			}else{
				System.out.printf("%2s", "  ");
				sb.append("  ");
			}
			
			for(int i=0; i< branch *elemWidth; i++){
				System.out.print("_");
				sb.append(" ");
			}
			sb.append("\\");
			for(int i=0; i<(indent + 1 -  branch)*elemWidth; i++){//indent + 1  add the middle element length.
				System.out.print(" ");
				sb.append(" ");
			}
			if(sb.length() > 0)
			sb.deleteCharAt(sb.length() -1);
			
			if(curNode != null){
				q.add(curNode.left);
				nlCounter ++;
				q.add(curNode.right);
				nlCounter ++;
			}
			
			if(--counter == 0){
				System.out.println("");
				if(level < height){//don't print connector for the bottom leaf nodes.
					System.out.println(sb.toString());
					sb.setLength(0);//clear the buffer.
				}
				counter = nlCounter;
				nlCounter = 0;
				level ++;
			}
		}
	}

	
	public static int maxHeight(Node node){
		if(node == null) return 0;
		int heightLeft = maxHeight(node.left);
		int heightRight = maxHeight(node.right);
		return Math.max(heightLeft, heightRight) + 1;
	}
}
