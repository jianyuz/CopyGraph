package memoryVisibility;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
	      int [] data= {5,3,0,2,4,1,0,5,2,3,1,4}; 
	 
	      System.out.println("Before: " + Arrays.toString(data));
	      sort(data);
	      System.out.println("After:  " + Arrays.toString(data));
	   }
	
	public static void sort(int[] data){
		int n = data.length;
		for(int j=n-1; j >=1; j--){
			for(int i=0; i<j; i++){
				if(data[i] > data[i+1]){
					swap(data, i, i+1);
				}
			}
		}
	}
	
	public static void swap(int[] data, int i, int j){
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
}
