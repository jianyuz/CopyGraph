package com.z2.StackTest;

import java.util.Stack;

/**
 * could let every element to keep track of the min 
 * by keep a complext object int it.
 * a lot of duplicate space.
 * do better using a separate stack to keep track of local min.
 * 
 * Note extends super.
 * call super.pop and push.
 * @author zhouzhou
 *
 */
public class MinStack extends Stack<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Stack<Integer> minS;
	/**
	 * return the minimum element in stack.
	 */
	public MinStack(){
		minS = new Stack<Integer>();
	}
	/**
	 * when pop see if it is the smallest element.
	 * if so popup it too.
	 */
	public Integer pop(){
		int value = super.pop();
		if(value == min()){
			minS.pop();
		}
		return value;
	}
	/**
	 * when push
	 * if smaller,
	 * push it into minstack.
	 * 
	 * @param s
	 */
	public void push(int s){
		int top = min();
		if(s < top){
			minS.push(s);
		}
		super.push(s);
	}
	
	public int min(){
		if(minS.isEmpty()){
			return Integer.MAX_VALUE;
		}else{
			return minS.peek();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinStack ms = new MinStack();
		ms.push(5);
		ms.push(1);
		ms.push(2);
		ms.push(4);
		
		System.out.println(ms.min());
		ms.pop();
		System.out.println(ms.min());
		ms.pop();
		ms.pop();
		System.out.println(ms.min());
		ms.pop();
		System.out.println(ms.min());
		ms.pop();
		

	}

}
