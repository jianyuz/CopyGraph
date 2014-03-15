package com.z2.localminimum;

/**
 * external merge sort
 * sort each independently
 * then load 9 chunks plus output buffer in memory.
 * 
 * 
 * @author zhouzhou
 *
 */
public class LocalMinimum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {2, 1, 4, 5, 3, 7, 8, 9, 6, 5, 7, 8};
		System.out.println(localMinimum(array));
		int[] array1 = {8, 7, 6, 5, 4, 7, 8, 9, 6, 5, 7, 8};
		System.out.println(localMinimum(array1));
		int[] array2 = {6, 3, 4, 5, 3, 2, 1, 0, 9, 10, 11};
		System.out.println(localMinimum(array2));
		int[] array3 = {1, 2, 3, 4, 5, 7};
		System.out.println(localMinimum(array3));
		int[] array4 = {3, 2, 3, 4, 5};
		System.out.println(localMinimum(array4));
	}
	
	public static int localMinimum(int[] a){
		if(a == null || a.length < 3){
			throw new IllegalArgumentException();
		}
		if(a[0] <= a[1]){
			return a[0];
		}
		if(a[a.length-2] >= a[a.length-1]){
			return a[a.length-1];
		}
		return localMinimum(a, 0, a.length -1);
	}
	
	/**
	 * draw diagram to see all the scenario
	 * one end is lower
	 * or mid is lower
	 * neighor of mid element
	 * increasing order
	 * decreasing order
	 * or both side got a local min
	 * @param a
	 * @param start
	 * @param end
	 * @return
	 */

	public static int localMinimum(int[] a, int start, int end){
		
		int mid = start + (end-start)/2;
		if(a[start] < a[mid] && (mid-start) >=2){
			return localMinimum(a, start, mid);
		}else if(a[mid] > a[end] && (end-mid) >=2){
			return localMinimum(a, mid, end);
		}else{ //a[mid] < a[start] and a[end]
			if(a[mid-1] >= a[mid] && a[mid] <= a[mid+1]){
				return a[mid];
			}else if(a[mid-1] < a[mid] && a[mid] < a[mid+1]){
				return localMinimum(a, start, mid);
			}else if(a[mid-1] > a[mid] && a[mid] > a[mid+1]){
				return localMinimum(a, mid, end);
			}else{
				if(mid-start >=2){
					return localMinimum(a, start, mid);
				}else{
					return localMinimum(a, mid, end);
				}
			}
			
		}
		
		
	}

}
