package com.z2.sqrtInt;

public class SqrtInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Math.sqrt(150) + " " + sqrtInt(150) + " " + sqrtInt1(150));
		System.out.println(Math.sqrt(0) + " " + sqrtInt(0) + " " + sqrtInt1(0));
		System.out.println(Math.sqrt(1) + " " + sqrtInt(1) + " " + sqrtInt1(1));
		System.out.println(Math.sqrt(2) + " " + sqrtInt(2) + " " + sqrtInt1(1));
		System.out.println(Math.sqrt(3) + " " + sqrtInt(3) + " " + sqrtInt1(3));
		System.out.println(Math.sqrt(4) + " " + sqrtInt(4) + " " + sqrtInt1(4));
	}
	
	/**
	 * non math method to compuate sqrt of int.
	 * return int
	 * n*n <=x < n+1 * n + 1
	 * @param x
	 * @return
	 */
	public static int sqrtInt(int x) {
		if(x < 0 ) return -1;
		int start = 0;
		int end = x;
		int mid, mid1, midsquare, mid1square;

		while (start <= end) {
			mid = start + (end - start) / 2;
			mid1 = mid + 1;
			midsquare = mid * mid;
			mid1square = mid1 * mid1;
			if (x >= midsquare && x < mid1square) {
				return mid;
			} else if (x >= mid1square) {
				start = mid1;
			} else if (x < midsquare) {
				end = mid-1;
			}
		}
		
		return -1;

	}
	
	
	public static int sqrtInt1(int x) {    
		int low = 0;    
		int high = x;
		int mid = 0;    
		while (true) {        
			mid = (low+high)/2;        
			if (x < mid * mid)            
				high = mid-1;        
			else if((mid+1)*(mid+1) <= x)            
				low = mid+1;        
			else //if(mid * mid <= x && x < (mid+1)*(mid+1))            
				break;   
			}        
		return  mid;
		
	}

}
