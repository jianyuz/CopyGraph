package memoryVisibility;

public class CheckTreeBalance {
	public static void main(String[] args){
		Node root = testTree();
		Res res = isBalanced(root, 0);
		System.out.println("is Balanced " + res.isBalanced);
	}
	
	public static Node testTree(){
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.left.left= new Node(6);
		return root;
		
	}
	public static Res isBalanced(Node root, int depth){
		if(root == null)
			return new Res(true, depth, depth);
		
		depth = depth +1;
		Res leftRes = isBalanced(root.left, depth);
		Res rightRes= isBalanced(root.right, depth);
		
		int min = leftRes.min < rightRes.min? leftRes.min: rightRes.min;
		int max = leftRes.max > rightRes.max? leftRes.max: rightRes.max;
		boolean isBalanced = (max - min) <= 1 && leftRes.isBalanced && rightRes.isBalanced;
		return new Res(isBalanced, min, max);

		
	}
	
	static class Res{
		boolean isBalanced;
		int min;
		int max;
		
		public Res(boolean balanced, int min, int max){
			this.isBalanced = balanced;
			this.min = min;
			this.max = max;
		}
	}
	static class Node{
		int data;
		Node left;
		Node right;
		
		public Node(int data){
			this.data = data;
		}
	}
	
	//alternative get all the depth then find the differences.
	//
	

}
