package com.z2.t10;

import java.util.LinkedHashMap;
import java.util.Map;

public class LongSubStringNoRepeat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "abdsadgsgcvfdga";
		String out = lsubStringNR(input);
		System.out.println("longest substring no repeat:" + out);
		input = "abcabc";
		out = lsubStringNR(input);
		System.out.println("longest substring no repeat:" + out);
		input = "abefaafy";
		out = lsubStringNR(input);
		System.out.println("longest substring no repeat:" + out);
		input = "aaa";
		out = lsubStringNR(input);
		System.out.println("longest substring no repeat:" + out);
		input = "a";
		out = lsubStringNR(input);
		System.out.println("longest substring no repeat:" + out);
		input = "";
		out = lsubStringNR(input);
		System.out.println("longest substring no repeat:" + out);
	}
	
	/**
	 * keep track of start and end index of the longest substring.
	 * use a linked hashmap to keep track of if the char has been visited.
	 * the value in the hashmap is the position of previously visited char.
	 * @param input
	 * @return
	 */
	public static String lsubStringNR(String input){
		if(input == null) return null;
		Map<Character, Integer> cMap = new LinkedHashMap<Character, Integer>(input.length());
		int i=0, j=0;
		int maxLen = 0;
		int resi = 0;
		int resj = 0;
		int pos = -1;
		while(j < input.length()){
			if(cMap.containsKey(input.charAt(j))){
				//key is in the map.
				if(j-i > maxLen){
					resi = i;
					resj = j;
					maxLen = resj-resi;
				}
				pos = cMap.get(input.charAt(j));
				i = pos + 1;
				
			}
			cMap.put(input.charAt(j), j);
			j++;
			
		}
		//at the end of the loop.
		if((input.length() -i)>maxLen){
			resi = i;
			resj = input.length();
			maxLen = resj- resi;
		}
		
		return input.substring(resi,resj);
	}

}
