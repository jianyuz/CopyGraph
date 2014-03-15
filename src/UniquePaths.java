
public class UniquePaths {

	/*
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
save the space 
just use one dimension array for space
	 */
	public int uniquePaths(int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(m <=0 || n<=0) return 0;
        
        int[] dp = new int[n+1];
        
        for(int i=0; i<=n; i++){
            dp[i] = 0; 
        }
        
        dp[1] = 1;
        
        for(int i=0; i< m; i++){
            for(int j=0; j < n; j++){
                dp[j+1] = dp[j] + dp[j+1];
            }
        }
        
        return dp[n];
    }
	
	public static int uniquePaths1(int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(m <=0 || n<=0) return 0;
        
        //unique paths
        //how to pick m -1moves horizontally
        //and how to pick n-1 moves vertically.
        
        int sum = m + n -2;
        
        //Csum m
        int k = m-1;
        if(n-1 < k){
            k = n-1;
        }
        
        long res = 1;//must be long here then caste back
        for(int i = 1; i<= k; i++){
            res = (res * (sum-i +1))/i; //note here is not k use i. don't multiply all the way.
            //but divide too to reduce number size.
        }
        
        return (int)res;
        
    }
	
	/**
	 * Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Start typing your Java solution below
        // DO NOT write main() function
     
        if(obstacleGrid == null) return 0;
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if(m == 0 && n == 0) return 0;
        
        int[] dp = new int[n+1]; //one dimension dp
        
        if(obstacleGrid[0][0] == 0){
            dp[1] = 1; //one path if it is not obstacle there.
        } //dp[0] is the dummy head to the left we keep.
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(obstacleGrid[i][j] == 0){
                    dp[j+1] = dp[j] + dp[j+1];
                }else{
                    dp[j+1] = 0;
                }
            }
        }
        
        return dp[n];
    }
	
	 public static void main(String[] args){
		 System.out.println(uniquePaths1(5, 3));
	 }
}
