import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * classic stack match.
 * @author zhouzhou
 *
 */
public class ValidParenthese {

	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
	 * @param s
	 * @return
	 */
	 public boolean isValid(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if( s == null || s.length() == 0) return false;
	        
	        //use stack to match the brackets.
	        //if not match faile.
	        //if stack is empty, fail.
	        //also afterward if stack is not empty, left unmatched, fail
	        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
	        m.put((int)'(', (int)')');
	        m.put((int)'{', (int)'}');
	        m.put((int)'[', (int)']');
	        
	        Stack<Integer> st = new Stack<Integer>();
	        
	        char[] chars = s.toCharArray();
	        
	        for(int i=0; i< chars.length; i++){
	            char c = chars[i];
	            switch(c){
	                case '(':
	                case '{':
	                case '[':
	                    st.push((int)c);
	                    break;
	                case ')':
	                case '}':
	                case ']':
	                    if(st.isEmpty()) return false;
	                    int p = st.pop();
	                    if(c != m.get(p)){
	                        return false;
	                    }
	                    break;
	                default:
	                    return false;
	            }
	        }
	        if(!st.isEmpty()) return false;
	        else
	        return true;
	    }
}
