package com.z2.clonetest;
/**
 * CloneTest doesn't implement clonable.
 * calling clone says it is not supported.
 * it doesn't even do bit by bit copy of it.
 * 
 * @author zhouzhou
 *
 */
public class CloneTest{
	private int a;
	private float b;
	
	public CloneTest(){
		
	}
	
	public static void main (String[] args) throws CloneNotSupportedException{
		CloneTest ct = new CloneTest();
		ct.clone();
	}

}
