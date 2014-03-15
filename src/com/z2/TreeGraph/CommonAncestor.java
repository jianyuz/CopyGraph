package com.z2.TreeGraph;


public class CommonAncestor {

	
	public static Node ancestor(Node root, Node n1, Node n2){
		if(root == null || n1== null || n2==null){
			return null;
		}
		
		if(root == n1 || root == n2){
			return root;
		}//return matched node.
		
		Node left = ancestor(root.left, n1, n2);
		Node right = ancestor(root.right, n1, n2);
		if(left!=null && right!=null){
			return root;
		}else if(left != null){
			return left;
		}else if(right != null){
			return right;
		}
		
		return null;
		
	}
	static Node a1, a4, a5, a6, a8;
	
	public static Node initTestBinary(){
		Node a7 = new Node(7);
		a4 = new Node(4);
		Node a2 = new Node(2);
		a2.left = a7;
		a2.right =a4;
		a6 = new Node(6);
		a5 = new Node(5);
		a5.left =a6;
		a5.right =a2;
		
		Node a0 = new Node(0);
		a8 = new Node(8);
		a1 = new Node(1);
		a1.left = (a0);
		a1.right = a8;
		
		Node a3 = new Node(3);
		a3.left =(a5);
		a3.right = (a1);
		
		return a3;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node root = initTestBinary();
		
		Node res;
		
		res = ancestor(root, a5, a4);
		System.out.println("LCA is: " + res.data);

		res = ancestor(root, a6, a4);
		System.out.println("LCA is: " + res.data);
		
		res = ancestor(root, a8, a4);
		System.out.println("LCA is: " + res.data);
		
		res = ancestor(root, a8, null);
		System.out.println("LCA is: " + ((res == null)?null:res.data));

		res = ancestor(root, a5, a1);
		System.out.println("LCA is: " + res.data);
	}

}
