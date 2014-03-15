package com.z2.sumof3;

import java.util.ArrayList;
import java.util.Arrays;

public class SumOf3 {

	/**two pointer solution.
	 * increment left and right.
	 * O(N^2)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] input = new int[] { -4, -1, -1, 0, 1, 2};
		ArrayList<ArrayList<Integer>> res = sumOf3 (input, 0);
		
		for(ArrayList<Integer> item :res){
			System.out.println(Arrays.toString(item.toArray()));
		}
	}
	
	public static ArrayList<ArrayList<Integer>> sumOf3(int[] input, int sum){
		
		ArrayList<ArrayList<Integer>> triplets = new ArrayList<ArrayList<Integer>>();
		if(input == null || input.length < 3){
			return null;
		}
		//sort the input array.
		Arrays.sort(input);
		
		
		int left, right, target; //two pointers
		for(int i=0; i<input.length-2; i++){
			if(i > 0 && input[i] == input[i-1]) continue; //check equals at three level.
			//i level, left and right level.
			left = i+1;
			right = input.length -1;
			target = -1 * input[i]; 
			while(left < right){
				int k = input[left] + input[right];
				if(k < target){
					while(++left < right && input[left] == input[left - 1] );
				}else if(k > target){
					while(--right > left && input[right] == input[right + 1]);
				}else{
					ArrayList<Integer> triplet = new ArrayList<Integer>();
					triplet.add(input[i]);
					triplet.add(input[left]);
					triplet.add(input[right]);
					triplets.add(triplet);
					while(++left < right && input[left] == input[left - 1] );
					while(--right > left && input[right] == input[right + 1]);
				}
			}
		}
		
		return triplets;
		
	}

}
