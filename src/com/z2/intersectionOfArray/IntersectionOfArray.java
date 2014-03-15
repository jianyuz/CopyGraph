package com.z2.intersectionOfArray;

import java.util.ArrayList;
import java.util.List;

/**
 * intersection of two sorted arrays.
 * m*n
 * or m* log(n) binary search
 * or merge search.
 * O(m + n);
 * @author zhouzhou
 *
 */
public class IntersectionOfArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrayA = new int[] {1, 4, 6, 8, 15};
		int[] arrayB = new int[] {4, 6, 9, 10, 15, 21, 22, 24};
		List<Integer> res = intersection(arrayA, arrayB);
		System.out.println(res);
	}
	
	public static List<Integer> intersection(int[] arrayA, int[] arrayB){
		if(arrayA == null || arrayB == null){
			return null;
		}
		if(arrayA.length == 0 || arrayB.length == 0){
			return null;
		}
		int indexA = 0;
		int indexB = 0;
		List<Integer> res = new ArrayList<Integer>();
		while(indexA < arrayA.length && indexB < arrayB.length){
			if(arrayA[indexA] < arrayB[indexB]){
				indexA ++;
			}else if(arrayA[indexA] > arrayB[indexB]){
				indexB ++;
			}else{
				
				res.add(arrayA[indexA]);
				indexA ++;
				indexB ++;
			}
		}
		return res;
	}

}
