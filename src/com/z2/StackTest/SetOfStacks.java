package com.z2.StackTest;

import java.util.ArrayList;
import java.util.List;

public class SetOfStacks {

	private int capacity;
	
	private List<MyStack> stacks;
	public SetOfStacks(int capacity){
		this.capacity = capacity;
		stacks = new ArrayList<MyStack>();
	}
	
	public void push(int v) throws Exception{
		MyStack last = getLastStack();
		if(last != null && !last.isAtCapacity()){
			last.push(v);
		}else{
			MyStack stack = new MyStack(capacity);
			stack.push(v);
			stacks.add(stack);
		}
	}
	
	private MyStack getLastStack() {
		if(stacks.size() > 0){
			return stacks.get(stacks.size() -1);
		}
		return null;
	}

	public int pop() throws Exception{
		MyStack last = getLastStack();
		if(last == null){
			throw new Exception("emptyStack");
		}
		int v = last.pop();
		if(last.size() == 0){
			stacks.remove(stacks.size() -1);
		}
		return v;
	}
	
	public int popAt(int index) throws Exception{
		if(index > stacks.size() -1){
			throw new Exception("out of index exception");
		}
		if(index == stacks.size() -1){
			//last stack
			return this.pop();
		}else{
			MyStack stack = stacks.get(index);
			int v = stack.pop();
			MyStack nextStack;
			for(int i= index+1; i< stacks.size(); i++){
				nextStack = stacks.get(i);
				stack.push(nextStack.removeBottom());
			    stack = nextStack;
			}
			if(stack.isEmpty()){//remove the empty stack from the stacks list.
				stacks.remove(stacks.size()-1);
			}
			return v;
		}
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SetOfStacks ss = new SetOfStacks(2);
		ss.push(1);
		ss.push(2);
		ss.push(3);
		ss.push(4);
		ss.push(5);
		ss.push(6);
		System.out.println(ss.popAt(0));
		System.out.println(ss.pop());
		System.out.println(ss.popAt(1));
		System.out.println(ss.pop());

	}

}
