import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * @author zhouzhou
 *
 */
public class LongestValidParathese {

	public static void main(String[] args){
		//System.out.println(longestValidParentheses("()(())"));
		System.out.println(longestValidParentheses(")(((((()())()()))()(()))("));
	}
	
	/**
	 * use a stack to keep track of open close paranthese.
	 * open mark as 1.
	 * close marke as -1.
	 * as we go along to match the parenthese,
	 * calculate the length and push it into stack.
	 * 
	 * keypoint is that we need coerce the values on the way.
	 * 
	 * a better approach is to push the index of left parenthese in.
	 * then use difference of i and position of left parenthese to calculate the length.
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //valid parenthese. must match open matches the end one.
        
        
        Stack<Integer>  stack = new Stack<Integer>();
        
        for(int i=0; i< s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(1); //use it as the marker.
            }else if(c == ')'){
                if(!stack.isEmpty()){
                    if(stack.peek() == 1){
                        stack.pop();//pop 1 out.
                        //coerce the values.
                        if(stack.isEmpty() || stack.peek() <= 1){
                            stack.push(2);
                        }else{
                            int val =2;
                            val += stack.pop();
                            stack.push(val);
                        }
                    }else if(stack.peek() > 1){
                        int val = stack.pop();
                        if(stack.isEmpty() || stack.peek() < 1){//can't flattern
                            stack.push(val);//push original back.
                            stack.push(-1);//no match.
                        }else{
                            if(!stack.isEmpty() && stack.peek() == 1){
                                stack.pop();
                                int val1 = val +2;
                                //coerce value with previous
                                if(!stack.isEmpty() && stack.peek() > 1){
                                	val = stack.pop();
                                	val1 += val;
                                	stack.push(val1);
                                }else{
                                	stack.push(val1);
                                }
                            }
                        }
                    }else{
                        stack.push(-1);
                    }
                }else{
                    stack.push(-1); // use -1 as marker for closing parenthese.
                }
            }
        }
        
        int maxLen = 0, curLen= 0;
        while(!stack.isEmpty()){
            curLen = stack.pop();
            if(curLen > 1 && curLen > maxLen){
                maxLen = curLen;
            }
        }
       
        return maxLen;
    }
	
	/**
	 * keep track of last unmatch )
	 * and position of lefts in stack.
	 * 
	 * @param s
	 * @return
	 */
	 public int longestValidParentheses1(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(s == null || s.length() == 0) return 0;
	        int maxLen = 0, last = -1;
	        //last is the position of last unmatched ).
	        Stack<Integer> lefts = new Stack<Integer>();
	        
	        for(int i=0; i< s.length(); i++){
	            char c = s.charAt(i);
	            if(c == '('){
	                lefts.push(i);
	            }else{
	                if(!lefts.isEmpty()){
	                    lefts.pop();
	                    if(!lefts.isEmpty()){
	                        maxLen = Math.max(maxLen, i - lefts.peek() );
	                    }else{
	                        maxLen = Math.max(maxLen, i - last);
	                    }
	                }else{ //unmatched )
	                    last = i;
	                }
	            }
	        }
	        return maxLen;
	    }
	 
	 /**
	  * this solution doesn't use storage.
	  * @param s
	  * @return
	  */
	 public int longestValidParentheses2(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	       //a solution that doesn't use storage.
	       //note parenthese must match.
	       //left and right count must match.
	       //if right count > left paren count. we need to recount.
	       //two scan from left to right and from right to left.
	       //handle situation when ( separates the count matching.
	       
	       if(s == null || s.length() == 0) return 0;
	       
	       int maxLen = 0;
	       int lefts = 0, rights = 0;
	       for(int i=0; i< s.length(); i++){
	           char c = s.charAt(i);
	           if(c == '('){
	               lefts ++;
	           }else{
	               rights ++;
	           }
	           if(lefts == rights){
	               maxLen = Math.max(maxLen, lefts + rights);
	           }
	           if(rights > lefts){
	               rights = 0;
	               lefts = 0;//start over since right paren separates the counts.
	           }
	       }
	       
	       //count from right to left.
	       lefts = 0;
	       rights = 0;
	       for(int i=s.length() -1; i >=0; i--){
	           char c = s.charAt(i);
	           if(c == '('){
	               lefts ++;
	           }else{
	               rights ++;
	           }
	           if(lefts == rights){
	               maxLen = Math.max(maxLen, lefts + rights);
	           }
	           if(lefts > rights){
	               rights = 0;
	               lefts = 0;//start over since right paren separates the counts.
	           }
	       }
	       
	       return maxLen;
	       
	    }
	 
	 /**
	  * this approach again keep track of length.
	  * not index
	  * reverse dynamic programming.
	  * 
	  * @param s
	  * @return
	  */
	 public int longestValidParentheses4(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	       //a solution that doesn't use storage.
	       //note parenthese must match.
	       //left and right count must match.
	       //if right count > left paren count. we need to recount.
	       //two scan from left to right and from right to left.
	       //handle situation when ( separates the count matching.
	       
	       if(s == null || s.length() == 0) return 0;
	        
	       //one dimensional dp.
	       //dp[i] represents the length of valid parentheses starting from position i.
	       int n = s.length();
	       int[] dp = new int[n];
	       dp[n -1] = 0;
	       //reversely to get dp [0].
	       //keep track of maxLen along the way.
	       int maxLen = 0;
	       
	       for(int i = n-2; i>=0; i--){
	            dp[i] = 0;
	            if(s.charAt(i) == '('){
	                //try to find matching ) after the valid parenthese starting from position i.
	                int j = i+1 + dp[i+1];
	                if(j < n && s.charAt(j) == ')'){//check boundary and matching parenthese.
	                    dp[i] = dp[i+1] + 2;
	                    dp[i] += (j+1) < n ? dp[j+1]: 0; //concatenating matching parenthese.
	                    //flattern since starting form j+1 position we ma have valid parenthese.
	                }
	            }
	            maxLen = Math.max(maxLen, dp[i]);
	       } 
	       
	      
	       return maxLen;
	       
	    }
	 
	 
	 /**
	  * explicit push the the ending parentheses into the stack too.
	  */
	     public int longestValidParentheses6(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	       
	       if(s == null || s.length() == 0) return 0;
	       
	       int maxLen = 0;
	       Stack<Integer> stack = new Stack<Integer>();
	       int n = s.length();
	       
	       // a natural way to keep track of position of ( and )
	       
	       for(int i=0; i< n; i++){
	           if(s.charAt(i) == '('){
	               stack.push(i);
	           }else{
	               if(!stack.isEmpty()){
	                   if(s.charAt(stack.peek()) == '('){
	                    //a match.
	                        stack.pop(); //first pop the matching parenthese off.
	                        //if stack is empty, then i + 1 length.
	                        //else, i- position of top of stack.
	                        //this way, we won't miss the case ()()
	                        maxLen = Math.max(maxLen, stack.isEmpty()? (i + 1): (i - stack.peek()));
	                   }else{
	                        stack.push(i);//push position of ')'. !dont' miss this.
	                   } 
	               }else{
	                   stack.push(i);//push position of ')'.
	               }
	           }
	       }
	       return maxLen;
	     }
	
}
