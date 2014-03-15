import java.util.ArrayList;
import java.util.Arrays;
/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:

All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, � , ak) must be in non-descending order. (ie, a1 ? a2 ? � ? ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
 * @author zhouzhou
 *
 */

public class CombinationSum {

	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(candidates == null || candidates.length == 0) return res;
        
        //empty result set if input candidates set is empty.   
        int n = candidates.length;
        
        Arrays.sort(candidates);
        
        doComboSum(candidates, n, 0, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
	/**
	 * depth first search solution using recursion.
	 * take each element in the candidate array.
     * add the elements in array with position greater or equal to it.
     * if sum is equal to the target chop of the result set.
	 * @param candidates
	 * @param n
	 * @param sum
	 * @param target
	 * @param pos
	 * @param pRes
	 * @param res
	 */
    public void doComboSum(int[] candidates, int n, int sum,  int target, int pos, ArrayList<Integer> pRes, ArrayList<ArrayList<Integer>> res){
        for(int i= pos; i< n; i++){
        	if(i > pos && candidates[i] == candidates[i-1]) continue; //this ensure solution doesn'
        	//contain duplicate combinations.
            pRes.add(candidates[i]);
            sum += candidates[i];
            if(sum == target){
                res.add(new ArrayList<Integer>(pRes));//copy constructor to clone
            }else if(sum < target){
                doComboSum(candidates, n, sum, target, i, pRes, res);
            }
            sum -= candidates[i]; //after depth search, recover state of sum and pRes.
            pRes.remove(pRes.size() -1);
        }
        
    }
    
    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 



     * @param candidates
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(candidates == null || candidates.length == 0) return res;
        
        //empty result set if input candidates set is empty.   
        int n = candidates.length;
        
        Arrays.sort(candidates);
        
        doComboSum2(candidates, n, 0, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    /**
	 * depth first search solution using recursion.
	 * take each element in the candidate array.
     * add the elements in array with position greater or equal to it.
     * if sum is equal to the target chop of the result set.
	 * @param candidates
	 * @param n
	 * @param sum
	 * @param target
	 * @param pos
	 * @param pRes
	 * @param res
	 */
    public void doComboSum2(int[] candidates, int n, int sum,  int target, int pos, ArrayList<Integer> pRes, ArrayList<ArrayList<Integer>> res){
        for(int i= pos; i< n; i++){
        	//when add next candidates, compare current one with previous one.
        	//note start from the pos.
            if(i > pos && candidates[i] == candidates[i-1]) continue;
            pRes.add(candidates[i]);
            sum += candidates[i];
            if(sum == target){
                res.add(new ArrayList<Integer>(pRes));//copy constructor to clone
            }else if(sum < target){
                doComboSum(candidates, n, sum, target, i+1, pRes, res); //here increment pos by one.
            }
            sum -= candidates[i]; //after depth search, recover state of sum and pRes.
            pRes.remove(pRes.size() -1);
        }
        
    }
}
