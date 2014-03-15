package com.z2.TreeGraph;

/**
 * check if one tree contains another subtree.
 * O(m*n)
 * @author zhouzhou
 *
 */
public class ContainTree {

	public boolean containsTree(Node t1, Node t2){
		if(t1== null) return false;
		if(t2 == null) return true;
		boolean res = false;
		if(t1.data == t2.data){
			res = matchTree(t1, t2);
		}
		if(!res){
			return containsTree(t1.left, t2) || containsTree(t1.right, t2);
		}else{
			return true;
		}
	}
	private boolean matchTree(Node t1, Node t2) {
		if(t1==null && t2==null) return true;
		if(t1==null || t2 == null) return false;
		if(t1.data != t2.data ) return false;
		
		return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
