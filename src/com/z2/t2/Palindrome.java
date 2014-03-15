package com.z2.t2;

public class Palindrome {

	public static void main(String[] args){
		System.out.println(isPalindrome(2442));
		System.out.println(isPalindrome(242));
		System.out.println(isPalindrome(2452));
		System.out.println(isPalindrome(345));
		System.out.println(isPalindrome(3446));
		System.out.println(isPalindrome(0));
		System.out.println(isPalindrome(-1));
		System.out.println("===============");
		System.out.println(isPalindromeR(2442));
		System.out.println(isPalindromeR(242));
		System.out.println(isPalindromeR(2452));
		System.out.println(isPalindromeR(345));
		System.out.println(isPalindromeR(3446));
		System.out.println(isPalindromeR(0));
		System.out.println(isPalindromeR(-1));
		System.out.println("===============");
		System.out.println(isPalindrome1(2442));
		System.out.println(isPalindrome1(242));
		System.out.println(isPalindrome1(2452));
		System.out.println(isPalindrome1(345));
		System.out.println(isPalindrome1(3446));
		System.out.println(isPalindrome1(0));
		System.out.println(isPalindrome1(-1));
	}
	
	public static boolean isPalindrome(int x){
		if(x <0 ) return false;
		int div = 1;
		
		while(x/div >=10){
			div *= 10;
		}
		System.out.println("div=" + div);
		
		while(x > 0){
			if(x/div != x%10) return false;
			x = x%div/10;
			div = div/100;
		}
		
		
		
		return true;	
	}
	
	public static boolean isPalindromeR(int x){
		if(x < 0) return false;
		if(x == 0) return true;
		int div = 1;
		
		while(x/div >=10){
			div *= 10;
		}
		
		if(x/div != x%10) return false;
		x = x%div/10; 
		return isPalindromeR(x);
	}
	
	public static boolean isPalindrome1(int x){
		if(x < 0 ) return false;
		int y = 0;
		int z = x;
		for (; z> 0; z=z/10){
			y = y * 10 + z%10;
		}
		return (x == y);
	}
}
