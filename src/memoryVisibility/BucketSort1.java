package memoryVisibility;

import java.util.Arrays;

/**
 * average case o(n+k)
 * 
 * @author zhouzhou
 *
 */
public class BucketSort1 {
	 public static void main(String[] args) {
	      int maxVal=5;
	      int [] data= {5,3,0,2,4,1,0,5,2,3,1,4}; 
	 
	      System.out.println("Before: " + Arrays.toString(data));
	      sort(data,maxVal);
	      System.out.println("After:  " + Arrays.toString(data));
	   }
	 
	 public static void sort(int[] data, int maxValue){
		 int[] bucket = new int[maxValue+1];
		 for(int i=0; i< data.length; i++){
			 bucket[data[i]] += 1;//use to keep track of the count of number appears
		 }
		 
		 for(int i=0, pos = 0; i< bucket.length; i++){
			 for(int j=0; j< bucket[i]; j++)
				 data[pos++] = i;
		 }
	 }

}
