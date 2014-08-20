import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * 变位词。
 * different from palindrome.
 * 
 * sort the chars in each word.
 * keep the anagram in hashmap
 * only output anagram group with size bigger than 1.
 * 
 * wlLog(wl) * number of words.
 * 
 * n storage.
 * 
 * @author zhouzhou
 *
 */
public class Anagrams {

	public List<String> anagrams(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        List<String> res = new ArrayList<String>();
        if(strs == null || strs.length == 0) return res;
        
        Map<String, ArrayList<String>> anaMap = new HashMap<String, ArrayList<String>>();
        
        for(String str: strs){
                String sStr = sortString(str);
                if(anaMap.get(sStr) == null){
                    ArrayList<String> nList = new ArrayList<String>();
                    nList.add(str);
                    anaMap.put(sStr, nList); 
                }else{
                    anaMap.get(sStr).add(str);
                }
        }
        
        //get all the anagram groups and put in the result list.
        Set<String> keys = anaMap.keySet();
        for(String key: keys){
            List<String> nList = anaMap.get(key);
            if(nList.size() > 1)
                res.addAll(nList);
        }
        
        return res;
    }
    
    public String sortString(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    
    /**
     * solutions with bkdr to calculate the string hash.
     * @param strs
     * @return
     */
    public ArrayList<String> anagrams1(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<String> res = new ArrayList<String>();
        if(strs == null || strs.length == 0) return res;
        
        Map<Long, ArrayList<Integer>> anaMap = new HashMap<Long, ArrayList<Integer>>();
        
        for(int i=0; i< strs.length; i++){
                String sStr = sortString(strs[i]);
                long hash = BKDRHash(sStr); //may use string hashCode method directly.
                
                if(anaMap.get(hash) == null){
                    ArrayList<Integer> nList = new ArrayList<Integer>();
                    nList.add(i);
                    anaMap.put(hash, nList); 
                }else{
                    anaMap.get(hash).add(i);
                }
        }
        
        //get all the anagram groups and put in the result list.
        Set<Long> keys = anaMap.keySet();
        for(Long key: keys){
            ArrayList<Integer> nList = anaMap.get(key);
            if(nList.size() > 1){
                for(Integer i: nList){
                    res.add(strs[i]);
                }
            }
        }
        
        return res;
    }
    
    //don't use this hash code as keys. collision.
    public long BKDRHash(String str){
        
        long res = 0;
        if(str == null) return res;
        
        long hash = 131;
        
        int i = 0;
        while(i < str.length()){
            res = res * hash + str.charAt(i);
            i++;
        }
        return res;
    }
    
}
