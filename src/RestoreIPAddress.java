import java.util.ArrayList;


public class RestoreIPAddress {

	public static void main(String[] args){
		ArrayList<String> res = restoreIpAddresses1("1111");
	}
	
	/**
	 * a more concise solution.
	 * 
	 * @param s
	 * @return
	 */
	public static ArrayList<String> restoreIpAddresses1(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<String> res = new ArrayList<String>();
        
        //invalid ip address anyway.
        if(s== null || s.length() < 4 || s.length() > 12) return res;
        
        helper1(s, 0, 0, "", res);
        
        return res;
        
        
    }
    
    /**
     * termiate condition check the length constraints per segment.
     * and check the success condition. start length no difference and segment is 4.
     * can change partial to StringBuffer.
     * 
     * add chars one by one and
     * accumulate the check of integer range.
     * if integer is already 0.
     * stop the check.
     * @param s
     * @param start
     * @param segment
     * @param partial
     * @param res
     */
    private static void helper1(String s, int start, int segment, String partial, ArrayList<String> res){
        if(s.length() - start > (4 - segment) * 3) return;
        if(s.length() - start < (4 - segment)) return;
        
        if(s.length() - start == 0 && segment == 4){
            res.add(partial.substring(0, partial.length()-1)); //delete last char .
            return;
        }
        
        int sum = 0;
        for(int i = start; i < start + 3  && i< s.length(); i++){ //length of ip segment can be 1, 2, 3.
            sum = sum * 10 +  (s.charAt(i) - '0');
            if(sum < 0 || sum > 255) continue; //out of range.
            partial += s.charAt(i); //append it char by char
            helper1(s, i+1, segment + 1, partial + "." , res); //append tailing .
            if(sum == 0) break; //can't keep add number to 0 string.
        }
    }
    
	
	 public ArrayList<String> restoreIpAddresses(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        ArrayList<String> res = new ArrayList<String>();
	        
	        //invalid ip address anyway.
	        if(s== null || s.length() < 4 || s.length() > 12) return res;
	        
	        helper(s, s.length(), 0, 1, "", res);
	        
	        return res;
	        
	        
	    }
	    
	    
	    private void helper(String s, int length, int start, int segment, String partial, ArrayList<String> res){
	        if(segment > 4) return;
	        
	        String newPartial = null; //need new variable here.
	        for(int i = 1; i < 4; i++){ //length of ip segment.
	            int resLen = length - i - start;
	            //prune the search length constraints.
	            if(resLen > (4 - segment) * 3 || resLen < (4-segment) * 1) continue;
	            //continue to search
	            String tmp = s.substring(start, start + i);
	            int tmpInt = Integer.parseInt(tmp);
	            if(tmpInt < 0 || tmpInt > 255) continue; //out of range.
	            if(Integer.toString(tmpInt).length() != i) continue; //preceding 0 in digit, invalid ip segment.
	            newPartial = partial +  ("".equals(partial)?"":".") + tmp; //conditional adding separator.
	            if(segment == 4){
	                res.add(newPartial);
	                return;
	            }
	            helper(s, length, start + i, segment + 1, newPartial, res);
	        }
	    }
}
