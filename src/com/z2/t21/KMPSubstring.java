package com.z2.t21;

import java.util.Arrays;

public class KMPSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String pattern = "ababaa";
		String pattern = "abab";
		int[] border = buildBorderTable(pattern);
		System.out.println(Arrays.toString(border));
		
		kmpMatch("ababab", pattern);
		
		pattern = "ababaa";
		border = buildBorderTable(pattern);
		System.out.println(Arrays.toString(border));
		
		kmpMatch("abababaaefababaa", pattern);
	}
	
	
	public static void kmpMatch(String input, String pattern){
		if(input == null || pattern == null || pattern.length() == 0) return;
		int[] border = buildBorderTable(pattern);
		
		int iLen = input.length();
		int pLen = pattern.length();
		
		int m = 0; //current position in input stream
		int j = 0; //current position in pattern
		while (m  + j < iLen){
			while(j < pLen && m + j < iLen && input.charAt(m + j) == pattern.charAt(j)){ j++;}
			if(j >= pLen){
				System.out.println("match at position " + m ); 
				j--; //back track after matching.
			}
			
			//adjust match position and j.
			//System.out.println(j);
			m = m + j - border[j];//always increase m.
			j = border[j] >=0 ? border[j]: 0;//border may be -1
			//maximum roll back is bounded by j
			//System.out.println(" m " + m + " j " + j);
		}
		
		System.out.println("done");
			
	}
	/*
	 * find repeating pattern in substring
	 * a border r of x can be extended by a 
	 * if ra is a border of xa
	 * border[i] is the widest border of the prefix of length i
	 * of the pattern.
	 * length i = 0; border[0] = -1
	 * border always start on the left edge.
	 * extends the boarder on the right border end.
	 * p[i] == p[j].
	 * border proper substring can be extended from the previous
	 * otherwise fall back to the 
	 */
	public static int[] buildBorderTable(String pattern){
		int len = pattern.length();
		int[] border = new int[len];
		
		//keep two pointers.
		int i = 0;
		int j = -1; //length of border for substring of length i;
		border[0] = -1; //init condition. must have.
		//element 0 has no border to the left.
		
		//Provided that the values b[0], ..., b[i] are already known, 
		//the value of b[i+1] is computed by checking if a border of the prefix p0 ... pi-1 can be extended by symbol pi. This is the case if pb[i] = pi (Figure 3).
		
		//..... b[i].... i
		//       j		
		while(i < len -1){
			while(j >=0 && pattern.charAt(i) != pattern.charAt(j)){
				j = border[j];//adjust base border width for substring of length i
			}
			i++;
			j++;
			border[i] = j;
		}
		
		return border;
		
	}

}
