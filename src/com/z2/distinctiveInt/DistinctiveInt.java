package com.z2.distinctiveInt;

/**
 * find distinctive int in sorted array
 * find distinctive abs int in sorted array
 * @author zhouzhou
 *
 */
public class DistinctiveInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] a = new int[] { -5, -4, -1,  2, 4, 4, 5, 6, 7, 7,8};
		int count = distinctiveCount(a);
		System.out.println(count);
		count = distincvieAbsCount(a);
		System.out.println(count);
	}

	private static int distincvieAbsCount(int[] a) {
		if(a == null || a.length == 0) return 0;
		int i = 0;
		int j = a.length -1;
		int cur = absMax(a, i, j);
		int counter = 1;
		while (i <= j){
			if(Math.abs(a[i]) == cur){
				i++;
			}else if (Math.abs(a[j]) == cur){
				j--; 
			}else {
				counter ++;
				cur = absMax(a, i, j);
			}
		}
		
		return counter;
	}
	
	private static int absMax(int[] a, int i, int j){
		return Math.max(Math.abs(a[i]), Math.abs(a[j])); 
	}

	private static int distinctiveCount(int[] a) {
		if(a == null || a.length ==0) return 0;
		
		int counter = 1;
		int cur = a[0];
		for(int i = 1; i< a.length; i++){
			if(a[i] != cur){
				counter ++;
				cur = a[i];
			}
		}
		return counter;
	}

}
