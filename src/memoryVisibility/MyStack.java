package memoryVisibility;

import java.util.Stack;

public class MyStack {
	private Stack<Integer> main = new Stack<Integer>();
	private Stack<Integer> min = new Stack<Integer>();
	
	MyStack(){
		
	}
	
	void push(int value){
		main.push(value);
		if(min.isEmpty() || value <= min.peek()){//check min is empty
			min.push(value);
		}
	}
	
	int pop(){
		int value = main.pop();
		if(value == min.peek()){
			min.pop();
		}
		return value;
	}
	
	int min(){
		return min.peek();
	}
	
	
	public static void main(String[] args){
		MyStack myStack = new MyStack();
		myStack.push(2);
		myStack.push(5);
		myStack.push(1);
		myStack.push(3);
		
		System.out.println("min is " + myStack.min());
		myStack.pop();
		System.out.println("min is " + myStack.min());
		
		myStack.pop();
		myStack.pop();
		System.out.println("min is " + myStack.min());
	}
}
