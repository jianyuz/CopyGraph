
public class AddBinary {

	 public String addBinary(String a, String b) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(a== null && b == null) return null;
	        if(a== null || a.length() == 0) return b;
	        if(b== null || b.length() == 0) return a;
	        
	        
	        if(a.length() > b.length()){//swap ab.
	            //swtich the two
	            String temp = a;
	            a = b;
	            b = temp;
	        }
	        
	        int aLen = a.length();
	        int bLen = b.length();
	        
	        int resLen = bLen;//longer size.
	        
	        char[] res = new char[resLen];
	        
	        int i = aLen -1;
	        int j = bLen -1;
	        int r = res.length -1;
	        char carry = '0';
	        
	        while(i >=0 && j >=0){
	            char ac = a.charAt(i);
	            char bc = b.charAt(j);
	            
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
	            }else{
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
	    
	        if(carry == '1'){
	            //add one more to the beginning.
	            char[] newRes = new char[res.length + 1];//array length is different.
	            System.arraycopy(res, 0, newRes, 1, res.length);
	            newRes[0] = '1';
	            return new String(newRes);
	        }else{
	            return new String(res);
	        }
	        
	    }
	 
	 /**
	  * smaller solution.
	  * convert to int for adding
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
	            aVal = (i >=0)? (a.charAt(i) - '0'): 0;
	            bVal = (j >=0)? (b.charAt(j) - '0'): 0;
	            int sum = aVal + bVal + carry;
	            carry = sum / 2;
	            sum = sum % 2;
	            res.insert(0, "" + sum);
	            i--;
	            j--;
	        }
	        return res.toString();
	        
	    }
}
