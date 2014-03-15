import java.util.HashMap;
import java.util.Map;


public class RomanToInt {

	/**
	 * hard code every possible mapping.
	 * @param s
	 * @return
	 */
	 public int romanToInt(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(s == null || s.length() == 0) return 0;
	        
	        char[] chars = s.toCharArray();
	        
	        int res = 0;
	        for(int i=0; i< chars.length;){
	            char cur = chars[i];
	            char next = '0';
	            if(i+1 < chars.length)
	                next = chars[i+1];
	                
	            switch(cur)
	            {
	                case 'I':
	                    if(next == 'V'){
	                        res += 4;
	                        i += 2;
	                    }else if(next == 'X'){
	                        res += 9;
	                        i += 2;
	                    }else{
	                        res += 1;
	                        i++;
	                    }
	                    break;
	                case 'V':
	                    res += 5;
	                    i++;
	                    break;
	                case 'X':
	                    if(next == 'L'){
	                        res += 40;
	                        i += 2;
	                    } else if(next == 'C'){
	                        res += 90;
	                        i += 2;
	                    } else{
	                        res += 10;
	                        i++;
	                    }
	                    break;
	                case 'L':
	                    res += 50;
	                    i++;
	                    break;
	                case 'C': 
	                    if(next == 'D'){
	                        res += 400;
	                        i += 2;
	                    }else if(next == 'M'){
	                        res += 900;
	                        i+= 2;
	                    } else{
	                        res += 100;
	                        i++;
	                    }
	                    break;
	                case 'D':
	                    res += 500;
	                    i++;
	                    break;
	                case 'M':
	                    res += 1000;
	                    i++;
	                    break;
	                    
	            }
	        }
	        return res;
	    }
	 
	 /**
	  * Use a map to store the mapping.
	  * and compare the value to determine what to do next.
	  * @param s
	  * @return
	  */
	 public int romanToInt1(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(s == null || s.length() == 0) return 0;
	        
	        Map<Character, Integer> convert = new HashMap<Character, Integer>();
	        convert.put('I', 1);
	        convert.put('V', 5);
	        convert.put('X', 10);
	        convert.put('L', 50);
	        convert.put('C', 100);
	        convert.put('D', 500);
	        convert.put('M', 1000);
	        
	        
	        
	        char[] chars = s.toCharArray();
	        
	        int res = 0;
	        for(int i=0; i< chars.length;){
	            char cur = chars[i];
	            char next = '0';
	            if(i+1 < chars.length)
	                next = chars[i+1];
	            
	            Integer c1 = convert.get(cur);
	            Integer c2 = convert.get(next);
	            if(c1 != null && c2 != null && c1 < c2){
	                res += (c2 - c1);
	                i += 2;
	            }else{
	                res += c1;
	                i += 1;
	            }
	                    
	        }
	        return res;
	    }
	 
	 /**
	  * convert integer to roman numerals.
	  * Note. append order
	  * residual processing
	  * duplicate variable declaration.
	  * @param num
	  * @return
	  */
	 
	 public String intToRoman(int num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        //garantee to be between 1 to 3999.
	        //mod by 10 to get residual.
	        //then divide by then to get what's left.
	        
	        Map<Integer, String> convert = new HashMap<Integer, String>();
	        convert.put(1, "I");
	        convert.put(4, "IV");
		    convert.put(5, "V");
	        convert.put(9, "IX");
		    convert.put(10, "X");
	        convert.put(40, "XL");
		    convert.put(50, "L");
	        convert.put(90, "XC");
		    convert.put(100, "C");
	        convert.put(400, "CD");
		    convert.put(500,  "D");
	        convert.put(900, "CM");
		    convert.put(1000, "M");
	            
	        StringBuilder res = new StringBuilder();
	        
	        int cur = num;
	        int count = 0;
	        while(cur > 0){
	            int resi = cur % 10;
	            
	            StringBuilder partial = new StringBuilder();
	            while(resi > 0){
	                String mapped = convert.get(resi * (int)Math.pow(10, count));
	                if(mapped != null){
	                    partial.append(mapped);
	                    resi -= resi;
	                }else{
	                    if(resi > 5){
	                        partial.append(convert.get(5 * (int)Math.pow(10, count)));
	                        resi -= 5;
	                    }else{
	                        partial.append(convert.get(1 * (int)Math.pow(10, count)));
	                        resi -= 1;
	                    }
	                }
	            }
	            
	            res.insert(0, partial.toString());
	            cur = cur / 10;
	            count ++;
	        }
	        
	        return res.toString();
	        
	    }
}
