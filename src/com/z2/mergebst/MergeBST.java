package com.z2.mergebst;

public class MergeBST {
	public static void main(String[] args){
		Node root = testTree();
		Node endList = convertToLinkedList(root, null);
		//head is the end of the list. biggest element.
		printList(endList);
		System.out.println();
		Node root1 = testTree1();
		Node endList1 = convertToLinkedList(root1, null);
		printList(endList1);
		Node mergedList = mergeLists(endList, endList1);
		System.out.println("\nSize " + size);
		printList1(mergedList);
		root = convertToBST(mergedList, 1, 10).root;
		System.out.println("\nprint BST\n");
		printBST(root);
	}
	
	public static void printBST(Node root){
		if(root == null) return;
		printBST(root.left);
		System.out.print(root.data + " " );
		printBST(root.right);
	}
	public static void printList(Node list){
		do{
			System.out.print(list.data + " ");
		}while((list = list.left) != null);
	}
	
	public static void printList1(Node list){
		do{
			System.out.print(list.data + " ");
		}while((list = list.right) != null);
		System.out.println();
	}
	static int size = 0;
	public static Node mergeLists(Node node, Node node1){
		Node end = null;
		while(node != null && node1 != null){
			if(node.data > node1.data){
				if(end != null)
				node.right = end;
				end = node;
				node = node.left;
			}else{
				if(end != null)
				node1.right = end;
				end = node1;
				node1 = node1.left;
			}
			size ++;
		}
		
		while(node != null){
			if(end != null)
			node.right = end;
			end = node;
			node = node.left;
			size++;
		}
		
		while(node1 != null){
			if(end != null)
			node1.right = end;
			end = node1;
			node1 = node1.left;
			size++;
		}
		
		return end;
	}
	
	public static Node testTree(){
		Node root = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right = new Node(15);
		root.right.left = new Node(9);
		root.right.right = new Node(17);
		return root;
	}
	
	public static Node testTree1(){
		Node root = new Node(10);
		root.left = new Node(6);
		root.right = new Node(14);
		return root;
	}
	
	public static Node convertToLinkedList(Node node, Node head){
		if(node == null) return head;
		head = convertToLinkedList(node.left, head);
		node.left = head;
		head = node;
		return convertToLinkedList(node.right, head);
	}
	
	
	
	public static BSTRes convertToBST(Node node, int start, int end){
		if(start > end){
			return new BSTRes(null, node);
		}
		if(start == end){
			node.left = null;
			Node temp = node.right;
			node.right = null;
			return new BSTRes(node, temp);
		}
		
		int mid = start + (end -start)/2;
		BSTRes leftRes = convertToBST(node, start, mid -1);
		
		Node root = leftRes.sNode;
		root.left = leftRes.root;
		node = leftRes.sNode.right;//advance node
		
		BSTRes rightRes = convertToBST(node, mid + 1, end);
		root.right = rightRes.root;
		
		return new BSTRes(root, rightRes.sNode);
	}
	
	static class BSTRes{
		Node root;
		Node sNode; //startNode;
		public BSTRes(Node root, Node sNode){
			this.root = root;
			this.sNode = sNode;
		}
	}
}
