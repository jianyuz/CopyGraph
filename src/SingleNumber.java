
public class SingleNumber {

	/**
	 * Given an array of integers, every element appears twice except for one. Find that single one.

Could you implement it without using extra memory?
	 * @param args
	 */
	public static void main(String[] args){
		int[] input = new int[] {2, 2, 1};
		System.out.println(Integer.toBinaryString(singleNumber(input)));
	}
	
	public static int singleNumber(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        
        if(A.length == 1){
            return A[0];
        }
        
        int res = A[0];
        for(int i=1; i< A.length; i++){
            res ^= A[i];
        }
        
        return res;
    }
}
