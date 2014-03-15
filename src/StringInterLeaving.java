
public class StringInterLeaving {

	/**
	 * slowest recursive method.
	 * compare char one by one.
	 * s3 may match with char from s1 or s2.
	 * terminal condition
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave(String s1, String s2, String s3) {
        // Start typing your Java solution below
        // DO NOT write main() function
     
        if(s1 == null || s2 == null || s3 == null) return false;
        
        return helper(s1, 0, s2, 0, s3, 0);
        
    }
    
    
    private boolean helper(String s1, int index1, String s2, int index2, String s3, int index3){
        
        int sum = index1 + index2 + index3;
        
        if(sum == (s1.length() + s2.length() + s3.length())){
            return true;
        }
        
        boolean res = false;
        
        if(index3 < s3.length() && index1 < s1.length() && s3.charAt(index3) == s1.charAt(index1)){
             res = res || helper(s1, index1 +1 , s2, index2, s3, index3 +1);
        }
        
        if(index3 < s3.length() && index2 < s2.length() && s3.charAt(index3) == s2.charAt(index2)){
             res = res || helper(s1, index1, s2, index2 + 1, s3 , index3 +1);
        }
        
        return res;
    }
    
    public boolean isInterleave1(String s1, String s2, String s3) {
        // Start typing your Java solution below
        // DO NOT write main() function
     
        if(s1 == null || s2 == null || s3 == null) return false;
   
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        
        //pre check if length makes sense.
        if((n1 + n2) != n3) return false;
        
        //dp array size is one count more.
        //since we need to consider empty string case.
        boolean[][] dp = new boolean[n1+1][n2+1];
        
        //no chars from s1 or s2.
        dp[0][0] = true;
        
        //all the chars from one string.
        for(int i=1; i <= n1; i++){
            if(s1.substring(0,i).equals(s3.substring(0,i)))
                dp[i][0] = true; 
            else
                dp[i][0] = false;
        }
        
        //all the chars from s2.
        for(int i=1; i<= n2; i++){
            if(s2.substring(0,i).equals(s3.substring(0,i)))
                dp[0][i] = true;
            else
                dp[0][i] = false;
        }
        
        //build the dp from 1 
        //dp [i][j] is that 0 i and 0 j char to interleave from s1 and s2.
        //and i+j chars in s3 
        for(int i=1; i<=n1; i++){
            for(int j=1; j<= n2; j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j -1)) ||
                   (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                
            }
        }
       
       return dp[n1][n2];
    }
    
    
}
