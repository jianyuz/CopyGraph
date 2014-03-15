package com.z2.QueueTest;

import java.util.Stack;

public class StackSort {

	/**
	 * return elements in sorted stack.
	 * @param o
	 * @return
	 */
	public static Stack<Integer> sort(Stack<Integer> o){
		Stack<Integer> s = new Stack<Integer>();
		int tmp;
		while (!o.isEmpty()) {
			tmp = o.pop();
			while (!s.isEmpty() && s.peek() < tmp) {
				o.push(s.pop());
			}
			s.push(tmp);

		}

		return s;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<Integer> o = new Stack<Integer>();
		o.push(2);
		o.push(7);
		o.push(4);
		o.push(5);
		o.push(8);
		o.push(6);
		
		Stack<Integer> s= sort(o);
		while(!s.isEmpty()){
			System.out.print(s.pop() + " " );
		}
	}

}
