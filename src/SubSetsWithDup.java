import java.util.ArrayList;
import java.util.Arrays;


public class SubSetsWithDup {

	public static void main(String[] args){
		int[] num = new int[]{1,2,3};
		subsetsWithDup(num);
		
	}
	public static ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        ArrayList<Integer> emptyList = new ArrayList<Integer>();
        res.add(emptyList);
        
        if(num == null) return res;
        
        Arrays.sort(num);
        
        int size;
        int duplicate = 0;
        ArrayList<Integer> tempList= null, cloneList = null;
        for(int i=0; i< num.length; i++){
            size = res.size();
            if(i >= 1 && num[i] == num[i-1])
                duplicate ++;
            else
                duplicate = 0; //count the duplicate count so far.
            for(int j = 0; j< size; j++){
                tempList = res.get(j);
               
                 if(duplicate > 0){ //if there is duplicate. count the number of duplicate
                	 //in res list. only add one duplicate to the list if we are 
                	 //increasing the number of duplicate in result by one.
                    int count = 0;
                    for(int k = tempList.size() -1; k >=0; k--){
                        if( tempList.get(k) == num[i]) count ++;
                        else break;
                    }
                    if(count != duplicate) continue;
                 }
                        
                
                cloneList = (ArrayList<Integer>)tempList.clone();
                cloneList.add(num[i]);
                res.add(cloneList);
            }
        }
        
        return res;
    }
	
	/**
	 * a simpler version.
	 * skip the existing subsets if they have been apended with the same elements.
	 * 
	 * @param num
	 * @return
	 */
	 public ArrayList<ArrayList<Integer>> subsetsWithDup1(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        ArrayList<Integer> emptyList = new ArrayList<Integer>();
	        res.add(emptyList);
	        
	        if(num == null) return res;
	        
	        Arrays.sort(num);
	        
	        int size;
	        ArrayList<Integer> tempList= null;
	        //incrementally build the power set.
	        //from empty set.
	        //add one element then 2 element, etc.
	        //[]
	        //add [1]
	        // add [2],[1,2]
	        //if next is 2 and we add to the previous 2 again.
	        //duplicate.
	        //need to skip, start with the newly added subsets.
	        
	        int start = 0;
	        for(int i=0; i< num.length; i++){
	            size = res.size();
	            
	            for(int j=start; j< size; j++){
	                tempList = new ArrayList<Integer>(res.get(j));
	                tempList.add(num[i]);
	                res.add(tempList);
	            }
	            
	            if(i< num.length -1 && num[i+1] == num[i]){//duplicate to previous element.
	                start = size; //ignore those subset we have generated with the same elements.
	                //only add to the new set.
	            }else{ 
	                start = 0;
	            }
	        }
	        
	        return res;
	    }
	 
	 /**
	  * generate subsets without duplication.
	  * @param S
	  * @return
	  */
	 public ArrayList<ArrayList<Integer>> subsets(int[] S) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        ArrayList<Integer> emptyList = new ArrayList<Integer>();
	        
	        res.add(emptyList);
	        
	        if(S == null) return res;
	        
	        Arrays.sort(S);
	        
	        int size;
	        ArrayList<Integer> tmpList;
	        for(int i = 0; i< S.length; i++){
	                size = res.size();
	                for(int j = 0; j< size; j++){
	                    tmpList = new ArrayList<Integer>(res.get(j));
	                    tmpList.add(S[i]);
	                    res.add(tmpList);
	                }
	        }
	        
	        return res;
	    }
	 
	 /**
	  * recursive solution
	  * @param num
	  * @return
	  */
	 
	 public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(num == null) return res;
	        
	        ArrayList<Integer> emptyList = new ArrayList<Integer>();
	        
	        Arrays.sort(num);//sort it first.
	        
	        helper(num, 0, emptyList, res);
	        return res;
	    }
	    
	    public void helper(int[] num, int start, ArrayList<Integer> partial, ArrayList<ArrayList<Integer>> res){
	        res.add(partial);//add partial to result.
	        
	        ArrayList<Integer> temp;
	        for(int i=start; i<num.length; i++){
	            if(i > start  && num[i] == num[i-1]) continue; //skip duplicate element.
	            //i must greater than start.
	            temp = new ArrayList<Integer>(partial);
	            temp.add(num[i]);
	            helper(num, i + 1, temp, res); //next start i + 1
	        }
	    }
}
