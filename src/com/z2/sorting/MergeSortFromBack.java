package com.z2.sorting;

import java.util.Arrays;

public class MergeSortFromBack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			int[] a = new int[10];
			a[0] = 4;
			a[1] = 5;
			a[2] = 7;
			a[3] = 8;
			a[4] = 9;
			
			int[] b = new int[]{1, 2, 3, 6, 10};
			mergeFromBack(a, b, 5);
			
			System.out.println(Arrays.toString(a));
	}

	private static void sortFromBack(int[] a, int aEnd, int[] b) {
		if(a == null || b== null) return;
		if(a.length != (b.length + aEnd + 1)){
			return;
		}
		
		int aIndex = aEnd;
		int bIndex = b.length -1;
		for(int i = a.length -1; i >=0; i--){
			if(aIndex >=0 && bIndex >=0){
				if(a[aIndex] > b[bIndex]){
					a[i] = a[aIndex];
					aIndex --;
				}else{
					a[i] = b[bIndex];
					bIndex --;
				}
			}else if(aIndex >=0){
				a[i] = a[aIndex];
				aIndex --;
			}else if(bIndex >=0){
				a[i] = b[bIndex];
				bIndex --;
			}
		}
	}
	
	public static void mergeFromBack(int [] a, int[] b, int m){
		if(a == null || b == null){
			return;
		}
		
		int aIndex = m -1;
		int bIndex = b.length -1;
		int mIndex = m + b.length -1;
		while(aIndex >=0 && bIndex >=0){
			if(a[aIndex] > b[bIndex]){
				a[mIndex--] = a[aIndex--];
			}else{
				a[mIndex--] = b[bIndex--];
			}
		}
		
		while(bIndex >=0){
			a[mIndex--] = b[bIndex --];
		}
		//a's has been all copied over. no need to check again.
	}

}
