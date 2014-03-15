import java.util.HashMap;
import java.util.Map;


public class NumDecoding {

	public static void main(String[] args){
		System.out.println(numDecodings("1"));
	}
	
	/**
	 * this solution doesn't pass the speed limit test.
	 * 
	 * @param s
	 * @return
	 */
	 public static int numDecodings(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if( s== null || s.length() == 0) return 0;
	    
	        
	        HashMap<String, String> codeMap = new HashMap<String, String>();
	        
	        for(int i=0 ; i< 26; i++){
	            codeMap.put("" + (i+1),  "" + (char)('A' + i));    //string to string mapping
	            //start from 1.
	        }
	        return helper(s, 0, codeMap);
	    }
	    
	    private static int helper(String s, int start, HashMap<String, String> codeMap){
	        
	        if(start >= s.length()){
	            return 1;
	        }
	        
	        int sum = 0;
	        String code = codeMap.get("" + s.charAt(start)); //convert char to string key.
	        if(code != null){ //try to take one char and map to code.
	            sum += helper(s,start+1, codeMap);
	        }
	        
	        if(start < s.length() -1){//try to take two chars and map to code.
	            code = codeMap.get(s.substring(start,start+2));
	            if(code != null){
	                sum += helper(s, start + 2, codeMap);
	            }
	            //only accumulate if the decode is valid.
	        }
	        return sum;
	    }
	    
	    
	    /**
	     * Dynamic programming
	     * time Complexity O(n);
	     * @param s
	     * @return
	     */
	    public int numDecodings1(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(s == null || s.length() == 0) return 0;
	        
	        Map<String, String> codeMap = new HashMap<String, String>();
	        for(int i=0; i< 26; i++){
	            codeMap.put("" + (i+1), "" + (char)('A' + i));
	        }//we can have other way to test valid code.
	        //like compare char to 1 and 2 and if second char < 6
	        
	        /*
	         * dp(0, n) = if( 1 char decode is valid) ?dp(0, n-1) + if( 2 decode valid) ? dp(0, n-2);
	         */
	         
	         int n = s.length();
	         int[] dp = new int[n+1];
	         //dp[i] indicate the number of ways of decoding for message length from 1 to i;
	         
	         dp[0] = 1; // base case 1, gate way
	         dp[1] = 0; //depends on if the first char is valid code.
	         if(codeMap.get("" + s.charAt(0)) != null){
	            dp[1] = 1;
	         }
	         
	         
	         String code;
	         for(int i=2; i<= n; i++){
	             dp[i] = 0;
	             code = codeMap.get("" + s.charAt(i-1));
	             if(code != null){
	                 dp[i] += dp[i-1];//add dp[i-1] only when the 1 char code is valid.
	             }
	             if(i <= n){ 
	                code = codeMap.get(s.substring(i-2, i));
	                //for i number of char, get substring of length 2.
	                if(code != null){
	                    dp[i] += dp[i-2]; //add dp[i-2] only when 2 char codes is valid.
	                }
	             }
	         }
	         
	         return dp[n];
	    }
	    
	    /**
	     * don't use hashmap
	     * char comparison to check code validity.
	     * @param s
	     * @return
	     */
	    public int numDecodings2(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(s == null || s.length() == 0) return 0;
	        
	        
	        /*
	         * dp(0, n) = if( 1 char decode is valid) ?dp(0, n-1) + if( 2 decode valid) ? dp(0, n-2);
	         */
	         
	         int n = s.length();
	         int[] dp = new int[n+1];
	         //dp[i] indicate the number of ways of decoding for message length from 1 to i;
	         
	         dp[0] = 1;
	         dp[1] = 0;
	         if(s.charAt(0) != '0'){
	            dp[1] = 1;
	         }
	         
	         
	         for(int i=2; i<= n; i++){
	             dp[i] = 0;
	             if(s.charAt(i-1) != '0'){
	                 dp[i] += dp[i-1];
	             }
                if(s.charAt(i-2) == '1' || s.charAt(i-2) == '2' && s.charAt(i-1) < '7') {
                    dp[i] += dp[i-2];
                }
	         }
	         
	         return dp[n];
	    }
}
