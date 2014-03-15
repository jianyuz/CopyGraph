package com.z2.beautyString;

import java.util.Arrays;

/*
 * When John was a little kid he didn't have much to do. There was no internet, no Facebook, and no programs. So he did the only thing he could... he evaluated the beauty of strings in a quest to discover the most beautiful string in the world.
 
Given a string s, little Johnny defined the beauty of the string as the sum of the beauty of the letters in it.
 
The beauty of each letter is an integer between 1 and 26, inclusive, and no two letters have the same beauty. Johnny doesn't care about whether letters are uppercase or lowercase, so that doesn't affect the beauty of a letter. (Uppercase 'F' is exactly as beautiful as lowercase 'f', for example.)
 
You're a student writing a report on the youth of this famous hacker. You found the string that Johnny considered most beautiful. What is the maximum possible beauty of this string?
 
Input
The input file consists of a single integer m followed by m lines.
 
Output
Your output should consist of, for each test case, a line containing the string "Case #x: y" where x is the case number (with 1 being the first case in the input file, 2 being the second, etc...) and y is the maximum beauty for that test case.
 
Constraints
 5 ≤ m ≤ 50
 2 ≤ length of s ≤ 500
 
 assign largest weight to most frequent chars.
 */
public class BeautyString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String input= "ABbCcc";
		System.out.println(beautyOfString(input));
		
		input= "Good luck in the Facebook Hacker Cup this year!";
		System.out.println(beautyOfString(input));
		input= "Ignore punctuation, please :)";
		System.out.println(beautyOfString(input));
		input= "Sometimes test cases are hard to make up.";
		System.out.println(beautyOfString(input));
		input= "So I just go consult Professor Dalves";
		System.out.println(beautyOfString(input));
		
	}
	
	public static int beautyOfString(String input){
		if(input == null || input.length() == 0){
			return -1;
		}
		char[] iChars = input.toCharArray();
		int[] counter = new int[26];
		//init counter array.
		for(int i = 0; i< counter.length; i++){
			counter[i] = 0;
		}
		
		for(char c : iChars){
			char tmpC = Character.toLowerCase(c);
			int index = tmpC - 'a';
			if(index >=0 && index < 26){
				counter[tmpC - 'a']++;
			}
		}
		
		Arrays.sort(counter);
		int weight = 26;
		int sumOfBeauty = 0;
		for(int i = counter.length-1; i>=0; i--){
			if(counter[i] == 0){
				break;
			}else{
				sumOfBeauty += counter[i] * weight--;
			}
		}
		
		return sumOfBeauty;
	}

}
