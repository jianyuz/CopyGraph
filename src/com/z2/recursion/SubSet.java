package com.z2.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubSet {
	
	public static void main(String[] args){
		
		List<Integer> myList = new ArrayList<Integer>();
		Collections.addAll(myList, 1, 2, 3);
		List<List<Integer>> sets = subset(myList);
		for(int i = 0; i< sets.size(); i++){
			System.out.println(Arrays.deepToString(sets.get(i).toArray(new Integer[1])));
		}
		
		System.out.println("-------");
		myList = new ArrayList<Integer>();
		Collections.addAll(myList, 1, 2, 3);
		List<List<Integer>> sets2 = subset2(myList);
		for(int i = 0; i< sets2.size(); i++){
			System.out.println(Arrays.deepToString(sets2.get(i).toArray(new Integer[1])));
		}
		
	}
	
	public static List<List<Integer>> subset(List<Integer> set){
		if(set.isEmpty()){
			List<List<Integer>> emptyRes = new ArrayList<List<Integer>>();
			emptyRes.add(new ArrayList<Integer>());
			return emptyRes;
		}
		List<List<Integer>> setList = new ArrayList<List<Integer>>();		
		
		int ele = set.remove(set.size()-1);//remove last element;
		
		List<List<Integer>> res = subset(set);
		setList.addAll(res);
		List<Integer> newItem = null;
		for(List<Integer> item : res){
			newItem = new ArrayList<Integer>(item);
			newItem.add(ele);
			setList.add(newItem);
		}
		return setList;
	}
	
	public static List<List<Integer>> subset2(List<Integer> set){
		if(set == null) return null;
		int size = set.size();
		int max = 1 << size;
		
		System.out.println("size " + max);
		List<List<Integer>> allsets = new ArrayList<List<Integer>>();
		
		List<Integer> subset;
		int k;
		int index;
		for(int i = 0; i< max; i++){
			subset = new ArrayList<Integer>();
			k = i;
			index = 0;
			while(k > 0){
				if((k & 1) > 0){
					subset.add(set.get(index));
				}
				k >>=1; //shift right to find next bit.
				index ++;
			}
			allsets.add(subset);
		}
		
		return allsets;
	}

}
