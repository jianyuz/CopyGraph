
public class RemoveDuplicatesFromSortedArray {

	
	public static int removeDuplicates(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        int n = A.length;
        
        int end = n-1; 
        for(int i = n -1; i > 0; i--){
            //compare two elements. the current element and the previous one.
            if(A[i] == A[i-1]){
                //move all the elments after i forward by 1.
                for( int k = i ; k<end; k++){
                    A[k] = A[k+1];
                }
                end--;
            }
        }
        
        //number of unique elements.
        return end +1;
    }
	
	
	/**
	 * a better solution that move duplicate by chunks.
	 * @param A
	 * @return
	 */
	public int removeDuplicates1(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        int n = A.length;
        
        int end = n-1; 
        int dupCount = 0;
        for(int i = n -1; i > 0; i--){
            //compare two elements. the current element and the previous one.
            //calculate duplicate count.
            dupCount = 0;
            while(i> 0 && A[i] == A[i-1]){ //note the boundary check here to make sure i> 0.
                dupCount ++; 
                i--;
                
            }
            //move all the elments after i forward by duplicate count.
            if(dupCount > 0){ //only move when dupCount > 0.
                end-=dupCount; //remove dupCount from total.
                for( int k = i ; k<=end; k++){
                    A[k] = A[k+dupCount]; //move the trunk.
                    //k+dupCount is the start of unique sequence.
                }
                
            }
        }
        
        //number of unique elements.
        return end +1;
    }
	
	
	/**
	 * O(n) solution
	 * copy the unique sequnce to the front of the array.
	 * and a variable to keep track of the length of the array.
	 * @param args
	 */
	 public int removeDuplicates2(int[] A) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        //O(n) solution.
	        if(A== null || A.length ==0) return 0;
	        if(A.length == 1) return 1;
	        
	        int n = A.length;
	        
	        int size = 1;
	        for(int i=1; i< n; i++){
	            if(A[i] != A[size-1]){
	                A[size++] = A[i];
	            }
	        }
	        
	        return size;
	    }
	 
	 
	 /**
	  * Allow 2 duplicates.
	  * use dupCount to keep track of how many dups we have for now.
	  * O(n) complexity.
	  * @param A
	  * @return
	  */
	 public int removeDuplicates3(int[] A) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(A == null || A.length == 0 ) return 0;
	        if(A.length == 1) return 1;
	        
	        int n = A.length;
	        int len = 1;
	        
	        int dupCount = 0;
	        for(int i=1; i< n; i++){
	            if(A[i] == A[len-1]){
	                if(dupCount <1){
	                    A[len++] = A[i];
	                }
	                dupCount ++;
	            }else{
	                dupCount = 0;
	                A[len++] = A[i];
	            }
	        }
	        return len;
	    }
	 
	public static void main(String[] args){
		int[] A = new int[] {1,2};
		System.out.println(removeDuplicates(A));
	}
}
