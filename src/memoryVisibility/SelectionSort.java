package memoryVisibility;

import java.util.Arrays;

public class SelectionSort {

	 public static void main(String[] args) {
	      int [] data= {5,3,0,2,4,1,0,5,2,3,1,4}; 
	 
	      System.out.println("Before: " + Arrays.toString(data));
	      sort(data);
	      System.out.println("After:  " + Arrays.toString(data));
	   }
	 
	 public static void sort(int[] data){
		 int n = data.length;
		 //select the smallest in each round.
		 for(int i=0; i< n-1; i++){
			 int minIndex = i;
			 for(int j=i+1; j< n; j++){
				 if(data[j] < data[minIndex]){
					 minIndex = j;
				 }
			 }
			 swap(data, minIndex, i);
		 }
		 
	 }
	 
	 public static void swap(int[] data, int i, int j){
			int tmp = data[i];
			data[i] = data[j];
			data[j] = tmp;
		}
}
