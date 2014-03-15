package com.z2.moderate;

import java.util.Arrays;
import java.util.Hashtable;

public class PairOfInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1, 3, 5, 7, 9,  6};
		pairInteger(a, 10);
		System.out.println("--");
		pairInteger1(a, 10);
	}

	
	public static void pairInteger(int [] a, int sum){
		Hashtable<Integer, Integer> map = new Hashtable<Integer, Integer>();
		for(int i=0; i< a.length; i++){
			if(map.get(a[i]) == null){
				map.put(sum-a[i], a[i]);
			}else{
				System.out.println(a[i] + " " + map.get(a[i]));
			}
		}
		
	}
	
	/**
	 * without using data structure.
	 * @param a
	 * @param sum
	 */
	public static void pairInteger1(int [] a, int sum){
		Arrays.sort(a);
		int aIndex = 0;
		int bIndex = a.length -1;
		
		while(aIndex < bIndex){
			if(a[aIndex] + a[bIndex] < sum){
				aIndex ++;
			}else if(a[aIndex] + a[bIndex] > sum){
				bIndex --;
			}else{
				System.out.println(a[aIndex] + " " + a[bIndex]);
				aIndex ++;
				bIndex --;
			}
		}
	}
	
	/**
	 * selection rank algorithm find kth smallest element in array.
	 * 
	 */
}
