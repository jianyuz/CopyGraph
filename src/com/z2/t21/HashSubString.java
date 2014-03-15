package com.z2.t21;

import java.util.HashMap;
import java.util.Map;

/**
 * use multinomial rolling hash.
 * worse case (n * m)
 * every hash is equal
 * best is n + m
 * 
 * can be used for multi pattern matching
 * expected O(n + k) k is the number of pattern
 * otherwise O(n * K)
 * @author zhouzhou
 *
 */
public class HashSubString {

	private static int base = 101;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "abdcdcdeggedcdfg";
		String pattern = "dcd";
		subStringMatch(input, pattern);
		
		String[] patterns = {"dcd", "cdf"};
		subStringMulMatch(input, patterns, 3);

	}

	public static void subStringMulMatch(String input, String[] patterns, int k){
		if(input == null || patterns == null || patterns.length == 0) return;
		Map<Long, String> patternMap = new HashMap<Long, String>(patterns.length);
		for(int i = 0; i< patterns.length; i++){
			long hashP = compHash(patterns[i], 0, patterns[i].length() -1);
			patternMap.put(hashP, patterns[i]);
		}
		
		int n = input.length();
		long hashS = compHash(input, 0, k-1);
		for(int i = 0; i < n - k; i ++ ){
			String pattern;
			if((pattern = patternMap.get(hashS)) != null){
				//compare the string. it is possibl multiple substring matches to one hash.
				if(compareStr(input, i, pattern)){
					System.out.println("matched pattern " + pattern + " starts at " + i + " length " + k);
				}
			}
			hashS = base * (hashS  - (long)(input.charAt(i) * (int)Math.pow(base, k-1))) + (long)input.charAt(i + k);
			System.out.println("hash value " + hashS);
		}
		
		System.out.println("done");
		
	}
	
	public static void subStringMatch(String input, String pattern){
		if(input == null || pattern == null) return;
		int n = input.length();
		int k = pattern.length();
		
		long hashP = compHash(pattern, 0, k-1);
		System.out.println("hash value for pattern " + hashP);
		long hashS = compHash(input, 0, k-1);
		for(int i = 0; i < n - k; i ++ ){
			if(hashS == hashP){
				//compare the string. it is possibl multiple substring matches to one hash.
				if(compareStr(input, i, pattern)){
					System.out.println("matched pattern starts at " + i + " length " + k);
				}
			}
			hashS = base * (hashS  - (long)(input.charAt(i) * (int)Math.pow(base, k-1))) + (long)input.charAt(i + k);
			System.out.println("hash value " + hashS);
		}
		
		System.out.println("done");
	}
	
	/**
	 * start got higher order
	 * end lower
	 * 
	 * @param a
	 * @param s
	 * @param e
	 * @return
	 */
	private static long compHash(String a, int s, int e){
		long sum = 0; 
		int exp = 0;
		for(int i = e; i >=s; i--){
			sum += (long)(a.charAt(i) * (int)Math.pow(base, exp++));
		}
		return sum;
	}
	
	private static boolean compareStr(String input, int s, String pattern){
		boolean res = true;
		for(int i = 0; i< pattern.length(); i++){
			if(input.charAt(s + i) != pattern.charAt(i)){
				res = false;
				break;
			}
		}
		return res;
	}
	
	
}
