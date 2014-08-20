import java.util.ArrayList;


/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
[2,4],
[3,4],
[2,3],
[1,2],
[1,3],
[1,4],
]

 */

public class Combinations {

	public static void main(String[] args){
		ArrayList<ArrayList<Integer>> res = combine1(4, 2);
		for(ArrayList<Integer> tmp: res){
			System.out.println(tmp);
		}
		
	}
	
	public static ArrayList<ArrayList<Integer>> combine2(int n, int k){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(k == 0) return res;
		res.add(new ArrayList<Integer>());
		
		for(int i=1; i<=k; i++){ //pick number one by one.
			//from current partial results list.
			//continue to pick next one.
			//can only pick from after the current position.
			ArrayList<ArrayList<Integer>> nextRes = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> partial: res){
				int pos = partial.size() == 0 ? 1 : partial.get(partial.size() -1) + 1;
				for(int j= pos; j<=n; j++){
					ArrayList<Integer> tmp = new ArrayList<Integer>(partial);//grow from current partial. needs to duplicate the arraylist.
					tmp.add(j);
					nextRes.add(tmp);
				}
			}
			res = nextRes;//different from combine exchange res and nextRes.
		}
		
		return res;
	}
	
	
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(k == 0) return res;
        
        res.add(new ArrayList<Integer>());
        int count = 0;
        for(int i = 1; i<= k; i++){//pick comb element by element.
            count = res.size(); 
            while(count > 0){
                ArrayList<Integer> cur = res.get(0);//get head of res list build comb one by one from current.
                int start = cur.size() == 0? 1: cur.get(cur.size() -1) +1;
                for(int j = start; j <= n; j++){//avoid duplication. only add element bigger than current.
                    ArrayList<Integer> temp = new ArrayList<Integer>(cur);
                    temp.add(j);
                    res.add(temp);
                }
                res.remove(0); //remove the partial list from res after processing it.
                count --; 
            }
        }
        
        return res;
    }
	
	
	/**
	 * recursive version.
	 * one by one build partial list.
	 * dfs
	 * to ensure no repetition.
	 * curindex increase by one each time.
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> combine1(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(k == 0) return res;
        
        ArrayList<Integer> partial = new ArrayList<Integer>();
        
        helper(n, k, 1, partial, res);
        return res;
       
    }
    
    private static void helper(int n, int k, int cur, ArrayList<Integer>partial, ArrayList<ArrayList<Integer>> res){
        //partial partially built up combo.
        //cur current index in the candidate set 1 to n.
        //k stop at k level if we found k elements in partial list.
        //res result list.
        
        if(partial.size() == k){
            //end condition. stop when we already found the k combos
            res.add(new ArrayList<Integer>(partial)); //copy partial since it will be used by other dfs path.
            return;
        }
        
        for(int i=cur; i<=n; i++){
            partial.add(i);
            helper(n, k, i+1, partial, res);//recursion
            partial.remove(partial.size()-1);//recover the original partial state by removing the last added i
        }
        
    }
	
}
