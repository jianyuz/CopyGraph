import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

get each of the elements in the num.
then prepend it to the permutation result.
add it to the res.

 * @author zhouzhou
 *
 */
public class Permutations {

	 public ArrayList<ArrayList<Integer>> permute(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(num == null || num.length == 0) return res;
	        
	        ArrayList<Integer> avails = new ArrayList<Integer>();
	        for(int i=0; i< num.length; i++){
	            avails.add(i);
	        }//add all the indices as available.
	        
	        return doPermute(num, avails);
	    }
	    
	    public ArrayList<ArrayList<Integer>> doPermute(int[] num, ArrayList<Integer> avails){
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        if(avails.size() == 1){
	             ArrayList<Integer> permRes = new ArrayList<Integer>();
	             permRes.add(num[avails.get(0)]);
	             res.add(permRes);
	             return res;
	        }
	        
	        ArrayList<ArrayList<Integer>> temp = null;
	        
	        int size = avails.size();
	        int count = 0;
	        while(count < size){
	            int index = avails.remove(0);//remove head index elements.
	            int item = num[index];
	            //remove the item from avails.
	            temp = doPermute(num, avails);
	            for(ArrayList<Integer> list: temp){
	                //prepend the element to each of the result list.
	                list.add(0, item);
	                res.add(list);
	            }
	            //add the item back to avails.
	            avails.add(index);
	            count ++;
	        }
	        
	        return res;
	    }
	    
	    //alternative solution.
	    //similiar like above.
	    //but use position swap.
	    //not the list flag to mark the used position.
	    public ArrayList<ArrayList<Integer>> permute1(int[] num) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(num == null || num.length == 0) return res;
	        
	        int n = num.length-1;
	        doPermute(num, 0, n, res);
	        return res;
	    }
	    
	    //do the permutation by position swapping.
	    //one element to swap with rest of elements.
	    //pick one element at the position.
	    //after ward fix the element and permute the others.
	    //then next element to swap with else.
	    
	    public void doPermute(int[] num, int level, int n, ArrayList<ArrayList<Integer>> res){
	        if( level == n){// can't add int array directly as arrayList since it hold object.
	        	//not primitive type.
	             ArrayList<Integer> temp = new ArrayList<Integer>();
	             for(int i: num){
	                 temp.add(i);
	             }
	             res.add(temp);
	             return;
	        }
	        
	        for(int i=level; i<=n; i++){
	            int tmp = num[level];
	            num[level] = num[i];
	            num[i] = tmp;
	            
	            //fix the element.
	            doPermute(num, level + 1, n, res);
	            //swap back to revert the change for next permutation.
	            tmp = num[level];
	            num[level] = num[i];
	            num[i] = tmp;
	        }        
	        return;
	    }
	    
	    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
            // Start typing your Java solution below
            // DO NOT write main() function
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        
	        if(num == null || num.length == 0) return res;
	        
	        int n = num.length-1;
	        doPermute1(num, 0, n, res);
	        return res;
	    }
	    
	    //do the permutation by position swapping.
	    //one element to swap with rest of elements.
	    //pick one element at the position.
	    //after ward fix the element and permute the others.
	    //then next element to swap with else.
	    
	    public void doPermute1(int[] num, int level, int n, ArrayList<ArrayList<Integer>> res){
	        if( level == n){// can't add int array directly as arrayList since it hold object.
	        	//not primitive type.
	             ArrayList<Integer> temp = new ArrayList<Integer>();
	             for(int i: num){
	                 temp.add(i);
	             }
	             res.add(temp);
	           ;
	        }
            
	        Set<Integer> showed = new HashSet<Integer>();//add hashset to check uniqueness of item used.
	        for(int i=level; i<=n; i++){
                if(showed.contains(num[i])) continue;
                else showed.add(num[i]);
	            int tmp = num[level];
                num[level] = num[i];
                num[i] = tmp;
                doPermute(num, level + 1, n, res);
	            //swap back to revert the change for next permutation.
	            tmp = num[level];
	            num[level] = num[i];
	            num[i] = tmp;
	        }
	        return;
	    }
	    
	    
	    public void doPermute2(int[] num, int level, int n, ArrayList<ArrayList<Integer>> res){
	        if( level == n){// can't add int array directly as arrayList since it hold object.
	        	//not primitive type.
	             ArrayList<Integer> temp = new ArrayList<Integer>();
	             for(int i: num){
	                 temp.add(i);
	             }
	             res.add(temp);
	           ;
	        }
            
	        for(int i=level; i<=n; i++){
                if(!shouldSwap(level, i, num)) continue;
	            int tmp = num[level];
                num[level] = num[i];
                num[i] = tmp;
                doPermute(num, level + 1, n, res);
	            //swap back to revert the change for next permutation.
	            tmp = num[level];
	            num[level] = num[i];
	            num[i] = tmp;
	        }
	        return;
	    }
        
        //if there is duplicate number for number before i.
        //return should not swap
        public boolean shouldSwap(int level, int i, int[] num){
            for(int j= level; j< i; j++){
                if(num[j] == num[i]) return false;
            }
            return true;
        }
}
