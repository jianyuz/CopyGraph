package com.z2.deepestFatherOfTwo;

/**
 * find deepest father node with childrens in the tree.
 * 
 * @author zhouzhou
 *
 */
public class DeepestFatherOfTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = buildTestTree1();
		FatherNode node = findFather(root);
		if(node.fatherNode == null){
			System.out.println("no father node");
		}else
			System.out.println("deepest father node with two kids " + node.fatherNode.data);
	}
	
	public static Node buildTestTree(){
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.left.right = new Node(4);
		root.left.left.left = new Node(5);
		root.left.left.left.left = new Node(6);
		root.left.left.left.right = new Node(7);
		root.right = new Node(8);
		root.right.left = new Node(9);
		root.right.right = new Node(10);
		root.right.left.left = new Node(11);
		return root;
	}
	
	public static Node buildTestTree1(){
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.left.left.left = new Node(5);
		root.left.left.left.left = new Node(6);
		return root;
	}
	
	public static FatherNode findFather(Node node){
		if(node == null){
			return new FatherNode();
		}
		
		FatherNode resLeft = findFather(node.left);
		
		FatherNode resRight = findFather(node.right);
		
		FatherNode res = compareDepth(resLeft, resRight);
		
		if(node.left != null && node.right != null & res.fatherNode == null){
			res.fatherNode = node;
		}
			
		res.depth ++;
		return res;
		
	}
	
	private static FatherNode compareDepth (FatherNode f1, FatherNode f2){
		if(f1.fatherNode != null &&  f2.fatherNode != null){
			if(f1.depth > f2.depth){
				return f1;
			}else{
				return f2;
			}
		}else if(f1.fatherNode != null || f2.fatherNode != null){
			return (f1.fatherNode != null)? f1: f2;
		}else{
			return new FatherNode(null, f1.depth > f2.depth? f1.depth: f2.depth);
		}
		
	}
	private final static class FatherNode{
		Node fatherNode;
		int depth;
		
		FatherNode(){
			this(null, 0);
		}
		
		FatherNode(Node node, int depth){
			this.fatherNode = node;
			this.depth = depth;
		}
	}
	
	private static final class Node{
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this(data, null, null);
		}
		
		Node(int data, Node left, Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

}
