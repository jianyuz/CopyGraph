package memoryVisibility;

public class BucketSort {
	
	public static void main(String[] args){
		int[] input = new int[] { 2, 4, 3, 9, 8, 7, 5};
		
		int[] buckets = new int[10];
		for(int i=0; i< buckets.length; i++){
			buckets[i] = -1;
		}
		for(int i: input){
			buckets[i] = i;
		}
		
		for(int i=0; i< buckets.length; i++){
			if(buckets[i] != -1)
			System.out.println(buckets[i]);
		}
	}

}
