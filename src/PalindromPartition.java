import java.util.ArrayList;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
  
  use dynamica programming to get all the possible palindrome
  then search result in the matrix for result list.
  using recursion
  note the len limit for growing dp
  from 1 to n-1
  limit of i relative to length
  0 to n-len
  i + len < n;
 * @author zhouzhou
 *
 */

public class PalindromPartition {

	public static void main(String[] args){
		ArrayList<ArrayList<String>> resList = partition("cdd");
		System.out.println(resList);
	}
	
	public static  ArrayList<ArrayList<String>> partition(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //get all possible substring partition and check if it is palindrome.
        
        ArrayList<ArrayList<String>> resList= new ArrayList<ArrayList<String>>();
        
        ArrayList<String> emptyList = new ArrayList<String>();
        
        
         
        if(s == null) return resList;
        if(s.length() == 0) {
            resList.add(emptyList);
            return resList;
        }
        
        int n = s.length();
        
        boolean[][] isPalin = new boolean[n][n];
        
        for(int i=0; i< n; i++){
            isPalin[i][i] = true;
        }
        
        for(int len = 1; len < n; len ++){
            for(int i= 0; i< n-len; i++){
                if(len == 1) isPalin[i][i+len] = (s.charAt(i) == s.charAt(i+len));
                else
                isPalin[i][i+len] = isPalin[i+1][i+len-1] && (s.charAt(i) == s.charAt(i+len));
            }
        }
        
        //get all the possible combinations.
        
        genResult(isPalin, s, n, 0, resList, emptyList);
        return resList;
        
    }
    
    
    public static void genResult(boolean[][] isPalin, String s, int n, int pos, ArrayList<ArrayList<String>> resList, ArrayList<String> res){
        if(pos == n){
            resList.add(new ArrayList<String>(res)); //make a copy.
            return;
        } 
            
        for(int i=pos; i< n; i++){
            if(isPalin[pos][i]){
                res.add(s.substring(pos, i+1));
                genResult(isPalin, s, n, i+1, resList, res);
                res.remove(res.size()-1);
            }
            
        }
        
    }
}
