package com.z2.t26;

public class ConvertBTDLinkList {
	
	public static void main(String[] args){
		BNode root = new BNode(4);
		BNode a = new BNode(2);
		BNode b = new BNode(5);
		root.left = a;
		root.right =  b;
		BNode a1 = new BNode(1);
		BNode b1 = new BNode(3);
		a.left = a1;
		a.right = b1;
		/*
		BNode[] nodes = convertBinaryTreeToDoubleLinkedList(root, new BNode[2]);
		
		BNode curNode = nodes[1];
		do{
			System.out.println(curNode.data + " ");
			curNode = curNode.right;
		}while(!curNode.equals(nodes[1]));
		
		System.out.println("==------");
	    curNode = nodes[1];
		do{
			System.out.println(curNode.data + " ");
			curNode = curNode.left;
		}while(!curNode.equals(nodes[1]));
		*/
		BNode res = convertBinaryTreeToDoubleLinkedList_Recursive(root);
		BNode curNode = res;
		do{
			System.out.println(curNode.data + " ");
			curNode = curNode.right;
		}while(!curNode.equals(res));
		
		System.out.println("==------");
		curNode = res;
	    do{
			System.out.println(curNode.data + " ");
			curNode = curNode.left;
		}while(!curNode.equals(res));
		
	}
	
	/**
	 * in order traversal.
	 * modified version.
	 * 
	 * @param root
	 * @return
	 */
	public static BNode[] convertBinaryTreeToDoubleLinkedList(BNode curNode, BNode[] nodes){
		if(curNode == null){
			return nodes;
		}
		
		
		nodes= convertBinaryTreeToDoubleLinkedList(curNode.left, nodes);
		
		//nodes[0] is the prev, nodes[1] is the head
		
		BNode prev, head;
		prev = nodes[0];
		head = nodes[1];
		if(prev == null){
			head =  curNode;
		}else{
			curNode.left = prev;
			prev.right = curNode;
			
		}
		
		//current node and head
		BNode rightNode = curNode.right;//remember before changing it.
		head.left = curNode;
		curNode.right = head;
		
		
		//update prev
		prev = curNode;
		nodes[0] = prev;
		nodes[1] = head;
		
		nodes = convertBinaryTreeToDoubleLinkedList(rightNode, nodes);
		
		return nodes;
	}
	
	
	
	/*
	 * recursive version
	 * natural node appending.
	 * 
	 */
	public static BNode convertBinaryTreeToDoubleLinkedList_Recursive(BNode curNode){
		
		if(curNode == null){
			return null;
		}
		
		BNode aList = convertBinaryTreeToDoubleLinkedList_Recursive(curNode.left);
		BNode bList = convertBinaryTreeToDoubleLinkedList_Recursive(curNode.right);
		// only make it one item list after we used the left and right pointer.
		
		curNode.left = curNode;// one node double linked list.
		curNode.right = curNode;// one node double linked list.

		
		
		
		aList = appendList(aList, curNode);
		
		return appendList(aList, bList);
		
	}
	
	public static BNode appendList(BNode aList, BNode bList){
		if(aList == null){
			return bList;
		}
		if(bList == null){
			return aList;
		}
		BNode aTail = aList.left;
		BNode bTail = bList.left;
		aTail.right = bList;
		bList.left = aTail;
		
		bTail.right = aList;
		aList.left = bTail;
		
		//complet the join b tail needs to link to a head.
		return aList;
	}

}
