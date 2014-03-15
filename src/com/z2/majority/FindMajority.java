package com.z2.majority;

import java.util.Arrays;

/**
 * find majority in the array.
 * modified version of quicksort.
 * find median of the array.
 * selection Algorithm
 * find kth smallest element.
 * o(nlogn) can be done.
 * 
 * {1, 3, 3, 3, 3, 2, 2}
 *
 * if using selection algoirhtm to find median
 * it takes only O(n)
 * @author zhouzhou
 *
 */
public class FindMajority {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1, 3, 3, 3, 3, 2, 2};
		int res = majority(a);
		System.out.println("majority is " + res);
		
		int[] a1 = {1, 3, 4, 5, 3, 2, 2};
		int res1 = majority(a1);
		System.out.println("majority is " + res1);
		
		int[] a2 = {1,1,1,1,2,2,2,2};
		int res2 = majority(a2);
		System.out.println("majority is " + res2);
	}
	
	public static int majority(int[] a){
		if(a == null) throw new IllegalArgumentException();
		Arrays.sort(a);
		//then the majority element is in the middle.
		//if it is. then check if it is majority to see the 
		//count of the number.
		//for array length of 7, the length of majority should be 7/2 + 1;
		//4.
		//binary search to find the other number since it is already sorted.
		int majorityLen = a.length/2 + 1;
		int median = a[majorityLen];
		int index = Arrays.binarySearch(a, median);
		System.out.println("index " + index + " of median " + median);
		if(index + majorityLen -1 < a.length && a[index + majorityLen -1] == median){
			return median;
		}
		
		return Integer.MIN_VALUE;
	}

}
