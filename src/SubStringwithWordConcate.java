import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
 * @author zhouzhou
 *
 */
public class SubStringwithWordConcate {

	public static void main(String[] args){
		ArrayList<Integer> res = findSubstring("aaa", new String[] {"a", "a"});
		System.out.println(res);
	}
	
	/**
	 * use hash comparison method.
	 * make sure all the hashes in L is covered by continuous block of S.
	 * use a arrayList to track found hashes.
	 * but doesn't pass the long run time.
	 * @param S
	 * @param L
	 * @return
	 */
	public static ArrayList<Integer> findSubstring(String S, String[] L) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //words of all same length?
        //then the sliding window length is num of words times len.
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int[] lHash = new int[L.length];
        int n = L[0].length();
        
        for(int i=0; i< L.length; i++){
            lHash[i] = BKDRHash(L[i]);
        }
        
        for(int i=0; i<= S.length() - n * L.length;){
            int j = i;
            int h= 0, foundPos = -1;
            ArrayList<Integer> found = new ArrayList<Integer>();
            
            for(int k = 0; i< L.length; i++){
                h = BKDRHash(S.substring(j, j+n));
                j=j+n;
                foundPos = LContains(lHash, h, found);
                if(foundPos == -1) break;
                else found.add(foundPos);
            }while( j+n <= S.length() && found.size() <= lHash.length);
            
            if(found.size() == lHash.length) res.add(i);
            i++;
        }
        return res;
    }
    
    public static int LContains(int[] lHash, int h, ArrayList<Integer> found){
         for(int j=0; j< lHash.length; j++){
                if(h == lHash[j] && !found.contains(j)){
                    return j;
                }
        }
        return -1;
    }
    public static int BKDRHash(String w){
        
        int hash = 0;
        int seed = 31;
        
        for(int i=0; i<w.length(); i++){
            hash = hash * seed + w.charAt(i);
        }
        
        return hash;
    }
    
    /**
     * keep the same logic as before.
     * the changes are use map to keep track of the hash pattern and the number of times the pattern appears
     *  this helps us speed up the matching process.
     *  
     * @param S
     * @param L
     * @return
     */
    public ArrayList<Integer> findSubstring1(String S, String[] L) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //words of all same length?
        //then the sliding window length is num of words times len.
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int n = L[0].length();
        Map<Integer, Integer> lHash = new HashMap<Integer, Integer>();
        //hash maps to position in L.
        
        for(int i=0; i< L.length; i++){
            int hash = BKDRHash(L[i]);
            if(lHash.get(hash) == null){
                lHash.put(hash, 1);
            }else{
                lHash.put(hash, lHash.get(hash) + 1);
            }
        }
        
        Map<Integer, Integer> found = new HashMap<Integer, Integer>();
         
        for(int i=0; i<= S.length() - n * L.length; i++){
            int h= 0;
           
            found.clear();
            int k = 0;
            for(k = 0; k< L.length; k++){
                if((i + (k+1) * n) > S.length()) break;
                h = BKDRHash(S.substring(i+k*n, i+(k+1)*n));
                
                if(lHash.get(h) == null) break; //not in the pattern hash. mismatch.
                
                if(found.get(h) == null){
                    found.put(h, 1);
                }else{
                    found.put(h, found.get(h) + 1); //accumulate the found hash pattern.
                } 
                
                if(found.get(h) > lHash.get(h)) break; //overflow condition.
            }
            
            if(k == L.length) res.add(i); //finished the whole pattern matching.
            
        }
        return res;
    }
    
    
}
