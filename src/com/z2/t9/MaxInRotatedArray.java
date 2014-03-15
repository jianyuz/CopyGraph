package com.z2.t9;

/**
 * Find maximum in sorted/rotated array.
 * @author zhouzhou
 *
 */
public class MaxInRotatedArray{

    public static void main(String[] args){
        int[] sample = { 3, 4, 5, 1, 2};
        System.out.println(findMaxInRArray(sample));
        int[] sample1 = { 3, 4, 5, 1, 2, 3};
        System.out.println(findMaxInRArray(sample1));
        int[] sample2 = { 3, 4, 5, 0, 1, 2, 3};
        System.out.println(findMaxInRArray(sample2));
        int[] sample3 = { 3, 4, 5};
        System.out.println(findMaxInRArray(sample3));
        int[] sample4 = { 3};
        System.out.println(findMaxInRArray(sample4));
        System.out.println("-----");
        int[] sample5 = { 3, 4, 5, 1, 2};
        System.out.println(findMinInRArray(sample5));
        int[] sample6 = { 3, 4, 5, 1, 2, 3};
        System.out.println(findMinInRArray(sample6));
        int[] sample7 = { 3, 4, 5, 0, 1, 2, 3};
        System.out.println(findMinInRArray(sample7));
        int[] sample8 = { 3, 4, 5};
        System.out.println(findMinInRArray(sample8));
        int[] sample9 = { 3};
        System.out.println(findMinInRArray(sample9));
    }
    public static int findMaxInRArray(int[] array){
        
    	if(array == null){
    		throw new IllegalArgumentException("array can't be null");
    	}
    	
        int len = array.length;
        
        if(len == 0){
            throw new IllegalArgumentException("Array needs to have at least one element");
        }
        if(len == 1){
            return array[0];
        }
        if(len == 2){
            return Math.max(array[0], array[1]);
        }
        
        int low = 0;
        int high = len-1;
        int mid;
        int vLow, vMid;
        while(low < high){
            mid = low + (high-low)/2 + 1; //need to add 1 here to make sure it grows.
            vLow = array[low];
            vMid = array[mid];
            
            if(vLow > vMid){
                high = mid -1; //rotated lower half
            }else{
                low = mid; //motonic upper half
            }    
        }
        
         return array[high];//high or low. the same here.    
    }
    
 public static int findMinInRArray(int[] array){
        
    	if(array == null){
    		throw new IllegalArgumentException("array can't be null");
    	}
    	
        int len = array.length;
        
        if(len == 0){
            throw new IllegalArgumentException("Array needs to have at least one element");
        }
        if(len == 1){
            return array[0];
        }
        if(len == 2){
            return Math.max(array[0], array[1]);
        }
        
        int low = 0;
        int high = len-1;
        int mid;
        int vHigh, vMid;
        while(low < high){
            mid = low + (high-low)/2; //need to add 1 here to make sure it grows.
            vHigh = array[high];
            vMid = array[mid];
            
            if(vHigh > vMid){
                high = mid; //monotic mid above
            }else{
                low = mid+1; //rotated upper part
            }    
        }
        
         return array[high];//high or low. the same here.    
    }
}