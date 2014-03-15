
public class MinPathSum {

	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
        // Start typing your Java solution below
   // DO NOT write main() function

   if(grid == null) {
       throw new IllegalArgumentException("Invalid argument");
   }
   
   if(grid.length == 0 && grid[0].length == 0) {
       throw new IllegalArgumentException("Invalid argument");
   }
   
   int m = grid.length;
   int n = grid[0].length;
   
   int[][] dp = new int[m][n];
   dp[0][0] = grid[0][0]; //set origin in dp array.
   
   for(int i=1; i< m; i++){
       dp[i][0] = dp[i-1][0] + grid[i][0] ; //first column;
   }
   
   for(int j=1; j< n; j++){
       dp[0][j] = dp[0][j-1] + grid[0][j]; //first row;
   }
   
   for(int i=1; i< m; i++){
       for(int j=1; j< n; j++){
           int upper = dp[i-1][j];
           int left = dp[i][j-1];
           dp[i][j] = Math.min(upper, left) + grid[i][j]; 
       }
   }
   return dp[m-1][n-1];
}
	
	/**
	 * one dimensional dp array only
	 * @param grid
	 * @return
	 */
	
	public int minPathSum1(int[][] grid) {
		// Start typing your Java solution below
		// DO NOT write main() function

		if (grid == null) {
			throw new IllegalArgumentException("Invalid argument");
		}

		if (grid.length == 0 && grid[0].length == 0) {
			throw new IllegalArgumentException("Invalid argument");
		}

		int m = grid.length;
		int n = grid[0].length;

		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[1] = 0;//sentinel to make sure we get min-dp.
		//since dp can be computed one dimensional
		//save some space.
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int upper = dp[j + 1];
				int left = dp[j];
				dp[j + 1] = Math.min(upper, left) + grid[i][j];
			}
		}
		return dp[n];
	}
}
