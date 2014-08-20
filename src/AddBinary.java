
public class AddBinary {

	/**
	 * adding two binary string to get result.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	 public String addBinary(String a, String b) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
		   //precondition checking.
	        if(a== null && b == null) return null;
	        if(a== null || a.length() == 0) return b;
	        if(b== null || b.length() == 0) return a;
	        
	        
	        //swap so length of b is longer than a.
	        if(a.length() > b.length()){//swap ab.
	            //swtich the two
	            String temp = a;
	            a = b;
	            b = temp;
	        }
	        
	        int aLen = a.length();
	        int bLen = b.length();
	        
	        int resLen = bLen;//longer size.
	        
	        //char array to store result.
	        char[] res = new char[resLen];
	        
	        int i = aLen -1;
	        int j = bLen -1;
	        int r = res.length -1;
	        
	        //declare carry bit
	        char carry = '0';
	        
	        //add from back to front.
	        while(i >=0 && j >=0){
	            char ac = a.charAt(i);
	            char bc = b.charAt(j);
	            
	            //add up bits. update carry field and result fields.
	            if(ac == '1' && bc == '1'){
	                if(carry == '1')
	                    res[r] = '1';
	                else
	                    res[r] = '0';
	                carry = '1';
	            }else if(ac != bc){
	                if(carry == '1'){
	                    res[r] = '0';
	                    carry = '1';
	                }else{
	                    res[r] = '1';
	                    carry = '0';
	                }//close else with curly brace.
	            }else{//both are "zeror"
	                if(carry == '1'){
	                    res[r]= '1';   
	                }else{
	                    res[r] = '0';
	                }
	                carry = '0';
	            }
	            i--;
	            j--;
	            r--;
	        }
	         
	        //b is longer so we must have run out of bit on a here.
	        while(j >=0){
	            char bc = b.charAt(j);
	            if(carry == '1' && bc == '1'){
	                res[r] = '0';
	                carry = '1';
	            }else if(carry != bc){
	                res[r] = '1';
	                carry = '0';
	            }else if(carry =='0' && bc == '0'){
	                res[r] = '0';
	            }
	            j--;
	            r--;//don't forget deduct 1.
	        }
	    
	        if(carry == '1'){//need to increment result array's space.
	            //add one more to the beginning.
	            char[] newRes = new char[res.length + 1];//array length is different.
	            System.arraycopy(res, 0, newRes, 1, res.length); //copy to 1- length pos.
	            newRes[0] = '1';
	            return new String(newRes);
	        }else{
	            return new String(res);
	        }
	        
	    }
	 
	 /**
	  * smaller solution.
	  * convert to integer for adding
	  * and avoid separate loop for dangling i or j
	  * and additional carry.
	  * 
	  * @param a
	  * @param b
	  * @return
	  */
	 public String addBinary1(String a, String b) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
		 //again precondition checking.
	        if(a == null && b == null) return null;
	        if(a == null || a.length() == 0) return b;
	        if(b == null || b.length() == 0) return a;
	        
	        int aLen = a.length();
	        int bLen = b.length();
	        
	        int carry = 0;
	        int aVal = 0;
	        int bVal = 0;
	        
	        int i = aLen-1;
	        int j = bLen-1;
	        
	        StringBuilder res = new StringBuilder();
	        
	        while(i >=0 || j>=0 || carry > 0){
	            aVal = (i >=0)? (a.charAt(i) - '0'): 0; //bit doesn't exist, use 0.
	            bVal = (j >=0)? (b.charAt(j) - '0'): 0;
	            int sum = aVal + bVal + carry;
	            carry = sum / 2; //how many 2 to carry. 
	            sum = sum % 2; // can be sum - carry * 2;
	            res.insert(0, "" + sum); //insert to the font of string buidler
	            i--;
	            j--;
	        }
	        return res.toString();
	        
	    }
}
