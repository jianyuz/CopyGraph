package com.z2.bigInteger;

import java.util.Iterator;

public class LinkedListBigInteger extends Number implements Iterable<Node<Integer>>, Comparable<LinkedListBigInteger>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Node<Integer> intListHead;
	private final int signum;
	
	
	public static void main(String [] args){
		LinkedListBigInteger l1 = new LinkedListBigInteger("+234567");
		LinkedListBigInteger l2 = new LinkedListBigInteger("+111111");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		
		l1 = new LinkedListBigInteger("+234567");
		l2 = new LinkedListBigInteger("+781153");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("+234567");
		l2 = new LinkedListBigInteger("+53781153");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("+53781153");
		l2 = new LinkedListBigInteger("+234567");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("+234567");
		l2 = new LinkedListBigInteger("-111111");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("-234567");
		l2 = new LinkedListBigInteger("+111111");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("-234567");
		l2 = new LinkedListBigInteger("+141111");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("204567");
		l2 = new LinkedListBigInteger("-9999");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("204567");
		l2 = new LinkedListBigInteger("0");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
		
		l1 = new LinkedListBigInteger("-204567");
		l2 = new LinkedListBigInteger("0");
		System.out.println("l1 = " + l1);
		System.out.println("l2 = " + l2);
		System.out.println(l1.add(l2).toString());
	}
	
	
	public LinkedListBigInteger(){
		this("0");
	}
	
	public LinkedListBigInteger(String value){
		
		if(value.length() == 0){
			throw new NumberFormatException("invalid input");
		}
		int cursor = 0;
		//check the sign.
		int sign = 1;
		int index1 = value.lastIndexOf("-");
		int index2 = value.lastIndexOf("+");
		if(index1 + index2 <= -1){
			if(index1 == 0 || index2 ==0){//sign exists
				cursor = 1;
			}
			if(index1 == 0){
				sign = -1;
			}
		}else{
			throw new NumberFormatException("invalid input");
		}
		
		while(cursor < value.length() && Character.digit(value.charAt(cursor), 10) == 0){
			cursor ++;
		}
		if(cursor == value.length()){
			signum = 0;
			intListHead = new Node<Integer>(0, null);
			return;
		}//all 0 without anything.
		
		Node<Integer> temp;
		Node<Integer> tempHead = null;
		this.signum = sign;
		while(cursor < value.length()){
			temp = new Node<Integer> (Integer.valueOf("" + value.charAt(cursor)), tempHead);
			tempHead = temp;
			cursor ++;
		}
		
		this.intListHead = tempHead;
		
	}
	
	
	public LinkedListBigInteger(Node<Integer> listIntHead, int signum) {
		this.signum = signum;
		this.intListHead = listIntHead;
	}


	public LinkedListBigInteger add(LinkedListBigInteger l){
		if(signum == 0){
			return l;
		}
		if(l.signum == 0){
			return this;
		}
		
		if(signum == l.signum){
			return new LinkedListBigInteger(add(intListHead, l.intListHead), signum);
		}
		
		int cmp =  compareMagnitude(l);
		if(cmp == 0){
			return new LinkedListBigInteger(new Node<Integer>(0, null), 0);
		}
		//different sign
		Node<Integer> head = (cmp > 0)?subtract(intListHead, l.intListHead):subtract(l.intListHead, intListHead);
		return new LinkedListBigInteger(head , cmp == signum? 1: -1);
		
	}
	
	private Node<Integer> subtract(Node<Integer> big,
			Node<Integer> little) {
		
		Node<Integer> tmp = big;
		Node<Integer> tmp1 = little;
		Node<Integer> tmpRes;
		Node<Integer> tmpResTail = null;
		Node<Integer> resIntListHead = null;
		
		int borrow = 0;
		int diff = 0;
		while(tmp != null && tmp1 != null){
			diff = tmp.getElement() - tmp1.getElement() + borrow;
			if(diff < 0){
				diff += 10;
				borrow = -1;
			}else
				borrow = 0;
			tmpRes = new Node<Integer>(diff, null);
			if(resIntListHead == null){
				resIntListHead = tmpRes;
			}
			if(tmpResTail !=null){
				tmpResTail.setNext(tmpRes);
			}
			tmpResTail = tmpRes;
			tmp = tmp.getNext();
			tmp1 = tmp1.getNext();
		}
		
		//big left
		while(tmp != null && borrow != 0){
			diff = tmp.getElement() + borrow;
			if(diff < 0){
				diff += 10;
				borrow = -1;
			}else
				borrow = 0;
			tmpRes = new Node<Integer>(diff, null);
			if(resIntListHead == null){
				resIntListHead = tmpRes;
			}
			if(tmpResTail !=null){
				tmpResTail.setNext(tmpRes);
			}
			tmpResTail = tmpRes;
			tmp = tmp.getNext();
		}
		
		//copy the remainder
		//copy remainder
		while(tmp!= null){
			tmpRes = new Node<Integer>(tmp.getElement(), null);
			if(resIntListHead == null){
				resIntListHead = tmpRes;
			}
			if(tmpResTail != null){
				tmpResTail.setNext(tmpRes);
			}
			tmpResTail = tmpRes;
			tmp = tmp.getNext();
		}
				
		return resIntListHead;
	}
	

	private Node<Integer> add(Node<Integer> intListHead, Node<Integer> intListHead1) {
		Node<Integer> tmp = intListHead;
		Node<Integer> tmp1 = intListHead1;
		Node<Integer> tmpRes;
		Node<Integer> tmpResTail = null;
		Node<Integer> resIntListHead = null;
		
		int carry = 0;
		int sum;
		while(tmp != null && tmp1 != null){
			sum = tmp.getElement() + tmp1.getElement() + carry;
			tmpRes = new Node<Integer>(sum %10,  null);
			if(resIntListHead == null){
				resIntListHead = tmpRes;
			}
			if(tmpResTail != null){
				tmpResTail.setNext(tmpRes);
			}
			tmpResTail = tmpRes;
			carry = sum /10;
			tmp = tmp.getNext();
			tmp1 = tmp1.getNext();
		}
		
		if(tmp1 != null){
			tmp = tmp1; //switch over the longer remainder
		}

		while(tmp!= null && carry > 0){
			sum = tmp.getElement() + carry;
			tmpRes = new Node<Integer>(sum%10, null);
			if(resIntListHead == null){
				resIntListHead = tmpRes;
			}
			if(tmpResTail != null){
				tmpResTail.setNext(tmpRes);
			}
			tmpResTail = tmpRes;
			carry = sum /10;
			tmp = tmp.getNext();
		}
		
		//copy remainder
		while(tmp!= null){
			tmpRes = new Node<Integer>(tmp.getElement(), null);
			if(resIntListHead == null){
				resIntListHead = tmpRes;
			}
			if(tmpResTail != null){
				tmpResTail.setNext(tmpRes);
			}
			tmpResTail = tmpRes;
			tmp = tmp.getNext();
		}
		
		if(carry > 0){//allocate new node
			tmpRes = new Node<Integer>(1, null);
			if(tmpResTail != null){
				tmpResTail.setNext(tmpRes);
				
			}
		}
		return resIntListHead;
	}



	private int compareMagnitude(LinkedListBigInteger l) {
		Node<Integer> tmp = this.intListHead;
		Node<Integer> tmp1 = l.intListHead;
		int tempRes = 0;
		while(tmp != null && tmp1 != null){
			if(tmp.getElement() != tmp1.getElement()){
				tempRes = (tmp.getElement() < tmp.getElement())? -1: 1;
			}
			tmp = tmp.getNext();
			tmp1 = tmp1.getNext();
		}
		
		if(tmp == null && tmp1 == null){//same length;
			return tempRes;
		}else if(tmp == null){
			//that is longer.
			return -1;
		}else if(tmp1 == null){
			return 1;
		}
		
		return 0;
	}



	@Override
	public int compareTo(LinkedListBigInteger o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Node<Integer>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		Node<Integer> tmp = this.intListHead;
		while(tmp != null){
			sb.insert(0, tmp.getElement());
			tmp = tmp.getNext();
		}
		
		if(signum < 0) sb.insert(0, "-");
		return sb.toString();
	}

}
