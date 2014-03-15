import java.util.Arrays;


public class WildCardMatching {

	 public boolean isMatch(String s, String p) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if( s== null || p == null) return false;
	        
	        return doMatch(s, 0, p, 0);
	    }
	    
	   /**
	    * doesn't pass large data set.
	    * recursive solution.
	    * char by char match.
	    * terminate condition.
	    * both are consumed.
	    * if s is consumed.
	    * pattern may have leftoever *.
	    * if pattern is consumed, but string is not.
	    * return false.
	    * 
	    * char match
	    * char match to ?
	    * 
	    * char match to *
	    * three cases.
	    * consume char, 
	    * consume pattern since * can consume empty string.
	    * consume both.
	    * @param s
	    * @param i
	    * @param p
	    * @param j
	    * @return
	    */
	    public boolean doMatch(String s, int i, String p, int j){
	        //both stirng and pattern are consumed.
	        if(i == s.length() && j == p.length()){
	            return true;
	        }else if(i == s.length()){
	             //exhausted the string length.
	             //j is * and it is the last char in pattern.
	             if(p.charAt(j) == '*') return doMatch(s, i, p, j+1);
	             else return false;
	        }else if(j == p.length()){
	            return false;
	        }
	       
	        
	        if(s.charAt(i) == p.charAt(j)) return doMatch(s, i+1, p, j+1);
	        else{
	            if(p.charAt(j) == '?') return doMatch(s, i+1, p, j+1);
	            if(p.charAt(j) == '*'){
	                //match any sequence or empty sequence.
	                //consume string char or pattern char or both.
	                return doMatch(s, i+1, p, j) || doMatch(s, i+1, p, j+1) || doMatch(s, i, p, j+1);
	            }
	        }
	        
	        return false;
	        
	    } 
	    
	    public static void main(String[] args){
	    	System.out.println(isMatch1("a", "*"));
	    }
	    
	    /**
	     * use mod to determine which is next array to fill.
	     * @param s
	     * @param p
	     * @return
	     */
	    public static boolean isMatch1(String s, String p) 
	    {
	        if(s == null || p == null) return false;
	        
	        int m=s.length();
	        int n=p.length();
	        
	        //store only two columns for pattern.
	        boolean[][] dp=new boolean[m+1][2];
	      
	        
	        for(int j=0; j<=n; j++){
	            //count pattern from empty to full pattern matching.
	            
	            //initialize pattern matching for all the string chars.
	            for(int i=0; i<=m; i++){
	                dp[i][j%2] = false;
	            }
	            if(j==0) {
	                dp[0][0] = true;
	                continue;
	            }
	            //empty string matches empty pattern.
	            
	            if(p.charAt(j-1) == '*'){ //note use j-1 to get char.
	                for(int i=0; i<=m; i++){
	                    if(dp[i][(j+1)%2]){//previous without star matches.
	                        for(int k=i; k<=m; k++){ // all the chars above can match with star.
	                            dp[k][j%2] = true; //note here we use k.
	                        }
	                    }   
	                }
	            }else{
	                //don't consider i=0;
	                //since 0 char match non star pattern. must be false.
	                for( int i=1; i<=m; i++){
	                    dp[i][j%2] = (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') && dp[i-1][(j+1)%2];
	                }// when match, carry over from last result.
	            }
	        }
	        
	        for(int i=0; i<=m; i++)
	        System.out.println(Arrays.toString(dp[i]));
	        return dp[m][n%2];
	        
	        
	    }
	    
	    
	    /**
	     * Analysis:

For each element in s
If *s==*p or *p == ? which means this is a match, then goes to next element s++ p++.
If p=='*', this is also a match, but one or many chars may be available, so let us save this *'s position and the matched s position.
If not match, then we check if there is a * previously showed up,
       if there is no *,  return false;
       if there is an *,  we set current p to the next element of *, and set current s to the next saved s position.

	     * @param s
	     * @param p
	     * @return
	     */
	    public static boolean isMatch4(String s, String p) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if( s== null || p == null) return false;
	        
	        
	        //greedy method.
	        //if assume * match no char.
	        //if not match, then backtrack
	        //if all left pattern char are *. return true.
	        
	        int m = s.length();
	        int n = p.length();
	        
	        int i = 0, j=0;
	        int star = -1; //last star position
	        int sp = -1; //last string position when encounter star.
	        
	        while(i < m){
	            if(j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
	                i ++;
	                j ++;
	                continue;
	            }
	            
	            if(j < n && p.charAt(j) == '*'){
	                star = j;
	                sp = i;
	                j++; //consume no char.
	                continue;
	            }
	            
	            if(j >= n || s.charAt(i) != p.charAt(j)){
	                if(star >= 0){ //back to star pos. //note >=
	                    j = star + 1; 
	                    i = ++sp ; //consume one char.
	                    //increment sp position. if next time doesn't match.
	                    //consume one more.
	                }else{
	                    return false;
	                }
	                continue;
	            }
	        }
	        
	        //handle left over stars
	        while(j < n){
	            if(p.charAt(j) == '*') j++;
	            else break;
	        }
	        return j== n;
	    }
	    
	    /**
	     * early terminate * pattern matching.
	     * reduce memory size to use two columsn still fails large data set test.
	     * add count to track how many none-star char in the pattern.
	     * compare with string leng.
	     * if bigger, then no match.
	     * @param s
	     * @param p
	     * @return
	     */
	    public static boolean isMatch3(String s, String p) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if( s== null || p == null) return false;
	        
	        int patternLenNoStar = 0;
	        for (char c : p.toCharArray())
	            if (c != '*') patternLenNoStar++;
	        if (patternLenNoStar > s.length()) return false;
	        
	        //dynamic programming approach.
	        //m * n m is the length of the string, 
	        //n is the length of the pattern.
	        
	        //optimize * multiple stars is equivalient to be one.
	        StringBuilder sb = new StringBuilder();
	        int count = 0;
	        while(count < p.length()){
	            if(count > 0 && p.charAt(count) == '*' && p.charAt(count-1) == '*') {
	                count++;
	                continue;
	            }
	            sb.append(p.charAt(count));
	            count++;
	        }
	        
	        p = sb.toString();
	        
	        int m = s.length();
	        int n = p.length();
	        
	        boolean[][] dp = new boolean[m+1][2];
	        //sequential update, only need two columns.
	        
	        dp[0][0] = true;//empty string match empty pattern.
	        
	        for(int i=1; i <= m; i++){
	            //pattern empty, string not.
	            dp[i][0] = false;
	        }
	        
	        //mapped cols
	        int pj = 0;
	        int cj = 1;
	        
	        for(int j=1; j<=n; j++){
	            for(int i=0; i<=m; i++){
	                if(i == 0){
	                    if(j-1 == 0 && p.charAt(j-1) == '*') dp[0][cj] = true;
	                    else dp[0][cj] = false;
	                    continue;
	                }
	                if(i -1 >=0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')){
	                    dp[i][cj] = dp[i-1][pj]; //copy the value from previous.
	                    //dependence relationship.
	                }else if(p.charAt(j-1) == '*'){
	                    //check the column above. one start match multiple chars.
	                    //and the column to the left. 
	                    //including the cell to the left. the start matches nothging.
	                    //anyone of them is true.
	                    //mark the cell as true;
	                    //use break to early terminate to avoid timout.
	                    boolean res = false;
	                    for(int k = i; k >=0; k--){
	                    	
	                        if( k != i && (res = (res || dp[k][cj]))) break;
	                        if( res = (res || dp[k][pj])) break;
	                    }
	                    dp[i][cj] = res;
	                }else{
	                    dp[i][cj] = false;
	                }
	                
	            }
	            cj = 1 - cj;
	            pj = 1 - pj;//update previous col and current cols.
	        }
	        
	        return dp[m][pj];
	    }
	    
	    
	    /**
	     * dynamic programming solution.
	     * o(n) m * n + n * n 
	     * n is the length of pattern.
	     * @param s
	     * @param p
	     * @return
	     */
	    public static boolean isMatch2(String s, String p) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if( s== null || p == null) return false;
	        
	        //dynamic programming approach.
	        //m * n m is the length of the string, 
	        //n is the length of the pattern.
	        
	        //optimize * multiple stars is equivalient to be one.
	        StringBuilder sb = new StringBuilder();
	        int count = 0;
	        while(count < p.length()){
	            if(count > 0 && p.charAt(count) == '*' && p.charAt(count-1) == '*'){
	            	count ++;
	            	continue;
	            }
	            sb.append(p.charAt(count));
	            count++;
	        }
	        
	        p = sb.toString();
	        
	        int m = s.length();
	        int n = p.length();
	        
	        boolean[][] dp = new boolean[m+1][n+1];
	        
	        dp[0][0] = true;//empty string match empty pattern.
	        
	        for(int i= 1; i<= n; i++){
	            //string empty. pattern not.
	            if(i-1 == 0 && p.charAt(i-1) == '*') dp[0][i] = true;
	            else dp[0][i] = false;
	        }
	        
	        for(int i=1; i <= m; i++){
	            //pattern empty, string not.
	            dp[i][0] = false;
	        }
	        
	        for(int i=1; i<=m; i++){
	            for(int j=1; j<=n; j++){
	                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
	                    dp[i][j] = dp[i-1][j-1];
	                }else if(p.charAt(j-1) == '*'){
	                    //check the column above. one start match multiple chars.
	                    //and the column to the left. 
	                    //including the cell to the left. the start matches nothging.
	                    //anyone of them is true.
	                    //mark the cell as true;
	                    boolean res = false;
	                    for(int k = i; k >=0; k--){
	                        res = res || dp[k][j];
	                    }
	                    if(j-1 >=0){
	                        for(int k = i; k>=0; k--){
	                            res = res || dp[k][j-1];
	                        }
	                    }
	                    dp[i][j] = res;
	                }else{
	                    dp[i][j] = false;
	                }
	                
	            }
	        }
	        
	        for(boolean[] a : dp){
	        	System.out.println(Arrays.toString(a));
	        }
	        return dp[m][n];
	    }
	    
	    
	    
	    
}
