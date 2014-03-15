import java.util.HashMap;
import java.util.Map;


/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

another solution.
sort and
double pointer work toward middle
but need to return index so we need to sort with index.
need additional storage too.
 * @author zhouzhou
 *
 */

public class TwoSum {

	 public int[] twoSum(int[] numbers, int target) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        int[] res = new int[2];
	        
	        if(numbers == null || numbers.length == 0 || numbers.length == 1) return res;
	        
	        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
	        //map residual value with position.
	        
	        int n = numbers.length;
	        for(int i=0; i< n; i++){
	            m.put(target - numbers[i], i);
	        }
	        
	        for(int i=0; i< n; i++){
	            Integer resIndex = m.get(numbers[i]);
	            if(resIndex != null && resIndex > i){
	                res[0] = i+1;
	                res[1] = resIndex + 1;
	            }
	        }
	        
	        return res;
	        
	    }
}
