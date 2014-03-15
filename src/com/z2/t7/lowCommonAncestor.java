package com.z2.t7;

public class lowCommonAncestor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node a8 = new Node(8);
		Node a6 = new Node(6);
		Node a5 = new Node(5);
		Node a4 = new Node(4);
		Node a1 = new Node(1);
		
		Node root = initTestBinary();
		//int count = matchCount(root, a5, a4);
		//System.out.println(count);
		
		Node res = topdown(root, a5, a4);
		System.out.println("LCA is: " + res);

		res = topdown(root, a6, a4);
		System.out.println("LCA is: " + res);
		
		res = topdown(root, a8, a4);
		System.out.println("LCA is: " + res);
		
		res = topdown(root, a8, null);
		System.out.println("LCA is: " + res);
		
		System.out.println("---------------");
		
		res = bottomUp(root, a5, a4);
		System.out.println("LCA is: " + res);

		res = bottomUp(root, a6, a4);
		System.out.println("LCA is: " + res);
		
		res = bottomUp(root, a8, a4);
		System.out.println("LCA is: " + res);
		
		res = bottomUp(root, a8, null);
		System.out.println("LCA is: " + res);

		res = bottomUp(root, a5, a1);
		System.out.println("LCA is: " + res);
	}
	
	public static Node initTestBinary(){
		Node a7 = new Node(7);
		Node a4 = new Node(4);
		Node a2 = new Node(2);
		a2.setlChild(a7);
		a2.setrChild(a4);
		Node a6 = new Node(6);
		Node a5 = new Node(5);
		a5.setlChild(a6);
		a5.setrChild(a2);
		
		Node a0 = new Node(0);
		Node a8 = new Node(8);
		Node a1 = new Node(1);
		a1.setlChild(a0);
		a1.setrChild(a8);
		
		Node a3 = new Node(3);
		a3.setlChild(a5);
		a3.setrChild(a1);
		
		return a3;
	}
	/**
	 * pass up matched node.
	 * recursive solution.
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static Node bottomUp(Node root, Node a, Node b){
		if(root == null || a == null || b == null){
			return null;
		}
		if(root.equals(a) || root.equals(b)){
			return root; //pass up the matched code. if both side matched code exists. the root is lca.
		}
		Node lRes = bottomUp(root.getlChild(), a, b);
		Node rRes = bottomUp(root.getrChild(), a, b);
		
		if(lRes != null && rRes != null){ return root;}
		
		return (lRes != null)?lRes:rRes; //include null here.
	}
	
	/**
	 * too costly.
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static Node topdown(Node root, Node a, Node b){
		if(root == null || a== null || b== null) {
			return null;
		}
		
		if(root.equals(a) || root.equals(b)) {
			return root;
		}
	
		int countLeft  = matchCount(root.getlChild(), a, b);
		
		if(countLeft == 2){
			return topdown(root.getlChild(), a, b);
		}else if(countLeft == 1){
			return root;
		}else { //match == 0
			return topdown(root.getrChild(), a, b);
		}
		
	}
	
	/**
	 * count the matched node under the node root.
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static int matchCount(Node root, Node a, Node b){
		if(root == null) return 0;
		int count = matchCount(root.getlChild(), a, b) + matchCount(root.getrChild(), a, b);
		if(root.equals(a)) {count ++;}
		if(root.equals(b)) {count ++;}
		return count;
	}
	
	


}
