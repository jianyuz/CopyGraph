import java.util.Arrays;


public class NextPermutation {

	/**
	 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
	 * @param num
	 */
	 public void nextPermutation(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(num == null || num.length == 0) return;
	        
	        //thoughts.
	        //scan from right to left.
	        //find the element that sammller than right.
	        //switch it with the next bigger element in the right subarray.
	        //then in place sort of the right subarray.
	        
	        int n = num.length;
	        
	        int curMinIndex = -1;
	        int curMin = Integer.MAX_VALUE;
	        
	        boolean found = false;
	        
	        for(int i = n-1; i>=0; i--){
	            if(i < n-1 && num[i] < num[i+1]){
	                
	                for(int j = i+1; j < n; j++){ //should search from back to front.
	                	//find first one that greater then num[i].
	                    if(num[j] > num[i] && num[j] < curMin){
	                        curMin = num[j];
	                        curMinIndex = j;
	                    }
	                }
	                //switch the element with elelment with curMinIndex
	                int tmp = num[i];
	                num[i] = num[curMinIndex];
	                num[curMinIndex] = tmp;
	                
	                Arrays.sort(num, i+1, n);//can be replaced with reverse.
	                found = true;
	                break;
	            }
	            
	            
	        
	        }
	        
	        if(!found){
	            Arrays.sort(num);//can be replaced with reverse.
	        }
	    }
	 
	 /**
	  * key use the order information to avoid sorting.
	  * @param num
	  */
	 public void nextPermutation1(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(num == null || num.length == 0) return;
	        
	        //thoughts.
	        //scan from right to left.
	        //find the element that sammller than right.
	        //switch it with the next bigger element in the right subarray.
	        //do the search from right to left. note
	        //then in place reverse the right subarray. they are in descending order.
	        
	        int n = num.length;
	        
	        for(int i = n-2; i>=0; i--){
	            if(num[i] < num[i+1]){
	                
	                //it is O(n) solution, since we are not iterating full N2.
	                for(int j = n-1; j >= 0 ; j-- ){
	                    if(num[j] > num[i]){
	                        swap(num, i, j);
	                        break;
	                    }
	                }
	                
	                reverse(num, i+1, n-1);
	                return;
	            }
	        
	        }
	        
	        //already in descending order.
	        //reverse the whole array.
	        reverse(num, 0, n-1);
	    }
	    
	    public void swap(int[] num, int i, int j){
	        int tmp = num[i];
	        num[i] = num[j];
	        num[j] = tmp;
	    }
	    
	    public void reverse(int[] num, int i, int j){
	        while( i<j){
	            swap(num, i, j);
	            i++;
	            j--;
	        }
	    }
}
