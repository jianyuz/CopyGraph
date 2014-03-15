package com.z2.classes;

import java.util.AbstractList;
import java.util.List;

public class skelton {
	
	static List<Integer> intArrayAsList(final int[] a){
		if(a == null){
			throw new NullPointerException();
		}
		/** user provide their own implementation of 
		 * the skeleton abstract class
		 */
		return new AbstractList<Integer>(){

			@Override
			public Integer get(int index) {
				// TODO Auto-generated method stub
				return a[index];
			}
			
			@Override public Integer set(int i, Integer val){
				int oldVal = a[i];
				a[i] = val;
				return oldVal;
			}

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return a.length;
			}
			
		};
	}
	public static void main(String[] args){
		List<Integer> list = intArrayAsList( new int[]{1, 2, 3, 5});
		System.out.println(list.get(3));
	}
}
