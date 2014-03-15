package com.z2.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * sort arrays of string that all anagrams are together.
 * 
 * @author zhouzhou
 *
 */
public class CloseAnagram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] strings = new String[] {
				"test",
				"new",
				"jack",
				"zhou",
				"cool",
				"ackj",
				"ouzh"
		};
		sortStringArray(strings);
		System.out.println(Arrays.deepToString(strings));
	}
	
	public static void sortStringArray(String[] strings){
		Arrays.sort(strings, new StrComparator());
	}
	
	static class StrComparator implements Comparator<String>{
		
		@Override
		public int compare(String a, String b){
			char[] aChars = a.toCharArray();
			Arrays.sort(aChars);
			char[] bChars = b.toCharArray();
			Arrays.sort(bChars);
			
			
			int res =  new String(aChars).compareTo(new String(bChars));
			if(res == 0){
				return a.compareTo(b);
			}
			return res;
		}
	}

}
