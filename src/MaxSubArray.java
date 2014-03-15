
public class MaxSubArray {

	/**
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.


	 * @param A
	 * @return
	 */
	 public static  int maxSubArray(int[] A) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(A == null || A.length == 0) {
	            throw new IllegalArgumentException("invalid input");
	        }
	        
	        //one dimensional DP.
	        //if sum is greater or equal then 0.
	        //continue to use it to accumulate sum
	        //for [0, i+1], if smaller than 0, just
	        //recumulate here by setting sum = 0;
	        int maxSum = Integer.MIN_VALUE, sum = 0;
	        int start = 0;//add start and mstart end to record start end location of the sequence.
	        int mStart = -1;
	        int mEnd = -1;
	        for(int i=0; i<A.length; i++){
	            sum += A[i];
	            //maxSum = Math.max(sum, maxSum);
	            if(sum > maxSum){
	            	maxSum = sum;
	            	mStart = start;
	            	mEnd = i;
	            }
	            
	            if(sum < 0) {//alredy make it negative, no dp.recumulate.
	            	sum = 0;
	            	start = i+1; 
	            }
	        }
	        System.out.println(" " + mStart + " " + mEnd);
	        return maxSum;
	    }
	 
	 public static void main(String[] args){
		 int[] A = {1,2,-1,-2,2,1,-2,1};
		 System.out.println(maxSubArray(A));
		 
		 int[] B = {-2,1,-3,4,-1,2,1,-5,4};
		 System.out.println(maxSubArray1(B));
		 
		 int[] C = {-2, 1};
		 System.out.println(maxSubArray(C));
	 }
	 
	 /**
	  * alternative way divide and conquer.
	  * nLogN using master theorem.
	  * 
	  * @param A
	  * @return
	  */
	 public static int maxSubArray1(int[] A) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(A == null || A.length == 0) {
	            throw new IllegalArgumentException("invalid input");
	        }
	        return calculateMax(A, 0, A.length -1);
	        
	    }
	    
	    public static int calculateMax(int[] A, int start, int end){
	        if(start > end){
	            return Integer.MIN_VALUE;
	        }
	        
	        if(start == end){
	            return A[start];
	        }
	        
	        int mid = start + (end - start)/2;
	        
	        int r1 = calculateMax(A, start, mid);
	        int r2 = calculateMax(A, mid + 1, end);
	        
	        int maxSum = Math.max(r1, r2);
	        
	        int sum = 0, lmax = Integer.MIN_VALUE;
	        for(int i=mid; i>=start; i--){
	            sum += A[i];
	            if(sum > lmax){
	                lmax = sum;
	            }
	        }//calculate left max starting from mid
	        
	        sum = 0;
	        int rmax = Integer.MIN_VALUE;
	        for(int i=mid+1; i<=end; i++){
	            sum += A[i];
	            if(sum > rmax){
	                rmax = sum;
	            }
	        }//right max starting from mid+1
	        
	        return Math.max(maxSum, lmax+rmax);
	        
	    }
}
