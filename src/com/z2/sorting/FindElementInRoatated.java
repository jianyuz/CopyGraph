package com.z2.sorting;

public class FindElementInRoatated {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[] { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		System.out.println(findElement(a, 5, 0, a.length));
		System.out.println(findElement(a, 15, 0, a.length));
		System.out.println(findElement(a, 14, 0, a.length));
		System.out.println(findElement(a, 1, 0, a.length));
		System.out.println(findElement(a, 28, 0, a.length));
		System.out.println(findElement(a, 7, 0, a.length));
	}
	
	public static int findElement(int[] r, int e, int l, int h){
		if(r == null) return -1;
		while (l <= h) {
			int m = l + (h - l) / 2;
			if (r[m] == e) {
				return m;
			}else if(r[m] >= r[l]) { //m > left
				if(e > r[m]){
					l = m + 1;
				}else if( e < r[l]){
					l = m+1;
				}else{
					h = m-1;
				}
			}else{
				if(e < r[m]){
					h = m-1;
				}else if(e >= r[l]){
					h = m-1;
				}else{
					l = m+1;
				}
			}

		}
		return -1;
	}

}
