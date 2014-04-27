package memoryVisibility;

import java.util.Arrays;

/**
 * array a and array B
 * b has the buffer to hold all the elements.
 * @author zhouzhou
 *
 */
public class MergeArrays {
	public static void main(String[] args) {
		int[] a = new int[5];
		int[] t = new int[] { 2, 6, 9 };
		System.arraycopy(t, 0, a, 0, t.length);

		System.out.println(Arrays.toString(a));
		int[] b = new int[] { 3, 7 };

		mergeArrays(a, b, 3, 2);
		System.out.println(Arrays.toString(a));
		
		int [] k = {
		        8, 9, 11, 15, 17, 1, 3, 5, 12, 18
	    };
		
		orderArray(k, 0, 4, 9);
		System.out.println(Arrays.toString(k));
		
	}
	
	/**
	 * half array swap.
	 * 
	 * @param k
	 * @param s
	 * @param m
	 * @param e
	 */
	public static void orderArray(int[] k, int s, int m, int e){
		if( k == null) return;
		
		for(int i=s; i <= m; i++){
			if( k[i] > k[m+1]){
				swap( k, i, m+1);
				for(int j = m+1; j < e; j++){
					if(k[j] > k[j+1]){//swap adjacent elements.
						swap(k, j, j+1);
					}else{
						break;
					}
				}
			}
		}
	}
	
	public static void swap(int[] k, int i, int j){
		int tmp = k[i];
		k[i] = k [j];
		k[j] = tmp;
	}
	
	public static void mergeArrays(int[] a, int[] b, int m, int n){
		if(a == null ||  b== null) return ;
		
		
		int p = a.length -1;
		int i = m-1;
		int j = n-1;
		while(i >= 0 && j >= 0){
			
			if(a[i] > b[j]){
				a[p--] = a[i--];
			}else{
				a[p--] = b[j--];
			}
		}
		
		while(j >=0){
			a[p--] = b[j--];
		}
	}

}
