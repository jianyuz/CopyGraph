import java.util.Arrays;

/**
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * @author zhouzhou
 *
 */
public class ScrambleString {

	/**
	 * Bruteforce.
	 * can't pass the speed test.
	 * @param s1
	 * @param s2
	 * @return
	 */
	 public boolean isScramble(String s1, String s2) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	    
	        if(s1== null || s2 == null) return false;
	        if(s1.length() != s2.length()) return false;
	        
	        int n = s1.length();
	        if(n == 1) {
	            return s1.equals(s2);
	        }
	        
	        for(int i=1; i<= n/2; i++){
	            String p1 = s1.substring(0, i), p2 = s1.substring(i);
	            String q1 = s2.substring(0, i), q2 = s2.substring(i);
	                     
	            //reflective part.
	            String r1 = s1.substring(0, n-i), r2 = s1.substring(n-i);
	            String t1 = s2.substring(0, n-i), t2 = s2.substring(n-i);
	            
	            if(isScramble(p1, q1) && isScramble(p2, q2)) return true;
	            if(isScramble(p1, t2) && isScramble(p2, t1)) return true;
	            if(isScramble(q1, r2) && isScramble(q2, r1)) return true;
	            if(isScramble(r1, t1) && isScramble(r2, t2)) return true;

	        }  
	        return false;
	        
	    }
	 
	 /**
	  * A beter one that passed speed test.
	  * before scramble test.
	  * try to sort and compare the base.
	  * scrambled string are formed by the same set of chars.
	  * @param s1
	  * @param s2
	  * @return
	  */
	 public boolean isScramble1(String s1, String s2) {
         // Start typing your Java solution below
	        // DO NOT write main() function
	    
	        if(s1== null || s2 == null) return false;
	        if(s1.length() != s2.length()) return false;
	        
	        int n = s1.length();
	        if(n == 1) {
	            return s1.equals(s2);
	        }
	        
         char[] s1c = s1.toCharArray();
         char[] s2c = s2.toCharArray();
         Arrays.sort(s1c);
         Arrays.sort(s2c);
         if(!String.valueOf(s1c).equals(String.valueOf(s2c)))//check if it is worth to go on.
         //if not equal base, can't be scrambled string.
             return false;
         
	        for(int i=1; i<= n/2; i++){
	            String p1 = s1.substring(0, i), p2 = s1.substring(i);
	            String q1 = s2.substring(0, i), q2 = s2.substring(i);
	                     
	            //reflective part.
	            String r1 = s1.substring(0, n-i), r2 = s1.substring(n-i);
	            String t1 = s2.substring(0, n-i), t2 = s2.substring(n-i);
	            
             
	            if(isScramble(p1, q1) && isScramble(p2, q2)) return true;
	            if(isScramble(p1, t2) && isScramble(p2, t1)) return true;
	            if(isScramble(q1, r2) && isScramble(q2, r1)) return true;
	            if(isScramble(r1, t1) && isScramble(r2, t2)) return true;

	        }  
	        return false;
	        
	    }
	 
	 
	 /**
	  * Another version.
	  * don't do so many cross over.
	  * just two each around.
	  * @param s1
	  * @param s2
	  * @return
	  */
	 public boolean isScramble2(String s1, String s2) {
         // Start typing your Java solution below
	        // DO NOT write main() function
	    
	        if(s1== null || s2 == null) return false;
	        if(s1.length() != s2.length()) return false;
	        
	        int n = s1.length();
	        if(n == 1) {
	            return s1.equals(s2);
	        }
	        
         char[] s1c = s1.toCharArray();
         char[] s2c = s2.toCharArray();
         Arrays.sort(s1c);
         Arrays.sort(s2c);
         if(!String.valueOf(s1c).equals(String.valueOf(s2c)))//check if it is worth to go on.
         //if not equal base, can't be scrambled string.
             return false;
         
	        for(int i=1; i< n; i++){
	            String p1 = s1.substring(0, i), p2 = s1.substring(i);
	            String q1 = s2.substring(0, i), q2 = s2.substring(i);
	                     
	            //reflective part.
	            String t1 = s2.substring(0, n-i), t2 = s2.substring(n-i);
	            
             
	            if(isScramble(p1, q1) && isScramble(p2, q2)) return true;
	            if(isScramble(p1, t2) && isScramble(p2, t1)) return true;

	        }  
	        return false;
	        
	    }
	 
	 /**
	  * dynamic programming
	  * complexity O(n^4);
	  */
	    
	 
	 public static boolean isScramble3(String s1, String s2) {
         // Start typing your Java solution below
	        // DO NOT write main() function
	        if(s1==null || s2== null) return false;
	        if(s1.length() != s2.length()) return false;
	        
	        int n= s1.length();
	        
	        boolean [][][][] dp = new boolean [n][n][n][n];
	        //four dimensional dp isScamble
	        //start pos in s1, end pos in s1, start pos in s2, end pos in s2.
	        //dp(s1, e1, s2, e2) = dp(s1,i,s2,i) && dp(i,e1, i, e2)
	        // or dp(s1, i, n-i, e2) && dp (i, s1, s2, n-i);
	        //initialize for case len is 1;
	        for(int i=0; i< n; i++){
	        	for( int j=0; j< n; j++){
	        		if(s1.charAt(i) == s2.charAt(j))
	        			dp[i][i][j][j] = true;
	        	}
	        }
	        
	        //outer loop len = 2 to n
	        for(int l = 2; l<= n; l ++){
	        	for(int s1Start=0; s1Start< n; s1Start++){
	        		int s1End = s1Start + l -1; //calculate s1.End 
	        		if(s1End > n-1) break; //break if out of loop
	        		for(int s2Start=0; s2Start< n; s2Start++){
	        			int s2End = s2Start + l -1; //calculate s2end
	        			if(s2End > n-1) break; //break if out of range.
	        			boolean res = false;
	        			for(int s1Mid= s1Start; s1Mid< s1End; s1Mid++){
	        				int leftSize= s1Mid - s1Start +1; //calculate left size.
	        				int s2Mid = s2Start + leftSize -1; //use left size to calculate s2mid.
	        				
	        				res = res || (dp[s1Start][s1Mid][s2Start][s2Mid] && 
	        						dp[s1Mid+1][s1End][s2Mid+1][s2End]);
	        				s2Mid = s2End - leftSize ;
	        				//reflective the size for reverse swap.
	        				res = res || (dp[s1Start][s1Mid][s2Mid+1][s2End] && 
	        						dp[s1Mid + 1][s1End][s2Start][s2Mid]);
	        			
	        			}
	        			dp[s1Start][s1End][s2Start][s2End] = res;
	        		}
	        	}
	        }
	        
	        
	        return dp[0][n-1][0][n-1];
	    }
	 
	 public static void main(String[] args){
		 System.out.println(isScramble3("ab", "ba"));
		 
	 }
}
