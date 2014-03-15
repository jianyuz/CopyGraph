package com.z2.LinkedList;

public class ListNode {

	
	int data;
	ListNode next;
	
	public ListNode(int data){
		this.data = data;
		this.next = null;
		
	}
	
	@Override 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		ListNode current = this;
		while(current != null){
			sb.append(current.data + ",");
			current = current.next;
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
