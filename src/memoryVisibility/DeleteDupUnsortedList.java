package memoryVisibility;

public class DeleteDupUnsortedList {

	public static void main(String[] args){
		Node head = new Node();
		head.data = 1;
		
		
		Node n2 = new Node();
		n2.data = 2;
		head.next= n2;
		
		Node n3 = new Node();
		n3.data = 3;
		n2.next = n3;
		
		Node n4 = new Node();
		n4.data = 4;
		n3.next = n4;
		
		
		Node cur = deleteDup(head);
		while(cur != null){
			System.out.println(cur.data);
			cur = cur.next;
		}
		
	}
	
	public static Node deleteDup(Node head){
		Node dummy = new Node();
		dummy.next = head;
		
		Node unique = head.next;
		
		Node cur = unique;
		while(cur != null){
			//for each check if it is one of the unique.
			Node iCur = head;
			while(iCur.next != null && iCur != unique){
				if(iCur.data == cur.data){
					break; // found duplicate.
				}
				iCur = iCur.next;
			}
			
			if(iCur == unique){
				//copy the data over
				int tmp = cur.data;
				cur.data = unique.data;
				unique.data = tmp;
				
				unique = unique.next;
			}
			cur = cur.next;
		}
		
		
		cur = dummy;
		while(cur.next != null){
			if(cur.next == unique){
				//delete all after.
				cur.next = null;
				break;
			}
			cur = cur.next;
		}
		return dummy.next;
	}
	
	
	static class Node{
		int data;
		Node next;
	}
}
