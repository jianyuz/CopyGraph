package com.z2.loopinlist;

/**
 * detect loop in linked list.
 * and splitting the linked list in halves.
 * 
 * @author zhouzhou
 *
 */
public class LoopInList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.print(loopInList(constructTestList()));
		Node head = constructTestList1();
		Node backNode = splitList(head);
		
		Node curNode = backNode;
		int count = 0;
		while(curNode != null){
			count++;
			curNode = curNode.next;
		}
		System.out.println("\nnumber of nodes is second list is: " + count);
	}
	
	public static Node splitList(Node node){
		if(node == null) return null;
		Node slow = node;
		Node fast = node;
		Node slowTail = slow;
		Node nextHead;
		while(fast != null){
			slowTail = slow;
			slow = slow.next;
			fast = (fast.next != null)?fast.next.next:fast.next;
		}
		nextHead = slowTail.next;
		slowTail.next = null;
		return nextHead;
		
	}
	
	public static Node constructTestList(){
		Node head = new Node(4);
		head.next = new Node(3);
		head.next.next = new Node(5);
		head.next.next.next = new Node(8);
		head.next.next.next.next = new Node(7);
		head.next.next.next.next.next = new Node(2);
		return head;
	}
	
	public static Node constructTestList1(){
		Node head = new Node(4);
		head.next = new Node(3);
		head.next.next = new Node(5);
		head.next.next.next = new Node(8);
		head.next.next.next.next = new Node(7);
		head.next.next.next.next.next = new Node(2);
		return head;
	}
	
	public static boolean loopInList(Node node){
		if(node == null) return false;
		
		Node slow = node, fast = node;
		while(slow != null && fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast){
				return true;
			}
		}
		return false;
	}

}
