import java.util.Arrays;


public class FirstMissingPositive {

	public static void main(String[] args){
		int[] A = {1, 1};
		System.out.println(firstMissingPositive(A));
	}
	
	public static int firstMissingPositive(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 1;
        
        //find first missing positive. among unsorted integer array.
        //don't care about the negative number in the array.
        
        //since O(n) requirment, use hash.
        //use array itself as hash.
        //use MIN_VALUE as the marker.
        
        
        for(int i=0; i<A.length; i++){
            while(A[i] > 0){
                if(A[i] <= A.length){//only mark in hash range.
                	int index = A[i]-1; //remember the index position.
                    int tmp = A[index];
                    A[index] = Integer.MIN_VALUE; //mark it as shown.
                    if(i != index && tmp !=Integer.MIN_VALUE ) A[i] = tmp; //conditional swap. 
                    //index not the same and it hasn't been marked.
                    else if(tmp == Integer.MIN_VALUE){ // if it has, the current item is useless.
                    	A[i] = 0;
                    }
                }else{
                    //go byond the range.
                    A[i] = 0; //mark it as irrelavent.
                }
            }            
        }
        
        System.out.println(Arrays.toString(A));
        int res = 1;
        for(int i=0; i< A.length; i++){
            if(A[i] != Integer.MIN_VALUE) break;
            else res ++;
        }
        return res;
    }
	
	/**
	 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
	 * @param A
	 * @return
	 */
	 public int firstMissingPositive1(int[] A) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(A == null || A.length == 0) return 1;
	        
	        //find first missing positive. among unsorted integer array.
	        //don't care about the negative number in the array.
	        
	        //since O(n) requirment, use hash.
	        //use array itself as hash.
	        
	        int n = A.length;
	        
	        for(int i=0; i< n; i++){
	            if(A[i] <= 0){
	                A[i] = n+ 2; //convert all negative number  or zeor as some postive number out of range.
	            }
	        }
	        
	        //to avoid swapping and looping.
	        //if encounter a number, take absolute avlue.
	        //we still keep the position indicator if it is in the range n.
	        //if the number is in range 1, n.
	        //we actively set it as negative number.
	        // lastly, we count the negative numbers.
	        for(int i=0; i< n; i++){
	           int val = A[i];
	           int absVal = Math.abs(val);
	           if(absVal <= n){
	               A[absVal -1 ] = - Math.abs(A[absVal -1]);
	           }
	        }
	        
	        int res = 1;
	        for(int i=0; i< A.length; i++){
	            if(A[i] >=0 ) break;
	            else res ++;
	        }
	        return res;
	    }
}
