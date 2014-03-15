package com.z2.clonetest;

public class CloneTest2 implements Cloneable{
	
	private int a;
	private float b;
	
	public CloneTest2(){
		
	}
	
	public CloneTest2 clone(){
		try{
		return (CloneTest2)super.clone();
		
		//if use constructor in super class.
		//sub class can't get the correct class type.
		}catch(CloneNotSupportedException e){
			//can't happen.
			throw new AssertionError();
		}
	}
	
	public static void main (String[] args) throws CloneNotSupportedException{
		CloneTest2 ct = new CloneTest2();
		CloneTest2 ct1 = (CloneTest2) ct.clone();
		if(ct== ct1){
			System.out.println("refer to same memory location");
		}
		if(ct1.getClass() == ct.getClass()){
			System.out.println("same class");
		}
		if(ct1.equals(ct)){
			System.out.println("clone is equal to original");
			//cause it is a new object reference.
		}
	}

}
