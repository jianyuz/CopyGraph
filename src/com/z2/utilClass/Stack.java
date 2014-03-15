package com.z2.utilClass;

import java.util.Arrays;


public class Stack implements Cloneable{

	Object[] items; //if we mark it as final.
	//it can not be assigned.
	
	int size;
	private static final int defaultSize = 16;
	
	@Override public Stack clone(){
		try{
			Stack res = (Stack)super.clone();
			//shouldn't call push here.
			res.items = items.clone();
			return res;
		}catch(CloneNotSupportedException e){
			throw new AssertionError();
		}
		
	}
	public Stack(){
		this(defaultSize);
		
	}
	
	public Stack(int capacity){
		this.items = new Object[capacity];
		this.size = 0;
	}
	
	public void push(Object item){
		ensureCapacity();
		items[size++] = item;
	}
	
	private void ensureCapacity() {
		if(size == items.length){
			items = Arrays.copyOf(items, 2 * size + 1);
		}
	}

	public Object pop() throws Exception{
		if(size == 0){
			throw new Exception("empty stack");
		}
		Object item = items[--size];
		items[size] = null;//ensure the element is garbage collected.
		return item;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
