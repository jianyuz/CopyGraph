package com.z2.t4;

public class RegularExpression {

	public static void main(String[] args){
		boolean match = isMatch("aa", "a");
		System.out.println("is Match: " + match);
		match = isMatch("aa", "aa");
		System.out.println("is Match: " + match);
		match = isMatch("aaa", "aa");
		System.out.println("is Match: " + match);
		match = isMatch("aa", "a*");
		System.out.println("is Match: " + match);
		match = isMatch("aa", ".*");
		System.out.println("is Match: " + match);
		match = isMatch("ab", ".*");
		System.out.println("is Match: " + match);
		match = isMatch("aab", "c*a*b");
		System.out.println("is Match: " + match);
		match = isMatch("abbc", "ab*bbc");
		System.out.println("is Match: " + match);
		match = isMatch("abcbcd", "a.*b.*d");
		System.out.println("is Match: " + match);
	}
	
	public static boolean isMatch(String input, String pattern){
		return isMatch(input, pattern, 0, 0);
	}
	/**
	 * match all the alternatives using recursion
	 * @param input
	 * @param pattern
	 * @param i
	 * @param p
	 * @return
	 */
	private static boolean isMatch(String input, String pattern, int i, int p){
		if(input == null || pattern == null) return false;
		if(input.length() ==0 && pattern.length() == 0) return true;
		if(i>=input.length() && p >= pattern.length()) return true;
		if(i>=input.length() || p >= pattern.length()) return false;
		
		
		if(p+1 >= pattern.length() || (p+1 < pattern.length() && pattern.charAt(p+1) != '*')){
			return (input.charAt(i) == pattern.charAt(p) 
					|| (pattern.charAt(p) == '.' && Character.isLetterOrDigit(input.charAt(i)) )) 
					&& isMatch(input, pattern, i+1, p+1); 
		}
		
		while(i < input.length() && (input.charAt(i)== pattern.charAt(p) 
				|| (pattern.charAt(p) == '.' && Character.isLetterOrDigit(input.charAt(i))))){
			//skip match
			if(isMatch(input, pattern, i, p+2)) return true;
			i++;
		}
		//skip char* pattern
		return isMatch(input, pattern, i, p+2);
	}
	
	public static boolean isMatch1(String input, String pattern){
		
		if(input == null || pattern == null || pattern.length() == 0) return false;
		
		int len = input.length();
		int pLen = pattern.length();
		char curChar, curPChar;
		boolean res = false;
		
		int i=0, j=0;
		Character previous = null;
		while( i< len && j < pLen){
			curChar = input.charAt(i);
			curPChar = pattern.charAt(j);
			if(curChar == curPChar && (curPChar != '.' && curPChar != '*')){
				i++;
				j++;
			} else if (curPChar == '.'){
				i++;
				j++;
			} else if (curPChar == '*'){
				if(j-1 >= 0 && i- 1 >=0 && pattern.charAt(j-1) != '*' ){
					previous = input.charAt(i-1);
				}
				if(previous != null && previous != curChar && previous != '.' ){
					j++;
					previous = null;
				}else{
					i++;
				}
				
			} else{
				break;
			}
			
		}
		if(i>=len && j>=pLen-1){
			res = true;
		}
		
		return res; 
		
	}
}
