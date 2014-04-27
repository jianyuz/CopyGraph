package memoryVisibility;

import java.util.Arrays;
import java.util.Comparator;

public class SortAnagrams {
	
	
	public static void main(String[] args){
		String s[] = {
		        "axyz", "abc", "yzax", "bac", "zyxa", "fg", "gf"
		 };
		
		sort(s);
		System.out.println(Arrays.toString(s));
	}
	/**
	 * anagram shall be close to each other.
	 * if anagram words, set the order to be close to each other.
	 * @param intput
	 */
	public static void sort(String[] input){
		Arrays.sort(input,new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				char[] s1Chars = o1.toCharArray();
				char[] s2Chars = o2.toCharArray();
				Arrays.sort(s1Chars);
				Arrays.sort(s2Chars);
				
				return new String(s1Chars).compareTo(new String(s2Chars));
			}
			
		});
	}
}
