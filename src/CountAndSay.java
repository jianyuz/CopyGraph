
public class CountAndSay {

	/**
	 * iterative solution.
	 * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
	 * @param n
	 * @return
	 */
	 public String countAndSay(int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(n <=0) return "";
	        
	        if(n == 1) return "1";
	        
	        int count = 1;
	        String res = "1";
	        while(count < n){ //count and say n-1 time.
	            StringBuilder sb = new StringBuilder();
	            int freq = 0;
	            char c = '\0', pc = '\0'; //char must be initialized.
	            for(int i = 0; i < res.length(); i++){
	                c = res.charAt(i);
	                if(i > 0){
	                    pc = res.charAt(i-1);
	                    if(c != pc){
	                        sb.append(freq).append(pc);
	                        freq = 0;//increment it only once.
	                    }
	                }
	                freq ++;
	            }
	            //add the last c.
	            sb.append(freq).append(c);
	            res = sb.toString();
	            count ++;
	        }
	        
	        return res;
	    }
	 
	 /**
	  * very clean recursive solution.
	  * doesn't require special condition handling.
	  * 
	  * @param n
	  * @return
	  */
	 public String countAndSay1(int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(n <=0) return "";
	        
	        if(n == 1) return "1"; //termination condition.
	        
	        String prev = countAndSay(n-1);
	        
	        StringBuilder sb = new StringBuilder();
	        
	        for(int i = 0; i < prev.length();){
	            char c = prev.charAt(i);
	            int freq = 0;
	            while( i < prev.length() && prev.charAt(i) == c){
	                freq ++;
	                i++;
	            }
	            sb.append(freq).append(c);
	        }
	        
	        return sb.toString();
	    }
}
