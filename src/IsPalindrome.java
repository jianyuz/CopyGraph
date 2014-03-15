
/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
 * @author zhouzhou
 *
 */
public class IsPalindrome {

	    public boolean isPalindrome(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(s == null) return false;
	        if(s.length() == 0) return true;
	        
	        char[] sChars = s.toCharArray();
	        int i = 0;
	        int j = sChars.length-1;
	        char a, b;
	        while(i <= j ){
	            
	            while(!Character.isLetterOrDigit(sChars[i])){
	                i++;
	                if(i >= sChars.length) break;
	            }
	            
	            while(!Character.isLetterOrDigit(sChars[j])){
	                j--;
	                if(j < 0) break;
	            }
	            
	            if(i> j) break;
	            else{
	                a = Character.toLowerCase(sChars[i]);
	                b = Character.toLowerCase(sChars[j]);
	            
	                if(a != b) {
	                    return false;
	                }
	            }
	            
	            i++;
	            j--;
	        }
	        return true;
	    }

	    /*second try*/
	    
	    public boolean isPalindrome1(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(s == null) return false;
	        if(s.length() == 0) return true;
	        
	        int len = s.length();
	        int lp = 0, rp = len-1;
	        
	        while(lp <= rp){
	            
	            char lc = s.charAt(lp);
	            while(!Character.isLetterOrDigit(lc)){
	                lp ++;
	                if(lp >= len) break;
	                lc = s.charAt(lp);
	            }
	            char rc = s.charAt(rp);
	            while(!Character.isLetterOrDigit(rc)){
	                rp --;
	                if(rp < 0) break;
	                rc = s.charAt(rp);
	            }
	            
	            if(lp <= rp){
	                if( Character.toLowerCase(lc) != Character.toLowerCase(rc))
	                    return false;
	                else
	                    {
	                        lp++;
	                        rp--;
	                    }
	            }
	            
	        }
	        
	        return true;
	    }
}


