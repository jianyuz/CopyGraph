package com.z2.clonetest;

/**
 * now it supports clone since we mark that the object is 
 * cloneable.
 * this interface define the behavor of boject's clone method.
 * it copy the immutable field and primitive types.
 * not return the same object referece.
 * it is not a typical marker interface.
 * modify behavior of the procted method on a superclass.
 * clone call copy object without calling constructors.
 * 
 * @author zhouzhou
 *
 */
public class CloneTest1 implements Cloneable{

	private int a;
	private float b;
	
	public CloneTest1(){
		
	}
	
	public static void main (String[] args) throws CloneNotSupportedException{
		CloneTest1 ct = new CloneTest1();
		CloneTest1 ct1 = (CloneTest1) ct.clone();
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
