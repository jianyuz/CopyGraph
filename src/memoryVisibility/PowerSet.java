package memoryVisibility;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
	
	public static void main(String[] args){
		List<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);
		//input.add(4);
		
		List<List<Integer>> powerset = generatePowerSet(input);
		
		System.out.println("powerset size " + powerset.size());
		for(List<Integer> component: powerset){
			System.out.println(component.toString());
		}
	}
	
	//empty 
	// 1 element
	// 2 elements
	// 3 elements
	public static List<List<Integer>> generatePowerSet(List<Integer> input){
		
		List<List<Integer>> powerset = new ArrayList<List<Integer>>();
		List<Integer> empty = new ArrayList<Integer>();
		powerset.add(empty); //empty set.
		
		int n = input.size();
		
		workingPowerSet(powerset, empty,  0, n, input);
		
		return powerset;
	}
	
	public static void workingPowerSet(List<List<Integer>> powerset, List<Integer> base, int i, int n, List<Integer> input ){
		if( i == n){
			return;
		}
		int j = i;
		while(j < n){
			List<Integer> newBase = new ArrayList<Integer>(base);
			newBase.add(input.get(j));
			powerset.add(newBase);
			workingPowerSet(powerset, newBase, j+1, n, input);
			j ++;
		}
	}

}
