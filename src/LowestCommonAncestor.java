import java.util.HashSet;


public class LowestCommonAncestor {

	/**
	 * lowest Common Ancestor of a binary search tree.
	 * Given root r, Node a and Node b.
	 * 
	 * 
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5

	 * @author zhouzhou
	 *
	 */
	
	static Node testTree(){
		Node root = new Node(6);
		root.left = new Node(2);
		root.right = new Node(8);
		root.left.left = new Node(0);
		root.left.right = new Node(4);
		root.left.right.left = new Node(3);
		root.left.right.right = new Node(5);
		
		root.right.left = new Node(7);
		root.right.right = new Node(9);
		return root;
	}
	
	/**
	 * it is simply a binary tree now
	 * 
	 *  _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
	 * @param args
	 */
	
	public static Node testTree2(){
		Node root = new Node(3);
		root.left = new Node(5);
		root.right = new Node(1);
		root.left.left = new Node(6);
		root.left.right = new Node(2);
		root.left.right.left = new Node(7);
		root.left.right.right = new Node(4);
		root.right.left = new Node(0);
		root.right.right = new Node(8);
		return root;
	}
	
	/**
	 * recursive to find the match element.
	 * propogate found element back
	 * based on the found element to determine which is the lowest ancestor.
	 * all the back to the root top.
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static Node findAncestorBTree(Node root, Node a, Node b){
		if(root == null || a == null || b== null)return null;
		if(root.val == a.val || root.val == b.val) return root; //found the match.
		Node left = findAncestorBTree(root.left, a, b);
		Node right = findAncestorBTree(root.right, a, b);
		if(left == null) return right; //only find left.
		if(right == null) return left; //only find right;
		if(left != null && right != null) return root;
		return null;
	}
	
	/**
	 *  _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
         with parent pointer
	 * @param args
	 */
	
	static class PNode{
		int val;
		PNode parent;
		PNode(int v){
			this.val = v;
		}
	}
	
	static PNode n7,n6, n5, n8;
	/**
	 * binary tree with parent pointer.
	 * find common ancestor of two.
	 */
	public static void testTree3(){
		n6 = new PNode(6);
		n5 = new PNode(5);
		n6.parent = n5;
		PNode n2 = new PNode(2);
		n7 = new PNode(7);
		n7.parent = n2;
		n2.parent = n5;
		PNode n3 = new PNode(3);
		n5.parent = n3;
		PNode n0 = new PNode(0);
		n8 = new PNode(8);
		PNode n1 = new PNode(1);
		n0.parent = n1;
		n8.parent = n1;
		n1.parent = n3;
	}
	
	public static void main(String[] args){
		Node root = testTree();
		Node a = new Node(2);
		Node b = new Node(3);
		//tree algoirhtm recursion.
		//breath first
		//depth first.
		//recursive counting.
		//recurisive return of previous target.
		Node res = findAncestor(root, a, b);
		System.out.println(res.val);
		
		root = testTree2();
		a = new Node(4);
		b = new Node(7);
		res = findAncestorBTree(root, a, b);
		System.out.println(res.val);
		
		testTree3();
		//find the common ancestor of n6, n4.
		PNode res1 = findPAncestor(n6, n8);
		System.out.println(res1.val);
		
		res1 = findPAncestor1(n6, n7);
		System.out.println(res1.val);
	}
	
	/**
	 * use a hashset to track if a element has been visited in a path.
	 * O(h) complexity and O(h) space complexity.
	 * @param a
	 * @param b
	 * @return
	 */
	public static PNode findPAncestor1(PNode a, PNode b){
		HashSet<Integer> s = new HashSet<Integer>();
		while(a != null || b != null){
			if(a != null){
				if(s.contains(a.val)){
					return a;
				}
				s.add(a.val);
				a = a.parent;
			}
			if(b != null){
				if(s.contains(b.val)){
					return b;
				}
				s.add(b.val);
				b= b.parent;
			}
		}
		return null;
	}
	public static PNode findPAncestor(PNode a, PNode b){
		if(a == null || b== null) return null;
		int da = findDepth(a);
		int db = findDepth(b);
		
		PNode ln = null, sn= null;
		if(da > db){
			ln = a;
			sn = b;
		}else{
			ln = b;
			sn = a;
		}
		
		int diff = Math.abs(da -db);
		System.out.println("diff " + diff);
		int count = 0;
		while(count < diff){
			ln = ln.parent;
			count ++;
		}
		
		while(ln != null && sn != null){
			if(ln == sn) return ln;
			ln = ln.parent;
			sn = sn.parent;
		}
		
		return null;
	}
	
	private static int findDepth(PNode a){
		if(a == null) return 0;
		
		return findDepth(a.parent) + 1;
	}
	
	/**
	 * common ancestor.
	 * if one of the nodes matches it is the common ancestor.
	 * if one is left one is right. then the current node is ancestor.
	 * if both node's value are smaller.
	 * search left.
	 * if both node's value are bigger.
	 * search right.
	 * 
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static Node findAncestor(Node root, Node a, Node b){
		if(root == null || a == null || b== null) return null;
		if(root.val == a.val || root.val == b.val){
			return root;
		}
		if(a.val < root.val && b.val > root.val){
			return root;
		}
		if(b.val < root.val && a.val > root.val){
			return root;
		}
		
		if(Math.max(a.val, b.val) < root.val){
			return findAncestor(root.left, a, b);
		}
		
		if(Math.min(a.val,  b.val) > root.val){
			return findAncestor(root.right, a, b);
		}
		return null;
	}
	
	static class Node{
		int val;
		Node left;
		Node right;
		
		Node(int val){
			this.val = val;
			left = null;
			right = null;
		}
	}
}
