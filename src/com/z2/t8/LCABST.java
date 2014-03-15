package com.z2.t8;

/**
 * lowest common ancestor of binary search tree.
 * 
 * @author zhouzhou
 *
 */
public class LCABST {

	static int[] bstData = new int[]{6, 2, 8, 0, 4, 7, 9, 3, 5};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BNode root = constructBST();
		System.out.println(root);
		
		BNode a = new BNode(4);
		BNode b = new BNode(2);
		BNode res = lcabst(root, a, b);
		System.out.println("LCA is " + res.getData());
		
		a = new BNode(3);
		b = new BNode(7);
		res = lcabst(root, a, b);
		System.out.println("LCA is " + res.getData());

		a = new BNode(3);
		b = new BNode(5);
		res = lcabst(root, a, b);
		System.out.println("LCA is " + res.getData());
		
		a = new BNode(0);
		b = new BNode(8);
		res = lcabst(root, a, b);
		System.out.println("LCA is " + res.getData());
	}
	
	/**
	 * compare the value of the current mid node with left and right data value.
	 * if current data equals to the value a or b that's the lca.
	 * or it is between the value of a and b.
	 * it is lca.
	 * if cur.value greater than both.
	 * traverse left
	 * or smaller than both
	 * traverse right
	 * 
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static BNode lcabst(BNode root, BNode a, BNode b){
		int aValue = a.getData();
		int bValue = b.getData();
		
		BNode cur = root;
		while(cur != null){
			if(cur.getData() == aValue || cur.getData() == bValue ||
					(cur.getData() > aValue && cur.getData() < bValue) ||
					(cur.getData() < aValue && cur.getData() > bValue)){
				return cur;
			}
			if(cur.getData() > aValue && cur.getData() > bValue){
				cur = cur.getLeft();
			}
			if(cur.getData() < aValue && cur.getData() <bValue){
				cur = cur.getRight();
			}
			break;
		}
		return cur;
	}
	
	public static BNode constructBST(){
		BNode bst = null;
		
		for(int nodeData: bstData){
			if(bst == null){
				bst = new BNode(nodeData);
			}else{
				BNode cNode = bst;
				
				while (true) {
					// compare with node
					if (nodeData <= cNode.getData() && cNode.getLeft() != null) {
						cNode = cNode.getLeft();
					} else if (nodeData > cNode.getData()
							&& cNode.getRight() != null) {
						cNode = cNode.getRight();
					} else {
						break;
					}

				}
				
				if(nodeData <= cNode.getData()){
					cNode.setLeft(new BNode(nodeData));
				}else{
					cNode.setRight(new BNode(nodeData));
				}
				
			}
		}
		
		return bst;
	}

}
