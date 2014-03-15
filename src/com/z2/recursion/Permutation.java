package com.z2.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
	
	public static void main(String[] args){
		String input = "abc";
		List<String> res = permutate(input);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	public static List<String> permutate(String input){
		if(input == null) return null;
		if(input.length() == 0) return null;
		if(input.length() == 1) {
			List<String> res = new ArrayList<String>();
			res.add(input);
			return res;
		}
		
		String subInput = input.substring(1);
		String insert = input.substring(0,1);
		List<String> subRes = permutate(subInput);
		List<String> res = new ArrayList<String>();
		for(String item: subRes){
			for(int i = 0; i<= item.length(); i++){
				StringBuffer sb = new StringBuffer(item);
				sb.insert(i, insert);
				res.add(sb.toString());
			}		
		}
		return res;
	}

}
