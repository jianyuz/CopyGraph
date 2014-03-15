
public class LongestPalindromeSubString {

	public static void main(String[] args){
		System.out.println(longestPalindrome("abb"));
	}
	
	public static String longestPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(s == null) return null;
        if(s.length() == 0) return "";
        
        int n = s.length();
        
        
        char[] chars = s.toCharArray();
        
        char[] nchars = new char[2 * n -1];
        
        nchars[0] = chars[0];
        for(int i=1; i< n; i++){
            nchars[2 * i -1] = '#';
            nchars[2 * i] = chars[i]; //pad the chars with #.
        }
        //use an array to keep track of palandrome length for each position.
        int[] p = new int[nchars.length];
        
        p[0] = 0;
        int center = 0; //center of palindrome
        int right = 0; //location of right edge of the palindrome
        
        for(int i=1; i< nchars.length; i++){
            
            int mirror = 2 * center - i; //find mirror position of current palindrome center.
            p[i] = (right >= i)? Math.min(p[mirror], right-i): 0;
            //maximum P[i] is right -i. if out of range.
            
            //let us expand p[i] beyona the right edge.
            while(i + p[i] + 1 < nchars.length && 
                i-p[i] -1 >=0 &&
                nchars[i + p[i] + 1] == nchars[i-p[i] - 1])
                p[i] ++;
            
            if(nchars[i + p[i]] == '#') p[i] --; // # doesn't count.
            
            if(i + p[i] > right){
                center = i;
                right = i + p[i];
            }
            
        }
        
        int maxLen = 0;
        int ci = 0;
        for(int i=0; i< p.length; i++){
            if(p[i] > maxLen){
                maxLen = p[i];
                ci = i;
            }
        }
        //calculate the substring.
        return s.substring( (ci-maxLen)/2, (ci-maxLen)/2 + maxLen +1 );
        
    }
	public static String longestPalindrome2(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        
        //every char itself is a palindrome substring.
        //the longest palindrome for first char is only 1.
        //for the second char would be 3.
        //the longest palindrome's center would be in the middle of the string.
        //palindrome can have two chars. center around empty "".
        
        
        if(s == null ) return null;
        if(s.length() == 0) return "";
        
        //iterate through char by char.
        
        //expand string the insert empty char.
        char[] chars = s.toCharArray();
        char[] nchars = new char[2 * chars.length  -1];
        
        nchars[0] = chars[0];
        for(int i=1; i< chars.length; i++){
            nchars[i *2-1] = ' ';
            nchars[i *2] = chars[i]; 
        }
        
        int maxLen = 1;
        int mStart = 0;
        int mEnd = 0;
        
        //maxLen for first char is 1.
        
        for(int i=1; i< nchars.length; i++){
             
            //center around current char.
            int start = i;
            int end = i;
            
            //empty char doesn't count. shouldn't be added for incrementation.
            while(start >= 0 && end < nchars.length  && nchars[start] == nchars[end]){
                start --;
                end ++;
            }
            
            if(nchars[start+1] == ' '){
                start ++;
                end --;
            }
            
            //compensate for over increment.
            if(end -start - 1 > maxLen){
                maxLen = end - start - 1;
                mStart = start + 1;
                mEnd = end -1;
            }
        }
        //convert start and end back.
        return s.substring(mStart/2, mEnd/2 + 1);
    }
	
	/**
	 * dynamica programming n2 time complexity
	 * and n2 space.
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome1(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(s == null) return null;
        if(s.length() == 0) return "";
        
        int n = s.length();
        
        //dynamica programming.
        //mark i,j substring is a palindrome or not.
        boolean[][] isPal = new boolean[n][n];
        char[] chars = s.toCharArray();
        
        for(int i=0; i< n; i++){
            for(int j=0; j< n; j++){
                if(i == j)
                    isPal[i][j] = true; //the char itself is a palindrome.
                if(j - i == 1 && chars[i] == chars[j]){
                    isPal[i][j] = true; //adjacent chars are equals.
                }
            }
        }
        
        //note we start from n-1 here
        //since the recursive relation is built up from larger i.
        for(int i = n-1; i>=0 ; i--){
            for(int j= i; j < n ; j++){
                if(i +1 < n && j-1 >=0 && isPal[i+1][j-1] && chars[i] == chars[j]){
                    isPal[i][j] = true;
                }
            }
        }
        
        int maxLen = 0;
        int mStart = -1;
        int mEnd = -1;
        for(int i=0; i< n; i++){
            for(int j=i; j< n; j++){
                if(isPal[i][j] && j - i +1 > maxLen){
                    maxLen = j - i + 1;
                    mStart = i;
                    mEnd = j;
                }
            }
        }
        
        return s.substring(mStart, mEnd +1);
    }
}
