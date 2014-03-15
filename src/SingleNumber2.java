import java.util.Arrays;


public class SingleNumber2 {
	
	public static void main(String[] args){
		int[] input = new int[]{0,1, 0, 1, 0, 1, 99};
		System.out.println(singleNumber(input));
	}
	public static int singleNumber(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        //what operation applies to three same number and gives 0.
        //110 ^ 110 ^ 110  110 the number itself. 
        if(A.length == 1){
            return A[0];
        }
        Arrays.sort(A);
        for(int i=0; i + 2< A.length;){
            int tmp = A[i] ^ A[i+1] ^ A[i+2];
            if(tmp == A[i] && tmp == A[i+1] && tmp == A[i+2]){
            	i = i+3;
                continue;
            }else{
                return tmp;
            }
        }
        return A[A.length-1]; //return the last element.
    }
	
	
	 public static int singleNumber2(int[] A) {
	        // Note: The Solution object is instantiated only once and is reused by each test case.
	        //what operation applies to three same number and gives 0.
	        //110 ^ 110 ^ 110  110 the number itself. 
	        if(A.length == 1){
	            return A[0];
	        }
	        
	           int ones = 0;  
	           int twos = 0;  
	           int not_threes, x;  
	         int n = A.length;
	           for (int i=0; i<n; ++i) {  
	                    x =  A[i];  
	                    twos |= ones & x;  
	                    ones ^= x;  
	                    not_threes = ~(ones & twos);  
	                    ones &= not_threes;  
	                    twos &= not_threes;  
	           } 
	           return ones;
	        
	    }
	 
	 
	 public static int singleNumber3(int[] A) {
	        // Note: The Solution object is instantiated only once and is reused by each test case.
	        //what operation applies to three same number and gives 0.
	        //110 ^ 110 ^ 110  110 the number itself. 
	        if(A.length == 1){
	            return A[0];
	        }
	        
	           int ones = 0;  
	           int twos = 0;  
	           int x;
	           int tmp;
	         int n = A.length;
	           for (int i=0; i<n; ++i) {  
	                    x =  A[i];
	                   tmp = ones; 
	                   ones = (ones ^ x) & ~twos;
	                   twos = (tmp & x) | (twos & ~x);
	                   
	                   
	           } 
	           return ones;
	        
	    }

}
