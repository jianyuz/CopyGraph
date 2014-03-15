package com.z2.t21;

/**
 * find a substring in the stream or input string.
 * pattern repetition.
 * 
 * @author zhouzhou
 *
 */
public class Substring {

	private static int base = 101;
	
	/**
	 * search string straight forward
	 * n-m+1 * m
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String input= "sdfreggrerrr";
		String pattern = "ggrer";
		int pos = RabinKarp(input, pattern);
		
		System.out.println("found the substring: " + pos);
		
		pos = KMP(input, pattern);
		
		System.out.println("found the substring: " + pos);
		
		pos = boyerMoore(input, pattern);
		System.out.println("found the substring: " + pos);

		pos = bruteforce(input, pattern);
		System.out.println("found the substring: " + pos);
		
	}
	
	public static int bruteforce(String input, String pattern){
		char[] iChars = input.toCharArray();
		char[] pChars = pattern.toCharArray();
		
		int start;
		int pstart;
		for(int i = 0; i< iChars.length; i++){
			start =i; 
			pstart = 0;
			while(pstart < pattern.length() && iChars[start] == pChars[pstart]){
				start ++;
				pstart ++;
			}
			if(pstart == pattern.length()){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * convert the pattern string into a hash value
	 * we may assign some string the same hash value.
	 * reduce the number of comparison.
	 * worst n*m
	 * best n+ m
	 * 
	 * @param input
	 * @param pattern
	 */
	public static int  RabinKarp(String input, String pattern){
		if(input == null || pattern == null) return -1;
		int n = input.length();
		int m = pattern.length();
		long hS = hashValue(input, 0, m-1);
		long hPattern = hashValue(pattern, 0, m-1);
		
		for(int i = 0; i< n-m+1; i++){
			if(hS == hPattern){
				//compare string
				if(compareStr(input, i, i+m-1, pattern)){
					return i;
				}
			}
			if(i+m < n)
			//rolling hash computation
			hS = (hS - ((int)input.charAt(i)) * (long)Math.pow(base,  m-1)) * base + (int)input.charAt(i + m); 
			System.out.println("hash " + hS);
		}
		return -1;
	}
	
	public static boolean compareStr(String in1, int start, int end, String in2){
		boolean matched = true;
		for(int i=start, j=0; i< end; i++, j++){
			if(in1.charAt(i) != in2.charAt(j)){
				return matched = false;
			}
		}
		return matched;
	}

	public static long hashValue(String input, int start, int end){
		
		long hashValue = 0;
		int exp = 0;
		for(int i= end; i >= start; i--){
			hashValue += ((int)input.charAt(i)) * Math.pow(base, exp);
			exp ++;
		}
		return hashValue;
	}
	
	/**
	 * build a table store the border of the string , which is the longest substring that is the prefix and suffix
	 * of the string
	 * eg x = 
	 * abacab 
	 * ab is the border of x
	 * also $ empty string. 
	 * but ab is the longest substring.
	 * that's both a proper prefix and suffix string of x.
	 * 
	 * determine the shift position from the border length
	 * length of the string by far - border length = shift length;
	 * resume the match at last position.
	 * 
	 * if a match fails, we fail back to the border of the pattern substring so far.
	 * avoid restarting the char matching again.
	 * @param target
	 * @param pattern
	 */
	public static int KMP(String target, String pattern){
		int[] border = buildBorderTable(pattern);
		for(int item: border){
			System.out.print(item);
		}
		System.out.println();
		
		int len = target.length();
		int m=0;//match position in s
		int i=0;//position of char in pattern.
		while(m + i < len){
			if(target.charAt(m+i) == pattern.charAt(i)){
				if(i == pattern.length()-1){
					return m;
				}
				i++;
			}else{//mismatch back tracking
				m = m + i - border[i];
				if(border[i] > -1){
					i = border[i];
				}else
					i = 0;
			}
		}
		return -1;
		
	}
	
	/**
	 * border table computed against pattern only.
	 * for each position in the pattern, 
	 * border value is the length of border string to the left of the position.
	 * 
	 * border width start with -1.
	 * if p[i] == p[j], j's width can extends.
	 * 
	 * it can extends
	 * the border table is of length of the pattern.
	 * 
	 * @param pattern
	 * @return
	 */
	public static int[] buildBorderTable(String pattern){
		int n = pattern.length();
		int[] border = new int[n];
		
		int borderEnd =0;
		int pos = 2;
		
		border[0] = -1;
		border[1] = 0;
		
		while(pos < n){
			if(pattern.charAt(pos-1) == pattern.charAt(borderEnd)){
				borderEnd ++;
				border[pos] = borderEnd;
				pos++;
			}else if(borderEnd > 0){
				borderEnd = border[borderEnd];
			}else{
				border[pos] = 0;
				pos++;
				
			}
		}
		return border;
	}
	
	/**
	 * preprocess pattern similiar to KMP.
	 * bad char skip rule.
	 * from alignment skip backward.
	 * 
	 * @param input
	 * @param pattern
	 */
	public static int boyerMoore(String input, String pattern){
		int[][] skipTable = skipTable(pattern);
		/*
		for(int i=0; i< 256; i++){
			System.out.print(((char)i) + ":");
			for(int j=0 ; j< pattern.length(); j++){
				System.out.print(skipTable[i][j] + " ");
			}
			System.out.println();
		}*/
		
		int m = input.length();
		int n = pattern.length();
		int inputAlign = n -1;
		boolean allMatch;
		char inputChar;
		while(inputAlign < m){
			allMatch = true;
			for(int i = n-1; i>=0; i--){
				inputChar =  input.charAt(inputAlign - (n-1-i));
				if(pattern.charAt(i) != inputChar){
					inputAlign = inputAlign + skipTable[(int)inputChar][i];
					allMatch = false;
					break;
				}
			}
			if(allMatch)
				return inputAlign - n + 1;
		}
		
		return -1;
	}
	
	//2D matirx c and i as index
	//return highest index j j<i char[j] = char[i]
	public static int[][] skipTable(String pattern){
		int[][] table = new int[256][pattern.length()];
		int len = pattern.length();
		
		for(int i=0; i< 256; i++){
			for(int j=0 ; j< len; j++){
				table[i][j] = len;//by default skip full string length.
			}
		}
		
		int charIndex;
		for(int i=0; i< pattern.length(); i++){//other than that, skip j-i
			charIndex = (int)pattern.charAt(i);
			for(int j=i; j< pattern.length(); j++){
				table[charIndex][j] = j - i ;
			}
		}
		return table;
	}
}
