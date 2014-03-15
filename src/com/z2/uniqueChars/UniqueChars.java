package com.z2.uniqueChars;

import java.util.Arrays;
import java.util.BitSet;

public class UniqueChars {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(containUniqueChars("abcdefa"));
		System.out.println(containUniqueChars("gabcdef"));
		System.out.println(reverseString("gabcdef"));
		
		char[] chars = "ababab".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate(chars)));
		
		chars = "aaaa".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate(chars)));
		chars = "abcd".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate(chars)));
		chars = "aaabbb".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate(chars)));
		
		System.out.println(String.valueOf(chars, 0, removeDuplicate(null)) + "---");
		
		
		chars = "ababab".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate1(chars)));
		
		chars = "aaaa".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate1(chars)));
		chars = "abcd".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate1(chars)));
		chars = "aaabbb".toCharArray();
		System.out.println(String.valueOf(chars, 0, removeDuplicate1(chars)));
		
		System.out.println(String.valueOf(chars, 0, removeDuplicate1(null)) + "---");
		
		String a = "abcda";
		String b = "acbda";
		System.out.println("Anagram: " + checkAnagram(a, b));
		
		a = "abcda";
		b = "acbd";
		System.out.println("Anagram: " + checkAnagram(a, b));

		a = "abcda";
		b = "acbdc";
		System.out.println("Anagram: " + checkAnagram(a, b));
		
		a = "aac";
		b = "abc";
		System.out.println("Anagram: " + checkAnagram(a, b));
		
		System.out.println("replace Space " + replaceSpace("av  db  d c "));
		
		int[][] m = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12},
				{13, 14, 15, 16}
		};
		
		rotate(m, m.length, 0);
		System.out.println(Arrays.deepToString(m));
		
		
		int[][] m1 = {
				{1, 2, 3, 4, 5},
				{6, 7, 8, 9, 10},
				{11, 12, 13, 14, 15},
				{16, 17, 18, 19, 20},
				{21, 22, 23, 24, 25}
		};
		
		rotate1(m1, m1.length, 0);
		System.out.println(Arrays.deepToString(m1));
		
		int[][] m2 = {
				{1, 2, 3, 4, 5},
				{6, 7, 0, 9, 10},
				{11, 12, 13, 14, 15},
				{16, 0, 18, 0, 20},
				{21, 22, 23, 24, 25}
		};
		
		setZero(m2);
		System.out.println(Arrays.deepToString(m2));
		
		System.out.println(isRotation("apple", "pleap"));
		System.out.println(isRotation("apple", "pleep"));
		System.out.println(isRotation("ababa", "babab"));
	}
	
	public static boolean containUniqueChars(String input){
		if(input == null) throw new IllegalArgumentException();
		int checker = 0; //int 32 bit. lower case alphabet only 26 char.
		//we can left shift 1 to represent if an char has appeared
		int val;
		for(int i=0; i< input.length(); i++){
			val = input.charAt(i) - 'a';
			if((checker & (1 << val)) > 0) return false;
			checker |= (1<<val);
		}
		return true;
	}
	/**
	 * reverse a string.
	 * @param input
	 * @return
	 */
	public static String reverseString(String input){
		char[] chars = input.toCharArray();
		int start = 0;
		int end = chars.length -1;
		char temp;
		while(start < end){
			temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start ++;
			end --;
		}
		return String.valueOf(chars);
	}
	
	/**
	 * remove duplicate chars in array
	 * in place without additional buffer
	 * search through current buffer
	 * if it doesn't have the char
	 * append it to the end as the tail.
	 * 
	 * @param chars
	 * @return the new size of chars array.
	 */
	public static int removeDuplicate(char[] chars){
		
		if(chars == null) return 0;
		if(chars.length < 2) return 1;
		
		int tail = 1;
		for(int i=1; i< chars.length; i++){
			int j;
			for( j=0; j< tail; j++){
				if(chars[i] == chars[j]){
					break;
				}
			}
			if(j == tail){//tail buffer doesn't have the char. append it. and advance tail
				chars[tail] = chars[i];
				tail++;
			}
		}
		
		return tail;
	}
	
	/**
	 * remove duplicate with additional data structure
	 * @param chars
	 * @return
	 */
	public static int removeDuplicate1(char[] chars){
		if(chars == null) return 0;
		if(chars.length < 2) return 1;
		
		int tail = 1;
		BitSet set = new BitSet(256);
		set.set(chars[0]); //remember to set the first one has been seen.
		for(int i=1; i< chars.length; i++){
			if(!set.get(chars[i])){
				set.set(chars[i]);
				chars[tail++] = chars[i];
			}
		}
		return tail;
	}
	
	public static boolean checkAnagram(String a, String b){
		if(a == null|| b== null ) return false;
		if(a.length() != b.length()) return false;
		
 		int[] counter = new int[256];
		for(int i=0; i<counter.length; i++){
			counter[i] = 0;
		}
		for(int i=0; i<a.length(); i++ ){
			counter[a.charAt(i)] ++;
		}
		
		for(int i=0; i< b.length(); i++){
			counter[b.charAt(i)] --;
			if(counter[b.charAt(i)] < 0){
				return false;
			}
		}
		
		return true;
	}

	
	public static String replaceSpace(String a){
		if(a == null)
			return null;
		
		int spaceCounter=0;
		for(int i=0; i< a.length(); i++){
			if(a.charAt(i) == ' '){
				spaceCounter++;
			}
		}
		
		int newLength=a.length() + spaceCounter *2;
		char[] chars = new char[newLength];
		
		int index = newLength - 1;
		for(int j= a.length()-1; j >=0; j--){
			if(a.charAt(j) == ' '){
				chars[index] = '0';
				chars[index-1] = '2';
				chars[index-2] = '%';
				index -= 3;
			}else{
				chars[index--] = a.charAt(j);
			}
		}
		return String.valueOf(chars);
	}
	
	/**
	 * m matrix to rotate
	 * k level or layer
	 * layer by layer.
	 * four way swap.
	 * in place rotate.
	 * 
	 * @param m
	 * @param l
	 */
	public static void rotate(int[][] m, int n, int k){
		if(m == null) return;
		if(n==1) return;//no need to rotate.
		
		int s = n -2 *k; //size left.
		if(s <= 1){//inner core is only one element or nothing left
			return;
		}
		//loop through matrix to perform a four-way swap for each of the elements on
		//the outer layer.
		int temp;
		for(int i=0; i< s-1; i++){
			temp = m[k][k + i];
			m[k][k+i] = m[k + s-1-i][k];
			m[k + s-1-i][k] = m[k + s-1][k + s-1-i];
			m[k + s-1][k + s-1-i] = m[k + i][k + s-1];
			m[k + i][k + s-1] = temp;
		}
		rotate(m, n, ++k);
	}
	
	public static void rotate1(int[][] m, int n, int k){
		if(m == null) return;
		if(n==1) return;//no need to rotate.
		
		int s = n -2 *k; //size left.
		if(s <= 1){//inner core is only one element or nothing left
			return;
		}
		//loop through matrix to perform a four-way swap for each of the elements on
		//the outer layer.
		int temp;
		for(int i=k; i< n-1-k; i++){
			temp = m[k][i];
			m[k][i] = m[n-1-k-i][k];
			m[n-1-k-i][k] = m[n-1-k][n-1-k-i];
			m[n-1-k][n-1-k-i] = m[i][n-1-k];
			m[i][n-1-k] = temp;
		}
		rotate(m, n, ++k);
	}
	
	/**
	 * set row and column zero if there is one element 0.
	 * 
	 * @param m
	 */
	public static void setZero(int[][] m){
		if(m == null) return;
		int w = m[0].length;
		int h = m.length;
		int[] rows = new int[w];
		int[] cols = new int[h];
		
		for(int i=0; i< w; i++){
			for(int j=0; j< h; j++){
				if(m[i][j] == 0){
					rows[i] = 1;
					cols[j] = 1;
				}
			}
			
		}
		
		for(int i=0; i<w; i++){
			for(int j=0; j< h; j++){
				if(rows[i] == 1 || cols[j] ==1){
					m[i][j] = 0;
				}
			}
		}
	}
	
	public static boolean isRotation(String s1, String s2){
		if(s1 == null || s2 == null) return false;
		if(s1.length() != s2.length()) return false;
		
		s1 = s1 + s1;
		return s1.contains(s2)? true: false;
	}
}
