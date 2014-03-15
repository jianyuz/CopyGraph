package com.z2.t24;

/**
 * Find an element in rotated/sorted array
 * doesn't work for array with duplicate.
 * @author zhouzhou
 *
 */
public class FindElementInRotatedSortArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] sample = { 3, 4, 5, 1, 2};
        System.out.println(findElementInRArray(sample, 1));
        int[] sample1 = { 3, 4, 5, 1, 2, 3};
        System.out.println(findElementInRArray(sample1, 3));
        int[] sample2 = { 3, 4, 5, 0, 1, 2, 3};
        System.out.println(findElementInRArray(sample2, 2));
        int[] sample3 = { 3, 4, 5};
        System.out.println(findElementInRArray(sample3, 7));
        int[] sample4 = { 3};
        System.out.println(findElementInRArray(sample4, 3));
        int[] sample5 = { 3, 5};
        System.out.println(findElementInRArray(sample5, 6));
        int[] sample6 = {6, 1, 2, 3, 5};
        System.out.println(findElementInRArray(sample6, 1));
        int[] sample7 = {3, 1, 2, 2, 3};
        System.out.println(findElementInRArray(sample7, 3));
        int[] sample8 = {1, 1, 1, 1, 2, 1};
        System.out.println(findElementInRArray(sample8, 2)); //last case doesn't work cause of the duplicate.
        
        /*
         * 3
         * 0
         * 5
         * -1
         * 0
         * -1
         * 1
         * 4
         * 1
         */
	}
	
	public static int findElementInRArray(int[] array, int element){
		if(array == null){
    		throw new IllegalArgumentException("array can't be null");
    	}
    	
        int len = array.length;
        
        if(len == 0){
            throw new IllegalArgumentException("Array needs to have at least one element");
        }
        
		if (len == 1) {
			if (array[0] == element) {
				return 0;
			}else{
				return -1;
			}
		}
        
        int low = 0;
        int high = len-1;
        int mid;
        int vMid, vHigh, vLow;
        while(low <= high){
            mid = low + (high-low)/2; 
            vMid = array[mid];
            vHigh = array[high];
            vLow = array[low];
            if(element == vMid){
            	return mid;
            }
            
            if(vMid <= vHigh){ //upper body is sorted.
            	if(element >vMid && element <=vHigh){
            		low = mid+1;
            	}else{
            		high = mid -1;
            	}
            }else{//lower body is sorted.
            	if(vLow <= element && element < vMid){
            		high = mid -1;
            	}else{
            		low = mid + 1;
            	}
            	
            }
            /*
            if((element < vMid && vMid< vHigh) ){
                high = mid-1; //motonic lower half
            }else{
                low = mid +1 ; //rotated upper half
            }*/
            //System.out.println("low" + low + " high " + high);
        }
        return -1;
        
	}
	

}
