package com.z2.t19;

import java.util.Stack;

public class PostTraversalIter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BNode root = buildTree();
		postTraversal(root);
		System.out.println();
		postTraversal2Stack(root);
	}
	
	public static BNode buildTree(){
		BNode cNode = new BNode("C");
		BNode eNode = new BNode("E");
		BNode dNode = new BNode("D");
		
		dNode.left = cNode;
		dNode.right = eNode;
		
		BNode aNode = new BNode("A");
		BNode bNode = new BNode("B");
		bNode.left = aNode;
		bNode.right = dNode;
		
		BNode hNode = new BNode("H");
		BNode iNode = new BNode("I");
		iNode.left = hNode;
		BNode gNode = new BNode("G");
		gNode.right = iNode;
		
		
		
		BNode root = new BNode("F");
		root.left = bNode;
		root.right = gNode;
		
		return root;
	}
	
	/**
	 * keep track of prev node.
	 * help check the traverse position.
	 * 
	 * @param root
	 */
	public static void postTraversal(BNode root){
		if(root == null) return;
		
		BNode curr = null, prev = null;
		Stack<BNode> stack = new Stack<BNode>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			curr = stack.peek();
			if(prev == null || prev.left == curr || prev.right == curr){ //moving down to left or right sub tree.
				if(curr.left != null){
					stack.push(curr.left);
				} else if(curr.right != null){
					stack.push(curr.right);
				}
				
			}else if(curr.left == prev){//moving up from left child, check right child
				if(curr.right != null){
					stack.push(curr.right);
				}
				
			}else {//moving up from right child
				System.out.print(curr.data);
				stack.pop();
			}
			prev = curr;
			
		}
	}
	
	/** two stacks.
	 * first do in order traversal 
	 * second reverse output it.
	 * @param root
	 */
	public static void postTraversal2Stack(BNode root){
		if(root == null) return;
		Stack<BNode> stack = new Stack<BNode>();
		Stack<BNode> out = new Stack<BNode>();
		
		BNode curr;
		stack.push(root);
		while(!stack.isEmpty()){
			curr = stack.pop();
			out.push(curr);
			if(curr.left != null){
				stack.push(curr.left);
			}
			if(curr.right != null){
				stack.push(curr.right);
			}
		}
		
		while(!out.isEmpty()){
			System.out.print(out.pop().data);
		}
	}
	
	

}
