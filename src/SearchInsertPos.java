
public class SearchInsertPos {

	/**
	 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0


binary search.
handle the boundary condition.
lower than start
hight than biggest elements.

	 * @param A
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        
        if(target <= A[0]) return 0;
        if(target > A[A.length -1]) return A.length;
        
        
        int low = 0, high = A.length-1;
        
        while(low <= high){
            
            int mid = low + (high - low)/2;
            
            if(A[mid] == target){
                return mid;
            }else if(A[mid] > target){
                high = mid -1; //search lower half.
            }else{
                low = mid +1; //search upper half.
            }
        }
        
        //low > high.
        return low;
    }
	public static void main(String[] args){
		int [] A = {1};
		System.out.println(searchInsert1(A, 2));
	}
	
	/**
	 * this approach doesn't handle the boundary condition.
	 * but keep track of middle
	 * and comapre with middle at last.
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	 public static int searchInsert1(int[] A, int target) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(A == null || A.length == 0) return 0;        
	        
	        int low = 0, high = A.length-1;
	        int mid = 0;
	        while(low <= high){
	            
	            mid = low + (high - low)/2;
	            
	            if(A[mid] == target){
	                return mid;
	            }else if(A[mid] > target){
	                high = mid -1; //search lower half.
	            }else{
	                low = mid +1; //search upper half.
	            }
	        }
	        
	        //keep the mid variable. at the last step it is low == high.
	        if(A[mid] < target){
	            return mid +1;
	        }else{
	            return mid;
	        }
	    }
}
