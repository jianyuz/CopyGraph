package com.z2.primeNumber;

public class ReverseList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	   Node head = testList();
	   Node newHead = reverseList(head);
	   
	   while(newHead != null){
		   System.out.print(newHead.value + " ");
		   newHead = newHead.next;
	   }
	   System.out.println();
	   head = testList();
	   newHead = recursiveReverse(head, null);
	   
	   while(newHead != null){
		   System.out.print(newHead.value + " ");
		   newHead = newHead.next;
	   }

	   System.out.println(isPalindrome(12324));

	}
	
	public static Node testList(){
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		return head;
	}
	
	/**
	 * return the new head.
	 * @param head
	 * @return
	 */
	
	public static Node reverseList(Node head){
		if(head == null || head.next == null){
			return head;
		}
		Node current = head;
		Node newCur = null;
		
		while(current != null){
			Node tmp = current;
			current = current.next;
			tmp.next = newCur;
			newCur = tmp; 
		}
		
		return newCur;
		
	}
	
	public static Node recursiveReverse(Node node, Node newNode){
		if(node== null){
			return newNode;
		}else{
			Node tmp = node;
			node = node.next;
			tmp.next = newNode;
			newNode = tmp;
			return recursiveReverse(node, newNode);
		}
	}
	
	static final class Node{
		int value;
		Node next;
		
		Node(int value){
			this(value, null);
		}
		
		Node(int value, Node next){
			this.value = value;
			this.next = next;
		}
	}
	
	public static boolean isPalindrome(int x){
		if(x < 0 ) return false;
		int div = 1;
		while (x/div >= 10){
			div *= 10;
		}
		
		while(x != 0){
			int end = x %10;
			int first = x/div;
			if(first != end){
				return false;
			}
			x = (x % div)/10;
			div = div/100;
		}
		return true;
		
	}

}
