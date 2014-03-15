import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * several approach.
 * 1. regular expression.
 * 2. pure rule based solution.
 * 
 * @author zhouzhou
 *
 */
public class IsNumber {

	/**
	 * check is a string is valid number
	 * +_ handling
	 * leading +- is fine.
	 * +- in the middle should follow e.
	 * +- can't be alone
	 * 
	 * . handling
	 * can't have more than 1 .
	 * pre after should have at least one digit.
	 * 
	 * chars and space handling.
	 * chars are not allowed except e.
	 * only one e is allowed.
	 * before and after e should contain valid number
	 * actually after should be an integer only.
	 * 
	 * relevance eindex should be greater than dotIndex.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        //possible chars in a number
        //leading +- chars e only, numbers, dot. 
        if(s == null || s.length() == 0) return false;
        s = s.trim();
        if(s.length() == 0) return false;
        
        char[] chars = s.toCharArray();
        int len = chars.length;
        int dotIndex = -1;
        int eIndex = -1;
        int plusIndex = -1;
        int minusIndex = -1;
        for(int i= 0; i< len; i++){
            //consecutive 0 at start is fine.
            if(chars[i] == '+' || chars[i] == '-'){
                if(i!= 0){//ok with the startoing + or -.
                    if(i-1 != eIndex) return false;
                    //in the middle it is invalid unless it is following e.
                    if(plusIndex > -1 || minusIndex > -1) return false;
                    //no more than one middle + or -1.
                    if(chars[i] == '+')
                        plusIndex = i;
                    if(chars[i] == '-')
                        minusIndex = i;
                }else{
                
                    //can't be alone. must be followed by a digit.
                    if(i+1 >=len)return false;
                }
            }
            
            
            if(chars[i] == '.'){
                if(dotIndex > -1)
                    return false;
                else{
                    dotIndex = i;
                }
                boolean isValid = false;
                if(i-1 >=0 && Character.isDigit(chars[i-1])) isValid = true;
                if(i+1 <len && Character.isDigit(chars[i+1])) isValid = true;
                if(!isValid) return false;
            }//dot only show once. pre or after should be a number.
           
           if(Character.isLetter(chars[i]) || Character.isSpaceChar(chars[i])){
                if(chars[i] != 'e'){
                    return false;
                }else if(eIndex > -1){
                    return false;
                }else{
                    //pre and after should contain valid number
                    if(!isNumber(s.substring(0, i))) return false;
                    if(!isNumber(s.substring(i+1))) return false;
                    eIndex = i;
                }
            }//only e letter is allowed, no space allowed
        }
        
        if(dotIndex > -1 && eIndex > -1 && eIndex < dotIndex) return false;
        //. precedes e
        return true;
    }
	
	/**
	 * Alternative solution
	 * divide based on e's position.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber1(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //use an approach that divide the number into two parts
        //according to the position of e.
        //before e should be a number
        //after e should be an integer.
        
        if(s == null || s.length() == 0)  return false;
        
        int len = s.length();
        int left = 0;
        int right = len -1;
        
        //trim empty space.
        while(left < len && s.charAt(left) == ' ') left++;
        while(right >= 0 && s.charAt(right) == ' ') right--;
        
        if(left > right) return false; //all empty space chars.
        
        for(int i = left; i<=right; i++){
            if(s.charAt(i) == 'e'){
                return isNumber(s, left, i-1) && isInteger(s, i+1, right);
            }
        }
        
        return isNumber(s, left, right);
        
    }
    
    /**
     * before and after e should both exists number.
     */ 
    public boolean isNumber(String s, int left, int right){
        if(left > right) return false;
        
        //to check if it is a number.
        //must have digit in string, separated by dot.
        boolean hasDigit = false;
        boolean hasDot = false;
        int i = left;
        //considering the leading + or - sign.
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++;
        }
        
        while(i <= right){
            if(s.charAt(i) >= '0' && s.charAt(i) <='9'){
                hasDigit = true;
            }else if(s.charAt(i) == '.'){
                if(!hasDot) hasDot = true;
                else return false;
            }else{
                return false;
            }
            i++;
        }
        if(!hasDigit) return false;
        else return true;
    }
    
    /**
     * only difference here is that we don't consider dot.
     */
    public boolean isInteger(String s, int left, int right){
        if(left > right) return false;
        boolean hasDigit = false;
        int i = left;
        //considering the leading + or - sign.
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++;
        }
        
        while(i <= right){
            if(s.charAt(i) >= '0' && s.charAt(i) <='9'){
                hasDigit = true;
            }else{//other than integer, false.
                return false;
            }
            i++;
        }
        if(!hasDigit) return false;
        else return true;
    }
    
    /**
     * regular expression matches.
     * @param s
     * @return
     */
    public boolean isNumber2(String s){
    	//start and end spaces
    	//digit with optional . + digits.
    	//or optional digit + . and digit.
    	//optional e +- digit.
    	//e must have one digit following
    	String patternStr = "^\\s*[+-]?(\\d+(\\.\\d*)?|(\\d*\\.)?\\d+)(e[+-]?\\d+)?\\s*$";
    	Pattern pattern = Pattern.compile(patternStr);
    	Matcher matcher = pattern.matcher(s);
    	return matcher.matches();
    }
}
