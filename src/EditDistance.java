
public class EditDistance {

	public static void main(String[] args){
		
		System.out.println(minDistance("b", ""));
	}
	/**
	 * edit distance between two words
	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int minDistance(String word1, String word2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //f(i,j) edit distance from substring S1[0 i] to S2[0,j]
        
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        //base case, 0 length to transit to m length. 
        for(int i=0; i<=m; i++){
            dp[i][0] = i;
        }
        
        for(int i=0; i<=n; i++){
            dp[0][i] = i;
        }
        
        char c1, c2;
        int min;
        for(int i=1; i<= m; i++){//when dp include 0. get char i-1. note this.
            c1 = word1.charAt(i-1);
            for(int j=1; j<=n; j++){
                c2=word2.charAt(j-1);
                if(c1==c2){
                    dp[i][j] = dp[i-1][j-1];
                }else{//replace, delete one, and add one from previous to reach transition.
                    min = Math.min(dp[i-1][j-1] + 1, dp[i-1][j] +1);
                    min = Math.min(min, dp[i][j-1] + 1);
                    dp[i][j] = min;
                }
            }
        }
        
        return dp[m][n];
    }
}
