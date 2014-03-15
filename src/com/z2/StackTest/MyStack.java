package com.z2.StackTest;

public class MyStack {
	private int capacity;
	private Node top, bottom;
	private int size = 0;
	
	public MyStack(int capacity){
		this.capacity = capacity;
	}
	
	public int size(){
		return size;
	}
	
	public void push(int v) throws Exception{
		if(!isAtCapacity()){
			Node n = new Node(v);
			if(size == 0){
				top = bottom = n;
			}else{
				top.above = n;
				n.below = top;
				top = n;
			}
			size ++;
			
		}else{
			throw new Exception("stack overflow");
		}
	}
	
	public int pop() throws Exception{
		if(!isEmpty()){
			int value = top.data;
			top = top.below;
			size --;
			if(isEmpty()){
				top = bottom = null;
			}
			return value;
		}else{
			throw new Exception("empty stack");
		}
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public boolean isAtCapacity(){
		return capacity == size;
	}
	
	public int removeBottom() throws Exception{
		if(!isEmpty()){
			int value = bottom.data;
			bottom = bottom.above;
			size --;
			if(isEmpty()){
				top = bottom = null;
			}
			return value;
		}else{
			throw new Exception("empty stack");
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MyStack ms = new MyStack(3);
		ms.push(1);
		ms.push(2);
		System.out.println(ms.isEmpty());
		ms.push(3);
		System.out.println(ms.isAtCapacity());
		System.out.println(ms.pop());
		ms.pop();
		ms.pop();
		System.out.println(ms.isEmpty());
		ms.push(1);
		ms.removeBottom();
		System.out.println(ms.isEmpty());
	}

}
