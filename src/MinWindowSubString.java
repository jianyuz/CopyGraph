import java.util.HashMap;
import java.util.Map;


public class MinWindowSubString {

	public static void main(String[] args){
		
		System.out.println(minWindow("abcabdebac", "cda"));
		
		//cabd
	}
	
	/**
	 * create frequency hashmap for the substring T.
	 * use start and i to keep track of start and end window.
	 * 
	 * compare foundCount with foundLimit.
	 * only add foundCount when freq is greater than or equals to 0
	 * only reduce foundCount when freq is greater than 0.
	 * 
	 * O(n) complexity.
	 * @param S
	 * @param T
	 * @return
	 */
	 public static String minWindow1(String S, String T) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(S == null || T == null) return "";
	        if(S.length() == 0 || T.length()==0) return "";
	        if(T.length() > S.length()) return ""; //special case, T length greate than that of S.
	        
	        int start = -1;
	        int min = Integer.MAX_VALUE; //find min window length.
	        int mStart = -1, mEnd = -1;
	        
	        //initialize freq array.
	        Map<String, Integer> freq = new HashMap<String, Integer>();
	        
	        String cur = null;
	        for(int i=0; i<T.length(); i++){
	            cur = "" + T.charAt(i);
	            if(freq.get(cur ) == null){
	                freq.put(cur, 0);
	            }
	            freq.put(cur, freq.get(cur) + 1); //T may have repeated char.
	        }
	        
	        //how many chars we found so far.
	        int foundCount = 0;
	        int foundLimit = T.length();
	        
	        for(int i=0; i<S.length(); i++){
	            cur = "" + S.charAt(i);
	            if(foundCount < foundLimit){
	                if(freq.get(cur) != null){//the cur is in T.
	                    if(foundCount == 0){
	                        start = i;
	                    	foundCount ++;
	                        freq.put(cur, freq.get(cur) -1);
	                    }
	                    else if(cur.equals("" + S.charAt(start)) && freq.get(cur) == 0){
	                        //progress start to a char in T not equal to start.
	                    	//if cur char is the same as start and we have matched all of the char.
	                        do{
	                            start ++;
	                        }while(start <=i && freq.get("" + S.charAt(start))== null);
	                    }else{
	                        freq.put(cur, freq.get(cur) - 1);
	                        if(freq.get(cur) >=0)
	                        	foundCount++;
	                    }
	                }    
	            }
	            
	            while(foundCount == foundLimit){
	                //update max, mstart and end;
	                if(i-start +1 <min){//compare first update mStart and mEnd later.
	                	min = i-start +1;
	                	mStart = start;
	                	mEnd = i;
	                }
	                cur = "" + S.charAt(start);//move start forward.
	                freq.put(cur, freq.get(cur) +1);//reduce match count.
	                if(freq.get(cur) > 0) //only reduce found count if we haven't overdone it.
	                	foundCount --;
	                do{
	                    start ++;
	                } while(start <=i && freq.get("" + S.charAt(start)) == null);//progree until encounter a char in T. 
	            }//there is a chance that foundCount not changed.
	            //do the loop to find 
	        }
	        
	        if(mStart == -1) return "";
	        else{
	        	
	        	return S.substring(mStart, mEnd +1);
	        }
	    }
	 
	 
	 public static String minWindow(String S, String T) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(S== null || T== null) return "";
	        
	        int sLen = S.length();
	        int tLen = T.length();
	        if(sLen == 0 || tLen == 0) return "";
	        
	        if(sLen < tLen) return "";
	        
	        Map<String, Integer> toFind = new HashMap<String, Integer>();
	        
	        String cur = null;
	        for(int i =0; i< tLen; i++){
	            cur = "" + T.charAt(i);
	            if(toFind.get(cur) == null){
	                toFind.put(cur, 0);
	            }else{
	                toFind.put(cur, toFind.get(cur) + 1);
	            }
	            
	        }
	        
	        //frequency map for the chars in T found so far.
	        Map<String, Integer> found = new HashMap<String, Integer>();
	        
	        int min = Integer.MAX_VALUE;
	        int mStart = -1, mEnd = -1;
	        int count = 0;
	        //two pointer to keep track of window start and end.
	        for(int start = 0, end = 0; end < sLen; end ++){
	            cur = "" + S.charAt(end);
	            if(toFind.get(cur) == null) continue;
	            
	            //start of the match.
	            if(count ==0) {
	                start = end;
	            }
	            
	            //increment statistics in found.
	            if(found.get(cur) == null){
	                found.put(cur, 0);
	            }else{
	                found.put(cur, found.get(cur) + 1);
	            }
	            
	            //increment number of found elements.
	            if(found.get(cur) <= toFind.get(cur)){
	                count ++;
	            }
	            
	            if(count == tLen){ //found all chars in T.
	                //shrink the window to avoid possible case of duplicate char at front.
	                cur = "" + S.charAt(start);
	                while(toFind.get(cur) == null || toFind.get(cur) < found.get(cur)){
	                    if(toFind.get(cur) != null && toFind.get(cur) < found.get(cur)){
	                        found.put(cur, found.get(cur) -1);
	                    }
	                    start ++;
	                    cur = "" + S.charAt(start);
	                }
	                
	                if(end -start +1 < min){
	                    min = end -start +1;
	                    mStart = start;
	                    mEnd = end;
	                }
	            }//once get count == tLen. we incrementally add matched char to the window.
	            //at the end until we found a duplicate char to the head char. then shrink the window 
	            //again.
	        }
	        
	        if(mStart == -1) return "";
	        else{
	            return S.substring(mStart, mEnd+1);
	        }
	    }
}
