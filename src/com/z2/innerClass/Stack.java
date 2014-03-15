package com.z2.innerClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Stack<E> {
	private E[] elements;
	private int size=0;
	private static final int DEFAULT_CAPACITY= 16;
	
	@SuppressWarnings("unchecked") 
	public Stack(){
		this.elements = (E[])new Object[DEFAULT_CAPACITY];
	}//list is not native implementation.
	//best use array here.
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public void pushAll(Iterable<? extends E> src){
		for(E e: src){
			push(e);
		}
	}
	
	public void popAll(Collection<? super E> dst){
		while(!isEmpty()){
			dst.add(pop());
		}
	}
	
	public E pop(){
		if(size <0 ) return null;
		return elements[size--];
	}
	
	public void push(E e){
		elements[size++] = e;
	}
	
	public static void main(String[] args){
		Stack<Number> numberStack = new Stack<Number>();
		List<Integer> listInt = new ArrayList<Integer>();
		Collections.addAll(listInt, 1, 2, 3);
		Iterable<Integer> integers = listInt;
		numberStack.pushAll(integers);
		
		Collection<Object> dst= new ArrayList<Object>();
		numberStack.popAll(dst);
	}
	
	//PECS producer extends, consumer super.

}
