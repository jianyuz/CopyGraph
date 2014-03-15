import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ? b ? c)
The solution set must not contain duplicate triplets.

 * @author zhouzhou
 *
 */
public class ThreeSum {

	   public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(num == null || num.length == 0) return res;
	        
	        Arrays.sort(num);
	        
	        int n = num.length;
	        
	        for(int i=0; i< n-2; i++){//go the way to n-2 , not to n.
	        	//not enough number.
	            int diff = 0 - num[i];
	            if(i > 0 && num[i] == num[i-1]) continue;//guarantee the uniquenes
	            //map residual to original number's index
	            //make sure we don't have index mapped back to itself.
	            Map<Integer, Integer> m = new HashMap<Integer, Integer>();
	            
	            for(int j = i+1; j< n; j++){
	                m.put(diff - num[j], j);
	            }
	            
	            for(int k= i+1; k<n; k++){
	                if(k > i+1 && num[k] == num[k-1]) continue; //this is needed to.
	                Integer tmp = m.get(num[k]); 
	                if(tmp != null && tmp != k && num[tmp] >= num[k]){
	                    ArrayList<Integer> part = new ArrayList<Integer>();
	                    part.add(num[i]);
	                    part.add(num[k]);
	                    part.add(num[tmp]);
	                    res.add(part);
	                }
	            }
	        
	        }
	        return res;
	    }
	   
	   /**
	    * solution doesn't use space
	    * @param num
	    * @return
	    */
	   public ArrayList<ArrayList<Integer>> threeSum1(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(num == null || num.length == 0) return res;
	        
	        Arrays.sort(num);
	        
	        int n = num.length;
	        
	        for(int i=0; i< n-2; i++){
	            int diff = 0 - num[i];
	            if(i > 0 && num[i] == num[i-1]) continue;
	            
	            //two pointers points to beginning and end.
	            int j = i+1; //keep two pointers
	            int k = n-1;
	            while(j<k){//move toward middle.
	                int sum = num[j] + num[k];
	                if(sum > diff) k--; //conditionally move the pointers
	                else if(sum < diff) j++;
	                else{
	                    ArrayList<Integer> part = new ArrayList<Integer>();
	                    part.add(num[i]);
	                    part.add(num[j]);
	                    part.add(num[k]);
	                    res.add(part);
	                    do{
	                        k--;
	                    }while(k > j && num[k] == num[k+1]); //step to avoid duplicates
	                    do{
	                        j++;
	                    }while(j < k && num[j] == num[j-1]);//must use do while to guaranttee
	                    //one of k j is moved.
	                }
	            }
	        
	        }
	        return res;
	    }
}
