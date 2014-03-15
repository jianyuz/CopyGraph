package com.z2.StackTest;

/**
 * implement 3 stacks using one array.
 * @author zhouzhou
 *
 */
public class StackTest implements Cloneable{

	private int[] store;
	private int stackSize;
	private int[] indexArray= {0, 0, 0};
	
	public StackTest(int stackSize){
		this.store = new int[stackSize * 3];
		this.stackSize = stackSize;
	}
	
	public void push(int sNum, int data) throws Exception{
		if(indexArray[sNum] >= stackSize){
			throw new Exception("exeeds stack size");
		}
		int index = indexArray[sNum] + sNum * stackSize;
		store[index] = data;
		indexArray[sNum] ++;
	}
	
	public int pop(int sNum) throws Exception{
		int index = indexArray[sNum];
		if(index == 0){
			throw new Exception("empty stack");
		}
		index = index -1 + sNum * stackSize;
		int value = store[index];
		//store[index] = null;
		indexArray[sNum]--;
		return value;
	}
	
	public int peek(int sNum) throws Exception{
		int index = indexArray[sNum];
		if(index == 0){
			throw new Exception("empty stack");
		}
		index = index -1 + sNum * stackSize;
		return store[index];
	}
	
	public boolean isEmpty(int sNum){
		int index = indexArray[sNum];
		if(index == 0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StackTest st = new StackTest(3);
		st.push(2, 4);
		st.push(2, 5);
		st.push(2, 6);
		System.out.println(st.pop(2));
		st.push(1, 1);
		System.out.println(st.peek(2));
		System.out.println(st.pop(2));
		st.pop(2);
		System.out.println(st.isEmpty(2));
		System.out.println(st.isEmpty(1));
		st.pop(2);
	}

}
