import java.util.Arrays;


public class ThreeSumClosest {

	public int threeSumClosest(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        int n = num.length;
        
        int curSum = 0;
        int resSum = 0;
        int minDist = Integer.MAX_VALUE;
        for(int i=0; i< n-2; i++){
            for(int j=i+1; j< n-1; j++){
                for(int k = j+1; k <n ; k++){
                    curSum = num[i] + num[j] + num[k];
                    if(Math.abs(curSum - target) < minDist){
                        minDist = Math.abs(curSum - target);
                        resSum = curSum;
                    }
                }
            }
        }
        
        return resSum;
        
    }
	
	/*
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 * 
	 * fixe one element and according to ascending order search for the best elements combination.
	 * o(n2) solution.
	 */
	public int threeSumClosest1(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        int n = num.length;
        
        Arrays.sort(num);
        
        int minDist = Integer.MAX_VALUE;
        int minSum = 0;
        for(int i=0; i< n; i++){
            int lp = i+1;
            int rp = n-1;
            
            while(lp < rp){
                int sum = num[lp] + num[rp] + num[i];
                
                if(sum == target ) return target;
                if(Math.abs(sum - target) < minDist){
                    minDist = Math.abs(sum - target);
                    minSum = sum;
                }
                
                if(sum < target) lp ++;
                if(sum > target) rp --;
                
            }
        }
        
        return minSum;
        
    }
}
