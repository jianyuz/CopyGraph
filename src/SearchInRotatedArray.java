
public class SearchInRotatedArray {

	public static int search(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if( A == null) return -1;
        if( A.length == 0) return -1;
        
        int n = A.length;
        
        return helper(A, 0, n-1, target);
    }
    
	//note terminate condition low > high.
	//low target can be >= A[low]
    private static int helper(int[] A, int low, int high, int target){
        if(low > high) return -1;
        
        int mid = low + (high - low)/2;
        if(A[mid] == target){
            return mid;
        }else if( target < A[mid]){
            if(target >= A[low]) return helper(A, low, mid-1, target);
            else if(A[low] <= A[mid]) return helper(A, mid+1, high, target); //check monocity.
            else return helper(A, low, mid-1, target);
        }else {
            if(target <= A[high]) return helper(A, mid+1, high, target);
            else if(A[mid] <= A[high])return helper(A, low, mid-1, target); //check monocity.
            else return helper(A, mid+1, high, target);
        }
        
    }
    
    /**
     * iterative version.
     * note that low <= high
     * @param A
     * @param target
     * @return
     */
    public int search1(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(A == null) return -1;
        if(A.length == 0) return -1;
        
        int low = 0;
        int high = A.length -1;
        
        int mid;
        while(low <= high){
            mid = low + (high - low)/2;
            if(A[mid] == target) {
                return mid;
            }else if(A[low]<= A[mid]){
                //lower half is sorted.
                if(A[low] <= target && target < A[mid]){ //if element is in sorted range.
                    high = mid -1;
                }else{
                    low = mid + 1;
                }
            }else {
                if(A[mid] < target && target <= A[high]){ //upper half is sorted.
                    low = mid + 1;
                }else{
                    high = mid -1;
                }
            }
        }
        return -1;
    }
    
    /**
     * search in rotated array
     * allow duplicates.
     * worst case O(n).
     * 
     * 13111 example.
     * 
     * @param A
     * @param target
     * @return
     */
    public boolean search2(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(A == null) return false;
        if(A.length == 0) return false;
        
        int low = 0;
        int high = A.length -1;
        
        int mid;
        while(low <= high){
            mid = low + (high - low)/2;
            if(A[mid] == target) {
                return true;
            }else if(A[low]< A[mid]){ //it is possible that low mid and high elements are all equals.
                //lower half is sorted.
                if(A[low] <= target && target < A[mid]){ //if element is in sorted range.
                    high = mid -1;
                }else{
                    low = mid + 1;
                }
            }else if(A[mid] < A[high]){
                if(A[mid] < target && target <= A[high]){ //upper half is sorted.
                    low = mid + 1;
                }else{
                    high = mid -1;
                }
            }else if(A[low] == A[mid]){
                if(A[high] != A[mid]){//doesn't continue duplicate to high.
                    //search in the high part.
                    low = mid + 1;
                }else{
                    low = low +1; //move low by 1.
                    high = high -1; //reduce high by 1. sincw we don't know now.
                }
                
            }else if(A[mid] == A[high]){
                if(A[low] != A[mid]){//doesn't continue duplicate to low.
                    //search in low part.
                    high = mid -1;
                }else{
                    low = low + 1;
                    high = high -1;
                }
            }
            
        }
        return false;
    }
    public static void main(String[] args){
    	int [] A = new int[] {1, 3};
    	System.out.println(search(A, 3));
    }
}
