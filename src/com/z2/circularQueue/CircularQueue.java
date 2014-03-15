package com.z2.circularQueue;

public class CircularQueue {

	private static final int MAX_CAPACITY = 1 << 30;
	private int head;
	private int tail;
	private int size;
	private int capacity;
	private int[] store;
	
	public CircularQueue(int capacity){
		this(capacity, MAX_CAPACITY);
	}
	
	public CircularQueue(int c, int mc){
		if(c > mc)
			throw new IllegalArgumentException("capatcity greater than max");
		store = new int[c];
		capacity = c;
		head = tail = 0;
		size = 0;
	}
	
	
	public boolean add(int item) throws Exception{
		if(size == capacity){
			throw new Exception("out of bound");
		}
		size ++;
		store[tail] = item;
		tail = (tail + 1)%capacity;
		return true;
	}
	
	public int remove(){
		if(size == 0) return -1;
		size --;
		int ret = store[head];
		
		head = (head + 1)%capacity;
		return ret;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		CircularQueue q = new CircularQueue(4);
		
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(3);
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		q.add(1);
		q.add(2);
		System.out.println(q.remove());
		System.out.println(q.remove());
		q.add(1);
		q.add(1);
		q.add(1);
		q.add(1);
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
	}

}
