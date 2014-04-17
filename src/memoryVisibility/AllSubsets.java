package memoryVisibility;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {

	public static void main(String[] args){
		int[] set = new int[]{2, 3, 6, 7};
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>());//add empty set.
		subsets(res, new ArrayList<Integer>(), set, 0, set.length);
		for(List<Integer> li: res){
			System.out.println(li.toString());
		}
		
		System.out.println();
		res = new ArrayList<List<Integer>>();
		subsets1(res, set, set.length);
		for(List<Integer> li: res){
			System.out.println(li.toString());
		}
	}
	
	/**
	 * a set is built using elements before index.
	 * iterate through the elements after the index.
	 * 
	 * @param res
	 * @param current
	 * @param set
	 * @param index
	 * @param n
	 */
	public static void subsets(List<List<Integer>> res, List<Integer> current, int[] set, int index, int n){
		//terminate condition
		if(index == n ){
			return; //search done.
		}
		
		for(int i=index; i<n; i++){
			current.add(set[i]);
			res.add(new ArrayList<Integer>(current));
			subsets(res, current, set, i+1, n);
			current.remove(current.size() -1);//recover current for next round.
		}
		
	}
	
	//number from 0 to 2 power of n-1, check bit number if it is 1 add corresponding element into the set.
	public static void subsets1(List<List<Integer>> res, int[] set, int n){
		int count = 1 << n;
		
		for(int i = 0; i< count; i++){
			List<Integer> tmp = new ArrayList<Integer>();
			int num = i; //copy number
			for(int j= 0; j<n; j++){
				if((num & 1) == 1){
					tmp.add(set[j]);
				}
				num >>=1; //incrementally shift bi to right.
			}
			res.add(tmp);
		}
		
	}
}
