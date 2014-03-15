package com.z2.TreeGraph;

public class InOrderNext {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BNode root = testTree();
		System.out.println(inOrderNext(root).data);
		System.out.println(inOrderNext(root.right).data);
		System.out.println(inOrderNext(root.right.right));
		System.out.println(inOrderNext(root.left.left).data);
		System.out.println(inOrderNext(root.left.left.left).data);
		System.out.println(inOrderNext(root.left.right).data);
	}
	
	public static BNode testTree(){
		BNode root = new BNode(1);
		root.setLeft(new BNode(2));
		root.setRight(new BNode(3));
		root.right.setLeft(new BNode(8));
		root.right.setRight(new BNode(9));
		root.left.setLeft(new BNode(4));
		root.left.setRight(new BNode(5));
		root.left.left.setLeft(new BNode(6));
		root.left.left.setRight(new BNode(7));
		return root;
	}
	
	public static BNode inOrderNext(BNode node){
		if(node == null) return null;
		if(node.right != null){
			return leftMost(node.right);
		}else{//right child is empty.
			if(node.parent != null && node.parent.left == node){
				return node.parent;
			}else{//finish the subtree under parent.
				//traverse back to top.until we are the left child
				BNode top = node;
				while(top.parent != null){
					if(top.parent.left == top){
						return top.parent;
					}
					top = top.parent;
				}
			}
		}
		return null;
	}

	private static BNode leftMost(BNode right) {
		if(right == null) return null;
		BNode res = right;
		while( res.left != null){
			res = res.left;
		}
		return res;
	}

}
