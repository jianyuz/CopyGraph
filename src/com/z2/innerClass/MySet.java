package com.z2.innerClass;

import java.util.AbstractSet;
import java.util.Iterator;

public class MySet<E> extends AbstractSet<E>{

	public static void main(String[] args){
		MySet<Integer> set = new MySet<Integer>(5);
		set.add(1);
		set.add(5);
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	E[] elements;
	int index;
	
	@SuppressWarnings(value = { "unchecked" })
	public MySet(int capacity){
		elements = (E[]) new Object[capacity];
	}
	
	@Override
	public boolean add(E e){
		class LocalClass{//local class 
			E a;
			LocalClass(E a){
				this.a = a;
			}
			public String toString(){
				return "" + a.toString();
			}
		}
		LocalClass localClass = new LocalClass(e);
		System.out.println("adding " + localClass.toString());
		if(index > elements.length) return false;
		elements[index++] = e;
		return true;
	}
	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	@Override
	public int size() {
		return index;
	}
	
	private class MyIterator implements Iterator<E>{

		private int curIndex;
		
		MyIterator(){
			curIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			if(curIndex < index){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public E next() {
			return elements[curIndex++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

}
