package memoryVisibility;

public class DeleteNode {

	public static void main(String[] args){
		Node head = new Node();
		head.data = 1;
		
		Node ele1 = new Node();
		ele1.data = 2;
		
		Node ele2 = new Node();
		ele2.data = 3;
		
		head.next = ele1;
		ele1.next = ele2;
		
		Node newHead = deleteNode(head, 2);
		Node current = newHead;
		while(current != null){
			System.out.println(current.data);
			current = current.next;
		}
	}
	
	private static Node deleteNode(Node head, int data){
		Node fakeHead = new Node();
		fakeHead.next = head;
		Node current = fakeHead;
		while(current.next != null){
			if(current.next.data == data){
				current.next = current.next.next;
				return fakeHead.next;
			}
			current = current.next;
		}
		return fakeHead.next;
	}
	
	static class Node{
		int data;
		Node next;
	}
}


