import java.util.HashMap;
import java.util.Map;


public class LongestSubstringwithoutRepeatChar {

	/**
	 * use hashmap to keep track of repeating chars.
	 * if duplicate found
	 * restart search from the next char of duplicate char.
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(s == null || s.length() == 0) return 0;
        
        char[] chars = s.toCharArray();
        
        int maxLen = 0;
        int curLen = 0;
        //map character to theri position. 
        //use it to check duplication
        Map<Character, Integer> posMap = new HashMap<Character, Integer>();
        
        for(int i=0; i< chars.length;){
            char curChar = chars[i];
            if(posMap.get(curChar) != null){
                //found duplicate chars.
                i = posMap.get(curChar) + 1;
                posMap.clear();
                if(curLen > maxLen){
                    maxLen = curLen;
                }
                curLen = 0;
            }else{
                posMap.put(curChar, i);
                curLen ++;
                i++;//remember to increment i here.
            }
        }
        
       
        
        return Math.max(maxLen, curLen);
    }
}
