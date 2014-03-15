package com.z2.QueueTest;

import java.util.Stack;

public class MyQueue {

	private Stack<Integer> tail;
	private Stack<Integer> head;
	
	public MyQueue(){
		head = new Stack<Integer>();
		tail = new Stack<Integer>();
	}
	
	public void offer(int i){
			tail.push(i);
	}
	
	public int poll() throws Exception {
		if (!head.isEmpty()) {
			return head.pop();
		}
		// move from tail to head.
		while (!tail.isEmpty()) {
			head.push(tail.pop());
		}
		return head.pop();

	}
	
	public int size(){
		return head.size() + tail.size();
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MyQueue q = new MyQueue();
		q.offer(1);
		q.offer(2);
		System.out.println(q.poll());
		q.offer(3);
		q.offer(4);
		System.out.println(q.poll());
		System.out.println(q.poll());
		q.poll();
		//q.poll();
	}

}
