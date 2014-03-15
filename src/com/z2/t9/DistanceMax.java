package com.z2.t9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DistanceMax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		distMax();
		maxIndexDiff();
	}
	
	//o(n) solution
	public static void maxIndexDiff(){
		int[] a = new int[] { 4, 3, 5, 2, 1, 3, 2, 3};
		
		//construct auxiliary array. the min of all the left elements
		int[] lMin = new int[a.length];
		lMin[0] = a[0];
		for(int i= 1; i< a.length; i++){
			lMin[i] = Math.min(a[i], lMin[i-1]);
		}
		
		System.out.println(Arrays.toString(lMin));
		int[] rMax = new int[a.length];
		rMax[a.length -1] = a[a.length -1];
		for(int j=a.length - 2; j>=0; j--){
			rMax[j] = Math.max(a[j], rMax[j+1]);
		}
		System.out.println(Arrays.toString(rMax));
		
		
		//construct auxiliary array. the max of all the right elements.
		
		//if there is an element smaller then a[i], don't consider i
		//if there is an element greater than a[j] don't consider j
		//find maximum j-i that a[i] < a[j].
		
		//advance i, j advance i if min >= max
		//advance j if min < max to increase i and j diff
		int maxDiff = -1;
		int i =0, j = 0;
		int resi =-1, resj= -1;
		while(i < a.length && j < a.length){
			if(lMin[i] >= rMax[j]){//min i is >= to the biggest on all the right elements. find smaller one by advancing i.
				i++;
			}else{//check if it is the max diff. and advance j.
				maxDiff = Math.max(maxDiff, j - i);
				if(maxDiff == (j-i)){
					resi = i;
					resj = j;
				}
				j++;
			}
		}
		
		System.out.println("max diff is:" + maxDiff + " with  i:" + resi + " j:" + resj);
	}
	
	public class Entry implements Comparable<Entry>{
		int value;
		int index;
		
		public Entry(int value, int index){
			this.value = value;
			this.index = index;
		}
		
		@Override
		public int compareTo(Entry b) {
			return value - b.value;
		}
		
		
	}
	
	
	
	//sort and keep the original index.
	
	public static void distMax(){
		DistanceMax dm = new DistanceMax();
		
		int[] a = new int[] { 4, 3, 5, 2, 1, 3, 2, 3};
		
		List<Entry> b = new ArrayList<Entry>();
		
		for(int i=0; i<a.length; i++){
			b.add(dm.new Entry(a[i], i));
		}
		
		Collections.sort(b);
		
		int[] indices = new int[b.size()];
		
		for(int i=0; i< b.size(); i++){
		
			indices[i] = ((Entry)(b.get(i))).index;
		}
		
		System.out.println(Arrays.toString(indices));
		
		//scan from right to find max index difference
		int maxRightIndex = -1;
		int maxDiff = -1;
		int resi=-1, resj=-1;
		for(int j=indices.length-1; j>=0; j--){
			if(indices[j] > maxRightIndex){
				maxRightIndex = indices[j];
			}
			
			if(maxRightIndex - indices[j] > maxDiff && a[maxRightIndex] != a[indices[j]]){
				maxDiff = maxRightIndex -indices[j];
				resi = indices[j];
				resj= maxRightIndex;
			}
		}
		
		System.out.println("max diff is:" + maxDiff + " with  i:" + resi + " j:" + resj);
	}

}
