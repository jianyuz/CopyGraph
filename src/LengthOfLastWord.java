
public class LengthOfLastWord {

	public int lengthOfLastWord(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(s == null || s.length() ==0) return 0;
        
        int start = 0;
        int end = s.length() -1;
        
        while(start < s.length() && ' ' == s.charAt(start) ) start ++; //boundary check
        while(end >=0 && ' ' == s.charAt(end) ) end --;
        
        if(end < start) return 0;
        
        if(end == start) return 1;
        
        int count = 0;
        
        while( end >=0 && ' ' != s.charAt(end)) { //boundary check.
            end --;
            count ++;
        }
        
        return count;
    }
	
	/**
	 * simpler solution.
	 * don't count the space at the beginning.
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord1(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(s == null || s.length() ==0) return 0;
        
        int end = s.length() -1;
        
        while(end >=0 && ' ' == s.charAt(end) ) end --;
        
        if(end <0) return 0;
        
        int count = 0;
        
        while( end >=0 && ' ' != s.charAt(end)) {
            end --;
            count ++;
        }
        
        return count;
    }
}
