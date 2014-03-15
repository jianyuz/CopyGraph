
public class RegularExpressionMatching {

	public static void main(String[] args){
		System.out.println(isMatch("aa", "a"));
		System.out.println(isMatch("aa", ".*"));
		System.out.println(isMatch("aa", "."));
		System.out.println(isMatch("aa", "*"));
		System.out.println(isMatch("aa", "aa"));
		System.out.println(isMatch("aab", ".*"));
		System.out.println(isMatch("aab", "c*a*b"));
		System.out.println(isMatch("a", "ab*"));
		System.out.println(isMatch("ab", ".*c"));
	}
	
	public static boolean isMatch(String s, String p) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(s == null || p == null) return false;
        
        
        int sLen = s.length();
        int pLen = p.length();
        
        if(sLen == 0 && pLen == 0) return true;
        
        //patch to string input.
        boolean[][] match = new boolean[pLen+1][sLen+1];
        
        match[0][0] = true;
        for(int i = 1; i<= sLen; i++){
            match[0][i] = false;
        }
        
        //skip char* pattern in init.
        for(int i=1; i<= pLen; i++){
            if(p.charAt(i-1) == '*') match[i][0] = match[i-2][0];
            else
            match[i][0] = false;
        }
        
        for(int i = 1; i<= pLen; i++){
            for(int j = 1; j<=sLen; j++){
                char pChar = p.charAt(i-1);
                char sChar = s.charAt(j-1);
                if(pChar != '*')
                match[i][j] = match[i-1][j-1] && (pChar == sChar || (pChar == '.'));
                else //skip .* or char * or s char matches with the char * 's char or .
                match[i][j] =  match[i-2][j] || 
                (match[i][j-1] && (p.charAt(i-2) == sChar || p.charAt(i-2) == '.')) ; 
            }
        }
        
        return match[pLen][sLen];
    }
	
	
	public static boolean isMatch2(String s, String p) {
	     
        if(s.length() == 0 && p.length() == 0) return true;
        if(s.length() > 0 && p.length() ==0 ) return false;
        if(p.length() > 0 && s.length() == 0){
            if(p.length() > 1 && p.charAt(1) == '*'){
                return isMatch(s, p.substring(2));
            }else
                return false;
        }
        
        if(p.length() ==1){//can not be *
            if(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') 
                return isMatch(s.substring(1), p.substring(1));
            else 
                return false;
        }else{
            if(p.charAt(1) == '*'){
                if(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'){
                    return isMatch(s.substring(1), p) ||  //consume one char in s.
                        isMatch(s, p.substring(2)); //bypass char.
                }else
                    return isMatch(s, p.substring(2)); //bypass char*
            }else{
                if(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'){
                    return isMatch(s.substring(1), p.substring(1));
                }else
                    return false;
            }
        }
        
    }
    
	
	
	 public static boolean isMatch1(String s, String p){
	        return doMatch(s.toCharArray(), p.toCharArray(), s.length(), p.length(), 0, 0);
	    }
	 //convert to array and do comparsition in array.
	 //don't substring out.
	     public static boolean doMatch(char[] s, char[] p, int sLen, int pLen, int ps, int pp){
	        if(ps == sLen && pp == pLen) return true;
	        if(pp >= pLen) return false; // ps > sLen doesn't mean it is definitely false,
	        if(ps >= sLen) {
	            //exhausted string s. but there is still pattern left.
	            if(pp + 1 < pLen && p[pp+1] == '*'){
	                return doMatch(s, p, sLen, pLen, ps, pp+2);
	            }else{
	                return false;
	            }
	        }
	        //pp could have char *
	        
	        //consider two situations.
	        // next p is * or next p is not.
	        //if not, we don't need to do skip match.
	        
	        //next p is not *
	        //add ps < sLen check
	        if(ps < sLen && (pp+1 >= pLen || (pp + 1 < pLen && p[pp + 1] != '*'))){
	            
	            //compare current char
	            return (s[ps] == p[pp] || (p[pp] == '.' )) &&
	                doMatch(s, p, sLen, pLen, ps +1, pp + 1);
	        }
	        
	        //next p is *
	        while(ps < sLen && (s[ps] == p[pp] ||  p[pp] == '.')){
	            //skip match skip the char *
	            if(doMatch(s, p, sLen, pLen, ps, pp + 2) )return true;
	            ps ++; //consume s chars.
	        }
	        
	        return doMatch(s, p, sLen, pLen, ps, pp + 2);
	    }
}
