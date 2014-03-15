
public class SearchForRange {
/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].
 * @param A
 * @param target
 * @return
 */

	public int[] searchRange(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        
        if(A == null || A.length == 0){
            return res;
        }
        
        int low=0, high=A.length -1;
        
        int mid = 0;
        while(low <= high){
            mid = low + (high -low)/2;
            
            if(A[mid] == target){
                if(mid == 0){
                    res[0] = mid; //this is the bottom.
                    break; //must terminate here since we found the left bound of the range.
                }else if(A[mid-1] == A[mid]){
                    high = mid -1; //more bottom range.
                }else{
                    res[0] = mid;  //this is the bottom.
                    break;
                }
            }else if(A[mid] > target){
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        
        
        low=0; 
        high=A.length -1;
        
        mid = 0;
        while(low <= high){
            mid = low + (high -low)/2;
            
            if(A[mid] == target){
                if(mid == A.length -1){
                    res[1] = mid;
                    break;
                }else if(A[mid + 1] == A[mid]){
                    low = mid  + 1;
                }else{
                    res[1] = mid;
                    break;
                }
            }else if(A[mid] > target){
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        
        return res;
    }
	
	/**
	 * use the idea of finding minumum matched element
	 * and maximum matched element.
	 * @param A
	 * @param target
	 * @return
	 */
	 public int[] searchRange1(int[] A, int target) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        int[] res = new int[2];
	        res[0] = -1;
	        res[1] = -1;
	        
	        if(A == null || A.length == 0){
	            return res;
	        }
	        
	        int low=0, high=A.length-1;
	        
	        int mid = 0;
	        
	        while(low < high){ //test for minimum range. don't use equal. reduce until one element left.
	            mid = low + (high -low)/2;
	            
	            if(A[mid] < target){//find the target element that greater than a[mid]
	                low = mid + 1; //grow the lower bound. find minimum
	            }else{
	                high = mid;
	            }
	        }
	        
	        if(A[low] != target) return res;
	        
	        res[0] = low;
	        
	        low = 0;
	        high=A.length-1;
	        
	        mid = 0;
	       
	        while(low < high){
	            mid = low + (high -low)/2 +1 ; //find maximum + 1 to avoid trapping.
	            
	            if(A[mid] > target){
	                high = mid -1; //lower upper bound to find maximumn
	            }else{
	                low = mid;
	            }
	        }
	        
	        res[1] = high;
	        
	        return res;
	    }

}
