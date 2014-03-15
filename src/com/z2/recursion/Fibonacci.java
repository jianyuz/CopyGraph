package com.z2.recursion;

public class Fibonacci {

	public static void main(String[] args){
		
		System.out.println(fabonacci(7));
		System.out.println(iterFib(7));
	}
	
	public static int  fabonacci(int n){
		if( n== 0 )
			return 0;
		else if(n==1){
			return 1;
		}
		else if( n > 1)
			return fabonacci(n-1) + fabonacci(n-2);
		else
			return -1;//error conditionl/
	}
	
	public static int iterFib(int n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		if(n < 0) return -1;
		int a = 0, b = 1;
		int c = 0;
		for( int i= 2; i<= n; i++){
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}
}
