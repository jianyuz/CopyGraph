
public class atoi {
/**
 * Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.


 * @param args
 */
	public static void main(String[] args){
		System.out.println(atoi("-0"));
		System.out.println(atoi("13434ag"));
		System.out.println(atoi("-2343"));
		System.out.println(atoi("-03435"));
		System.out.println(atoi("-003435"));//number starts with 0 is fine.
		
	}
	
	/**
	 * A new way to check overflow condition.
	 * use max_VaLUE to precheck the operation to see if the operation can possibly generate 
	 * an overflow.
	 * @param str
	 * @return
	 */
	 public static int atoi1(String str) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        
	        //possible input
	        //positive 
	        //negative
	        //and zero.
	        //what are the allowed chars  + -, [1-9].
	        //if invalid, what to do?
	        //white spaces in input it is possible. zero value
	        //if the number is out of range of representable value. max_int or min_int is returned.
	        //ignore anything after the last digit.
	        
	        if(str == null || str.length() == 0) return 0;
	        
	        //remove empty space at beginning.
	        int pos = 0;
	        
	        boolean isNeg = false;
	        int res = 0;
	        
	        while(str.charAt(pos) == ' ') pos ++;
	        
	        if(str.charAt(pos) == '+' || str.charAt(pos) == '-'){
	            if(str.charAt(pos) == '-') isNeg = true;
	            pos ++;
	        }
	        
	        
	        while(pos < str.length() ){
	            int tmp = str.charAt(pos) - '0';
	            if(tmp >=0 && tmp <= 9 ){
	                if(Integer.MAX_VALUE /10 >= res) res *= 10;
	                else{
	                    return isNeg?Integer.MIN_VALUE: Integer.MAX_VALUE;
	                }
	                
	                if(Integer.MAX_VALUE - tmp >= res){
	                    res += tmp;
	                }
	                else{
	                    return isNeg?Integer.MIN_VALUE: Integer.MAX_VALUE;
	                }
	                pos ++;
	            }else break; // invalid or ignore subsequent chars.
	        }
	        
	        res =  isNeg? -res: res;
	        
	        return  res;
	        
	    }
	public static int atoi(String str) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        
        //possible input
        //positive 
        //negative
        //and zero.
        //what are the allowed chars  + -, [1-9].
        //if invalid, what to do?
        //white spaces in input it is possible. zero value
        //if the number is out of range of representable value. max_int or min_int is returned.
        //ignore anything after the last digit.
        
        if(str == null || str.length() == 0) return 0;
        
        //remove empty space at beginning.
        int pos = 0;
        
        boolean isNeg = false;
        long res = 0;
        
        while(str.charAt(pos) == ' ') pos ++;
        
        if(str.charAt(pos) == '+' || str.charAt(pos) == '-'){
            if(str.charAt(pos) == '-') isNeg = true;
            pos ++;
        }
        
        
        while(pos < str.length() ){
            int tmp = str.charAt(pos) - '0';
            if(tmp >=0 && tmp <= 9 ){
                res = res * 10 + tmp;
                pos ++;
            }else break; // invalid or ignore subsequent chars.
        }
        
        res =  isNeg? -res: res;
        
        if(res > Integer.MAX_VALUE) res = Integer.MAX_VALUE;
        if(res < Integer.MIN_VALUE) res = Integer.MIN_VALUE;
        return (int) res;
        
    }
}
