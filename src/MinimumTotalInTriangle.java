import java.util.ArrayList;


/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 Complexity:
 1 + 2 + ... + level
 (1+level) * level/2
 O(level*level)
 
 * @author zhouzhou
 *
 */
public class MinimumTotalInTriangle {

	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(triangle == null || triangle.size() == 0 ) return 0;
        
        int level = triangle.size();
        
        int[] sumList = new int[level];
        
        for(int i=0; i< level; i++){
            sumList[i] = Integer.MAX_VALUE;
        }
        
        sumList[0] = triangle.get(0).get(0);
        for(int i=1; i< level; i++){
            for(int j= i ; j >=0; j--){
                int curr = triangle.get(i).get(j);
                sumList[j] = Math.min( (j-1)>=0 ? (sumList[j-1] +curr): Integer.MAX_VALUE,
                    (j<= i-1)? (sumList[j] + curr): Integer.MAX_VALUE);
            }
        } 
        
        int minTotal = Integer.MAX_VALUE;
        for(int i = 0; i< sumList.length; i++){
            if(sumList[i] < minTotal){
                minTotal = sumList[i];
            }
        }
        return minTotal;
    }
}
