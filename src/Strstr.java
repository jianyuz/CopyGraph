import java.math.BigInteger;


public class Strstr {

	public static void main(String[] args){
		//System.out.println(strStr("a", "a"));
		//System.out.println(strStr("abeabcabcabe", "abcabe"));
		//System.out.println(strStr1("abeabcabcabe", "abcabe"));
		System.out.println(strStr1("ababaabbbbababbaabaaabaabbaaaabbabaabbbbbbabbaabbabbbabbbbbaaabaababbbaabbbabbbaabbbbaaabbababbabbbabaaabbaabbabababbbaaaaaaababbabaababaabbbbaaabbbabb", "abbabbbabaa"));
		//System.out.println(strStr1("abbabaaaabbbaabaabaabbbaaabaaaaaabbbabbaabbabaabbabaaaaababbabbaaaaabbbbaaabbaaabbbbabbbbaaabbaaaaababbaababbabaaabaabbbbbbbaabaabaabbbbababbbababbaaababbbabaabbaaabbbba", "bbbbbbaa"));
	}
	public static String strStr(String haystack, String needle) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(haystack == null || needle == null) return null;
        
        if(haystack == "" && needle == "") return "";
        if(haystack == "" && needle != "") return null;
        if(haystack != "" && needle == "") return haystack;
        
        char[] mchars = haystack.toCharArray();
        char[] nchars = needle.toCharArray();
        
        int[] table = jumpTable(needle);
        
        int m = mchars.length;
        int n = nchars.length;

        for(int i=0; i<= m-n;){
            int j = 0;
            while(j<n && nchars[j] == mchars[i]) {
                i++;
                j++; //increment both position
            }
            if(j == n) return haystack.substring(i-n); //matched whole needle string.
            else{ //didn't match the whole string.
            	i = i-table[j]; //maximum jump back is n-1. //jump back is less than the 
            	//loop execution time. so i is increasing in each round.
            	//total maximum rollback is bounded by i; at most 2m execution.
                //jump back j
            	if(table[j] > 0) j = table[j];
            	else j=0;
            }
            
        }
        return null;

    }
    
    
    //calculate the edge of the string. 
    // what's edge, max length substring at the boundary of the string.
    //edge's nature. edge can be extended.
    //from it to get the jump table.
    //then use jump table to match the main string.
    public static int[] jumpTable(String needle){
        int[] table = new int[needle.length()];
        
        table[0] = -1;
        if(table.length <=1) return table;
        table[1] = 0;
        
        char[] chars = needle.toCharArray();
        
        int j=0;
        for(int i=2; i<chars.length;){
            if(chars[j] == chars[i-1]){
                j++;
                table[i] = j;
                i++;//i++ need to be the last.
            }else if(j > 0){//match fails, fall back. table[j] = -1 in the case.
                j= table[j];
            }else{//nothing to fall back, 
                table[i] = 0; //fall back to 0;
                i++;
            }
        }
        
        return table;
        
    }
    
    
    /**
     * User big integer for rolling hash.
     * Note roll hash timecomplexity O(m+n) on average
     * worst O(m*n)
     * @param haystack
     * @param needle
     * @return
     */
    public static String strStr1(String haystack, String needle) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(haystack == null || needle == null) return null;
        if(haystack == "" && needle == "") return "";
        if(haystack == "" && needle != "") return null;
        if(haystack != "" && needle =="") return haystack;
        
        //Rabin Karp algorithm
        char[] mchars = haystack.toCharArray();
        char[] nchars = needle.toCharArray();
        
        int m = mchars.length;
        int n = nchars.length;
        BigInteger patternHash = computeHash(nchars, 0, n);
        
        BigInteger pHash = BigInteger.valueOf(0);
        for(int i=0; i<= m-n; i++){
            BigInteger hash = compRollingHash(mchars, i, n, pHash);
            if(hash.equals(patternHash)){
                //compare string char by char
                int k = i;
                int j =0;
                while(j< n && mchars[k] == nchars[j]){
                    j++;
                    k++;
                }
                if(j==n) return haystack.substring(k - n);
            }
            pHash = hash;
        }
        return null;
        
    }
    
    public static BigInteger compRollingHash(char[] chars, int i, int n, BigInteger pHash){
        int base = 101;
        if(pHash.intValue() == 0) return computeHash(chars, i, n);
        else{
        	BigInteger factor = BigInteger.valueOf(base).pow(n-1);
        	BigInteger vi = factor.multiply(BigInteger.valueOf((int)chars[i-1]));
        	
        	return pHash.subtract(vi).multiply(BigInteger.valueOf(base)).add(BigInteger.valueOf((int)chars[i+n-1]));
        }//math.pow get double back need to convert to int.
    }
    
    public static BigInteger computeHash(char[] chars, int s, int n){
        int base = 101;
        BigInteger res = BigInteger.valueOf(0);
        for(int i=s; i< s+n; i++){
            BigInteger factor = BigInteger.valueOf(base).pow(n-1-(i-s));
            res = res.add(factor.multiply(BigInteger.valueOf((int)chars[i]))); 
        }
        return res;
    }

}
