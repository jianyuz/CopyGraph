import java.util.ArrayList;


public class GrayCode {

	/**
	 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

reflective binary number
	 * @param n
	 * @return
	 */
	public ArrayList<Integer> grayCode(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        res.add(0);
        if(n == 0) return res;
        
        int size;
        for(int i=1; i<=n; i++){
            size = res.size();
                
            for(int j=size-1; j>=0; j--){
                res.add(res.get(j) | (1 << (i-1)));
            }
        }
        return res;
    }
	
	/**
	 * a faster method. 2 to N complexity.
	 * @param n
	 * @return
	 */
	
	public ArrayList<Integer> grayCode1(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int size = 1 << n;
        
        for(int i=0; i< size; i++){
            res.add( (i >> 1) ^ i);
        }
        return res;
    }
}
