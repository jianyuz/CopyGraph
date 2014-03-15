package com.z2.StackTest;

import java.util.ArrayList;
import java.util.List;

/**
 * mixed three stack in one array.
 * 
 * @author zhouzhou
 *
 */
public class StackTest1 {

	int stackSize;
	StackNode[] store;
	int[] indexArray = {-1,-1,-1};
	List<Integer> freeList = new ArrayList<Integer>();
	int indexUsed;
	
	public StackTest1(int stackSize){
		this.store = new StackNode[stackSize * 3];
		this.stackSize = stackSize;
		this.indexUsed = 0;
	}
	
	/**
	 * stackNum 0, 1, 2
	 * 
	 * @param stackNum
	 * @param data
	 * @throws Exception 
	 */
	public void push(int stackNum, int data) throws Exception{
		int previousIndex = indexArray[stackNum];
		if(freeList.size() > 0){
			int index = freeList.get(0);
			store[index] = new StackNode(previousIndex, data);
			freeList.remove(0);
			indexArray[stackNum] = index;
		}else{
			if(indexUsed > stackSize * 3){
				throw new Exception("stack overflow");
			}
		
			store[indexUsed] = new StackNode(previousIndex, data);
			indexArray[stackNum] = indexUsed;
			indexUsed ++;
		}
	}
	
	public int pop(int stackNum) throws Exception{
		int index = indexArray[stackNum];
		if(index == -1){
			throw new Exception("empty stack");
		}
		int value = store[index].data;
		indexArray[stackNum] = store[index].previous;
		store[index] = null;
		
		if(index < indexUsed -1){
			freeList.add(index);
		}else{
			indexUsed--;
		}
		return value;
	}
	
	public boolean isEmpty(int stackNum){
		int index = indexArray[stackNum];
		if(index == -1) return true;
		return false;
	}
	
	public int peek(int stackNum){
		return store[indexArray[stackNum]].data;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		StackTest1 st = new StackTest1(3);
		st.push(2, 4);
		st.push(2, 5);
		st.push(1, 1);
		st.push(2,  7);
		System.out.println(st.peek(2));
		System.out.println(st.pop(1));
		System.out.println(st.isEmpty(2));
		System.out.println(st.isEmpty(1));
		st.push(1, 2);
		System.out.println(st.peek(2));
		System.out.println(st.peek(1));

	}

}
