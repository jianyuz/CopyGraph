package com.z2.AlternateList;

/**
 * Suppose that we have a sorted singly linked list with integer values. For example:
1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
We want to change the pointers of this linked list so that it becomes:
7->1->6->2->5->3->4

I did not have enough time left to finish my code and I could not think of a good solution.
 * @author zhouzhou
 *
 */
public class AlternateList {

	public static void main(String[] args){
		Node head = buildList();
		Node newHead = alternateList(head);
		printList(newHead);
	}
	
	public static void printList(Node head){
		while(head != null){
			System.out.print(head.data + ",");
			head = head.next;
		}
	}
	
	public static Node buildList(){
		Node head = new Node(1, null);
		head.next = new Node(2, null);
		head.next.next = new Node(3, null);
		head.next.next.next = new Node(4, null);
		head.next.next.next.next = new Node(5, null);
		head.next.next.next.next.next = new Node(6, null);
		head.next.next.next.next.next.next = new Node(7, null);
		head.next.next.next.next.next.next.next= new Node(8, null);
		return head;
	}
	
	public static Node alternateList(Node head){
		if(head == null || head.next == null){
			return head;
		}
		Node slowP = head;
		Node fastP = head;
		
		while(fastP!= null && fastP.next != null){
			fastP = fastP.next.next;
			slowP = slowP.next;
		}
		
		//after found the mid node.
		//reverse the pointer from mid to the end.
		Node mid = slowP;
		Node tmp;
		Node tail = null;
		//reverse list, like taking node one by one to create a new list
		while(mid != null){
			tmp = mid.next;
			mid.next = tail;
			tail = mid;
			mid = tmp;	
		}
		
		printList(head);
		System.out.println();
		printList(tail);
		System.out.println();
		//now alternate.
		
		Node newHead = tail;
		while(tail != null && head != null && head != tail){
			tmp = tail.next;
			tail.next = head;
			if(head.next == tail){//break the loop
				head.next = null;
			}
			tail = tmp;
			//exchange head and tail
			tmp = head;
			head = tail;
			tail = tmp;
		}
		
		return newHead;
	}
	
	
}
