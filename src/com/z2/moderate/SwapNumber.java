package com.z2.moderate;

public class SwapNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		swap(3, 5);
		System.out.println(getMax(3, 5));
	}
	
	public static void swap(int a , int b){
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
	}
	
	public static int getMax(int a, int b){
		int c = a -b;
		int k = (c >> 31) & 0x1;
		return (a - k * c);
	}
	
	

}
