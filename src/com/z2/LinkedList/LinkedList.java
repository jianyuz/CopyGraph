package com.z2.LinkedList;

import java.util.Hashtable;

public class LinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = constructList();
		removeDuplicate(head);
		System.out.println(head.toString());
		head = constructList1();
		removeDuplicate1(head);
		System.out.println(head.toString());
		head = constructList2();
		System.out.println(findNthToLast(head, 6));
		head = constructList3();
		deleteMiddleNode(head.next.next);
		System.out.println(head);
		
		ListNode a = constructList4();
		ListNode b = constructList5();
		System.out.println(add(a, b).toString());
		
		System.out.println(recursiveAdd(a, b, false).toString());
		
		head = constructList6();
		System.out.println(detectLoop(head).data);
	}
	
	public static ListNode constructList6(){
		ListNode head = new ListNode(2);
		head.next = new ListNode(3);
		head.next.next = new ListNode(4);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next = new ListNode(8);
		head.next.next.next.next.next.next.next = new ListNode(9);
		head.next.next.next.next.next.next.next.next = head.next.next.next;
		return head;
		
	}
	public static ListNode constructList4(){
		ListNode head = new ListNode(3);
		head.next = new ListNode(1);
		head.next.next = new ListNode(5);
		return head;
	}
	public static ListNode constructList5(){
		ListNode head = new ListNode(5);
		head.next = new ListNode(9);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		return head;
	}
	public static ListNode constructList3(){
		ListNode head = new ListNode(2);
		head.next = new ListNode(3);
		head.next.next = new ListNode(4);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(6);
		
		return head;
	}
	
	
	public static ListNode constructList2(){
		ListNode head = new ListNode(2);
		head.next = new ListNode(3);
		head.next.next = new ListNode(4);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next = new ListNode(8);
		return head;
	}
	
	
	public static ListNode constructList(){
		ListNode head = new ListNode(2);
		head.next = new ListNode(3);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(2);
		return head;
	}
	
	public static ListNode constructList1(){
		ListNode head = new ListNode(2);
		head.next = new ListNode(3);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(2);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(2);
		return head;
	}
	
	public static void removeDuplicate(ListNode node){
		if(node == null) return;
		Hashtable<Integer, Boolean> hash = new Hashtable<Integer, Boolean>();
		ListNode prev = null;
		ListNode current = node;
		
		while(current != null){
			if(hash.containsKey(current.data)){//check key contains
				prev.next = current.next;
			}else{
				hash.put(current.data, true);
				prev = current;
			}
			current = current.next;
		}
		
	}
	
	/**
	 * don't use hashtable or any buffer.
	 * keep two pointers.
	 * for each element check with previous nodes to see if there is any duplicate.
	 * 
	 * @param node
	 */
	public static void removeDuplicate1(ListNode node){
		if(node == null) return;
		ListNode prev = node;
		ListNode current = node.next;
		ListNode runner = null;
		
		while(current != null){
			runner = node;
			while(runner != current){
				if(runner.data == current.data){//find duplicate.
					prev.next = current.next; //delete current as duplicate; 
					//only one is possible
					break;
				}
				runner = runner.next;
			}
			//runner finished. increment current.
			if(runner == current){
				prev = current;//otherwise prev keeps the same.
			}
			current = current.next;			
		}
		
	}
	
	public static int findNthToLast(ListNode node, int n){
		if(node == null) throw new IllegalArgumentException("list is empty");
		int counter = 0;
		ListNode current = node;
		while(current != null && counter <= n){
			current = current.next;
			counter ++;
		}
		if(counter != n +1 ) throw new IllegalArgumentException("n exceeds length");
		
		ListNode head = node;
		while(current != null){
			head = head.next;
			current = current.next;
		}
		
		return head.data;
	}
	
	/**
	 * no need to recursively copy.
	 * copy the data and pointer
	 * @param mid
	 */
	public static void deleteMiddleNode(ListNode mid){
		if(mid == null || mid.next == null)
			return;
		
		mid.data = mid.next.data;
		mid.next = mid.next.next;
		
//		ListNode current = mid;
//		while(current != null && current.next != null){
//			current.data = current.next.data;
//			if(current.next.next == null){
//				current.next = null;//remove last element
//			}
//			current = current.next;
//			
//		}
	}
	
	public static ListNode add(ListNode n1, ListNode n2){
		if(n1 == null && n2 == null){
			return null;
		}
		if(n1 == null) return n2;
		if(n2 == null) return n1;
		
		ListNode p1 = n1;
		ListNode p2 = n2;
		
		ListNode newP = null, prevNewP= null, newHead = null;
		int sum;
		boolean increment = false;
		while(p1 != null && p2 != null){
			sum = p1.data + p2.data;
			if(increment){
				sum++;
				increment = false;
			}
			if(sum >= 10){
				increment = true;
				sum -= 10;
			}
			newP = new ListNode(sum);
			if(newHead == null) newHead = newP;
			if(prevNewP != null) prevNewP.next = newP;//link up.
			prevNewP= newP;
			
			p1 = p1.next; //loop
			p2 = p2.next;
		}
		
		while(p1 != null){
			sum = p1.data;
			if(increment){
				sum ++;
				increment = false;
			}
			newP = new ListNode(sum);
			if(prevNewP != null) prevNewP.next = newP;//link up.
			prevNewP = newP;
			p1 = p1.next;
			
		}
		
		while(p2 != null){
			sum = p2.data;
			if(increment){
				sum ++;
				increment = false;
			}
			newP = new ListNode(sum);
			if(prevNewP != null) prevNewP.next = newP;//link up.
			prevNewP = newP;
			p2 = p2.next;
			
		}
		
		return newHead;
	}
	
	public static ListNode recursiveAdd(ListNode n1, ListNode n2, boolean increment){
		if(n1 == null && n2 == null){
			return null;
		}
		if(n1 == null) return n2;
		if(n2 == null) return n1;
		
		int sum = n1.data + n2.data;
		if(increment) sum++;
		
		ListNode head = new ListNode(sum %10);
		ListNode next = recursiveAdd(n1.next, n2.next, (sum >=10)?true:false);
		head.next = next;
		
		return head;
	}
	
	/**
	 * they will meet k node before the start of loop.
	 * @param head
	 * @return
	 */
	public static ListNode detectLoop(ListNode head){
		if(head == null) return null;
		ListNode p1 = head, p2 = head;
		while(p1.next !=null && p2.next.next != null){
			p1 = p1.next;
			p2 = p2.next.next;
			if(p1 == p2){
				break;
			}
		}
		
		if(p2.next == null)//error check no meeting point
		return null;
		
		p1 = head;//move back to head and move the meeting point in sync
		//head is k away from loop start
		//meeting point is k away from loop start.
		while(p1 != p2){
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

}
