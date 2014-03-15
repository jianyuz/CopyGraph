package com.z2.snapper;

public class Snapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(snapper(1,0));
		System.out.println(snapper(1,1));
		System.out.println(snapper(4, 0));
		System.out.println(snapper(4, 47));
		System.out.println(snapper1(4, 47));
	}
	
	/*
	 * n is the number of snappers chained, k is the number of snap clicks.
	 */
	public static boolean snapper(int n, int k){
		int res =  k;
		//binary mask
		int mask =0;
		for(int i=0; i< n; i++){
			mask += Math.pow(2,  i);
		}
		
		res = res&mask;
		
		int count = 0;
		while(res != 0){
			res = res & (res-1); //calculate the number of 1's
			count ++;
		}
		
		
		return (count == n);
		
	}
	
	/**
	 * compare if it is 2^n -1
	 * @param n
	 * @param k
	 * @return
	 */
	public static boolean snapper1(int n, int k){
		return (k& ((1<<n) - 1)) == ((1 <<n) -1);
	}

}
