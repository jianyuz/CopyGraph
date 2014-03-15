
public class MergeSortedArray {

	/**
	 * merge B into A two sorted Array.
	 * @param A
	 * @param m
	 * @param B
	 * @param n
	 */
	 public void merge(int A[], int m, int B[], int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        int i = m-1;
	        int j = n-1;
	        
	        int count = m+n-1;
	        
	        while(i >=0 && j>=0){
	            if(A[i] > B[j]){
	                A[count] = A[i];
	                i--;
	            }else{
	                A[count] = B[j];
	                j--;
	            }
	            
	            count --;
	        }
	        
	        while(i>=0){
	            A[count--] = A[i--];
	        }
	        
	        while(j>=0){
	            A[count--] = B[j--];
	        }
	    }
}
