package com.z2.t12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * determine if two arrays are permutation of each other.
 * use hashmap to trade of space for run time.
 * constant space solution?
 * 
 * @author zhouzhou
 *
 */

public class KthSmallest {

	public static void main(String[] args) {
		Integer[] arrayA = new Integer[] { 3, 8, 11, 25, 56 };
		Integer[] arrayB = new Integer[] { 1, 13, 15, 26, 78, 81, 90 };

		int k = 7;

		int refRes = refKSmallest(arrayA, arrayB, k);
		int res1 = kSmallest1(arrayA, arrayB, k);
		int res = kSmallest(arrayA, arrayB, k);
		int res2 = findKthElement(arrayA, arrayB,  k);
		System.out.println("refResult: " + refRes + " res:" + res + " res1: " + res1 + " res2: " + res2);

		arrayA = new Integer[] { 3, 8, 11, 25, 56 };
		arrayB = new Integer[] { 78, 81, 90, 100, 150, 170 };

		k = 7;

		refRes = refKSmallest(arrayA, arrayB, k);
		res = kSmallest(arrayA, arrayB, k);
		res1 = kSmallest1(arrayA, arrayB, k);
		res2 = findKthElement(arrayA, arrayB,  k);
		System.out.println("refResult: " + refRes + " res:" + res + " res1 " + res1 + " res2 " + res2);

		arrayA = new Integer[] { 3 };
		arrayB = new Integer[] { 78, 81, 90, 100, 150, 170 };

		k = 7;

		refRes = refKSmallest(arrayA, arrayB, k);
		res1 = kSmallest1(arrayA, arrayB, k);
		res = kSmallest(arrayA, arrayB, k);
		res2 = findKthElement(arrayA, arrayB,  k);
		System.out.println("refResult: " + refRes + " res:" + res + " res1 " + res1 + " res2 " + res2);
		
		k = 1;

		refRes = refKSmallest(arrayA, arrayB, k);
		res1 = kSmallest1(arrayA, arrayB, k);
		res = kSmallest(arrayA, arrayB, k);
		res2 = findKthElement(arrayA, arrayB,  k);
		System.out.println("refResult: " + refRes + " res:" + res + " res1 " + res1 + " res2 " + res2);
		
		k = 3;
		arrayA = new Integer[] { 3 , 5};
		arrayB = new Integer[] {  4, 78, 81, 90, 100, 150, 170 };
		
		refRes = refKSmallest(arrayA, arrayB, k);
		res1 = kSmallest1(arrayA, arrayB, k);
		//res = kSmallest(arrayA, arrayB, k);
		res2 = findKthElement(arrayA, arrayB,  k);
		System.out.println("refResult: " + refRes + " res:" + res + " res1 " + res1 + " res2 " + res2);
	}

	public static int refKSmallest(Integer[] arrayA, Integer[] arrayB, int k) {
		List<Integer> resList = new ArrayList<Integer>();
		resList.addAll(Arrays.asList(arrayA));
		resList.addAll(Arrays.asList(arrayB));
		Collections.sort(resList);
		return resList.get(k - 1);
	}

	/**
	 * use array index iteration.
	 * 
	 * @param arrayA
	 * @param arrayB
	 * @param k
	 * @return
	 */
	public static int kSmallest1(Integer[] arrayA, Integer[] arrayB, int k) {
		if (arrayA == null || arrayB == null) {
			return -1;
		}
		if (arrayA.length == 0 || arrayB.length == 0) {
			return -1;
		}

		if (k > (arrayA.length + arrayB.length)) {
			return -1;
		}

		int indexA = -1;
		int indexB = -1;
		
		/**
		 * consider the case that we run out of one of the array.
		 */
		while (indexA < arrayA.length && indexB < arrayB.length) {
			if (indexA + indexB + 2 == k) {
				if(indexA > indexB){
					return arrayA[indexA];
				}else{
					return arrayB[indexB];
				}
			}

			if (indexA == -1 || indexB == -1) {
				if(indexA == -1 && indexB == -1){
				if (arrayA[0] > arrayB[0]) {
					indexB++;
				} else {
					indexA++;
				}}else if(indexA == -1){
					if(arrayA[0] < arrayB[indexB]){
						indexA ++;
					}else{
						indexB ++;
					}
				}else{
					if(arrayB[0] < arrayA[indexA]){
						indexB ++;
					}else{
						indexA ++;
					}
				}
			} else {
				if (arrayA[indexA] < arrayB[indexB]) {
					indexA++;
				} else if (arrayA[indexA] > arrayB[indexB]) {
					indexB++;
				}
			}
		}
		
		if(indexA < arrayA.length){
			return arrayA[k - indexB - 1];
		}
		
		if(indexB < arrayB.length){
			return arrayB[k- indexA -1];
		}
		return -1;
	}

	/**
	 * O(lnM + ln(n)
	 * first all the special cases.
	 * empty array
	 * both empty
	 * one is empty
	 * 
	 * arrayA end element > arrayB start element.
	 * 
	 * @param arrayA
	 * @param arrayB
	 * @param k
	 * @return
	 */
	public static int kSmallest(Integer[] arrayA, Integer[] arrayB, int k) {
		if (arrayA == null && arrayB == null) {
			throw new IllegalArgumentException("invalid array input");
		}

		int lenA = ((arrayA == null) ? 0 : arrayA.length);
		int lenB = ((arrayB == null) ? 0 : arrayB.length);

		if (k > lenA + lenB) {
			throw new IllegalArgumentException("invalid k input");
		}

		if (arrayA.length == 0) {
			return arrayB[k - 1];
		}
		if (arrayB.length == 0) {
			return arrayA[k - 1];
		}
		if (arrayA[arrayA.length - 1] < arrayB[0]) {
			if (k > arrayA.length) {
				return arrayB[k - arrayA.length - 1];
			} else {
				return arrayA[k - 1];
			}
		}
		if (arrayB[arrayB.length - 1] < arrayA[0]) {
			if (k > arrayB.length) {
				return arrayA[k - arrayB.length - 1];
			} else {
				return arrayB[k - 1];
			}
		}

		// pick i and j index in Array A and ArrayB
		// make sure the sum of them i+j=k
		int as = 0;
		int ae = arrayA.length - 1;

		int bs = 0;
		int be = arrayB.length - 1;
		
		//keep track of aStart aend
		//and bstart and bEnd
		//maintain i + j = k +1,
		//since 0 i and 0 j k+1 elements
		

		k = k + 1; // i + j = k+1;
		// get the element of k smallest.
		while (k > 0) {
			lenA = ae - as + 1;
			lenB = be - bs + 1;

			if (lenA == 0) {
				return arrayB[bs + k - 2];
			}

			if (lenB == 0) {
				return arrayA[as + k - 2];
			}

			int i = (int) (((float) lenA / (lenA + lenB)) * k);
			int j = k - i;

			i = i + as;
			j = j + bs;

			int Ai_2 = (i - 2 >= 0) ? arrayA[i - 2] : Integer.MIN_VALUE;
			int Bj_2 = (j - 2 >= 0) ? arrayB[j - 2] : Integer.MIN_VALUE;
			int Ai_1 = (i - 1 >= 0) ? ((i - 1 < arrayA.length) ? arrayA[i - 1]
					: Integer.MAX_VALUE) : Integer.MIN_VALUE;
			int Bj_1 = (j - 1 >= 0) ? ((j - 1 < arrayB.length) ? arrayB[j - 1]
					: Integer.MAX_VALUE) : Integer.MIN_VALUE;

			if (Ai_2 < Bj_1 && Bj_1 < Ai_1) {
				return arrayB[j - 1];
			}

			if (Bj_2 < Ai_1 && Ai_1 < Bj_1) {
				return arrayA[i - 1];
			}

			if (Ai_1 > Bj_1) {
				// discard lower half of arrayB element.
				// and upper half of arrayA.
				// k = k-j
				k = k - (j - bs);
				bs = j;
				ae = i - 1;

			} else {
				// discard lower half of arrayA and upper half of arrayB.
				// k = k -i;
				k = k - (i - as);
				as = i;
				be = j - 1;
			}
			System.out.println("k is " + k + " i " + i + " j " + j);
			System.out.println("A search range: " + as + "-" + ae);
			System.out.println("B search range: " + bs + "-" + be);
		}
		// then subdivide the array to binary search the result.

		return -1;
	}
	
	/**
	 * remember to handle the special cases.
	 * empty array
	 * zero length array
	 * length sum > k
	 * end element < start element.
	 * then compare k with length.
	 * 
	 * 
	 * how about find kth smallest among n server
	 * each server contains a list of numbers.
	 * sort on each server first O(nlogn)
	 * keep k in the main server compare and get kth element with each server.
	 * 
	 * @param arrayA
	 * @param arrayB
	 * @param k
	 * @return
	 */
	public static int findKthElement(Integer[] arrayA, Integer[] arrayB, int k) {
		if (arrayA == null && arrayB == null) {
			throw new IllegalArgumentException("invalid array input");
		}

		int lenA = ((arrayA == null) ? 0 : arrayA.length);
		int lenB = ((arrayB == null) ? 0 : arrayB.length);

		if (k > lenA + lenB) {
			throw new IllegalArgumentException("invalid k input");
		}

		if(arrayA.length == 0 && arrayB.length == 0){
			return -1;
		}
		if (arrayA.length == 0) {
			return arrayB[k - 1];
		}
		if (arrayB.length == 0) {
			return arrayA[k - 1];
		}
		if (arrayA[arrayA.length - 1] <= arrayB[0]) {
			if (k > arrayA.length) {
				return arrayB[k - arrayA.length - 1];
			} else {
				return arrayA[k - 1];
			}
		}
		if (arrayB[arrayB.length - 1] <= arrayA[0]) {
			if (k > arrayB.length) {
				return arrayA[k - arrayB.length - 1];
			} else {
				return arrayB[k - 1];
			}
		}
		
		return findKthElement(arrayA, 0, arrayA.length-1, arrayB, 0, arrayB.length-1, k);
	}
	
	public static int findKthElement(Integer A[], int as, int ae, Integer B[], int bs, int be, int k) { 
		//System.out.println("A range: " + as + " - " + ae);
		//System.out.println("B range: " + bs + " - " + be);
		//System.out.println("K " + k);
		int m = ae - as + 1;
		int n = be - bs + 1;
		int i = (int)(((float)m)/(m+n)*(k -1));
		int j = (k-1) - i;
		i = as + i;
		j = bs + j;
		//System.out.println("i " + i + " j " + j);
		//A[i] or B[j] is the Kth element, return it    
		if ((j <= 0 || B[j-1] < A[i]) && (j >= n || A[i] < B[j]))        
			return A[i];    
		if ((i <= 0 || A[i-1] < B[j]) && (i >= m || B[j] < A[i]))        
			return B[j];        
		//A[i] is too small, get rid of lower part of A and higher part of B    
		if (0 < j && A[i] < B[j-1]){    
			//System.out.println("a " + i + " <  b " + (j-1));
			return findKthElement(A, i+1, ae, B, bs, j-1, k-i-1);  
		}
		//B[j] is too small, get rid of higher part of A and lower part of B    
		else {//if(i > 0 && B[j] < A[i-1])  
			//System.out.println("b " + j + " <  a " + (i-1));
			return findKthElement(A, as, i-1, B, j+1, be, k-j-1);
		}
	}

}
