package com.z2.bigInteger;

public class Node <E> {
	private E element;
	private Node<E> next;
	
	public Node(){
		this(null, null);
	}
	
	public Node(E e, Node<E> n){
		this.element = e;
		this.next = n;
	}
	
	public E getElement(){
		return element;
	}
	
	public Node<E> getNext(){
		return next;
	}
	
	public void setElement(E newEle){
		this.element = newEle;
	}
	
	public void setNext(Node<E> newNext){
		this.next = newNext;
	}
}
