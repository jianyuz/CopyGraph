package com.z2.StringReorder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * lower case charaters string. reorder same chars at least distance d.
 * acect
 * 
 * caect
 * distance 5?
 * c
 * 
 * greedy method find next max c.
 * keep track of dist map
 * make sure dist drop to 0 for the char chosen
 * and it got max freq left.
 * 
 * @author zhouzhou
 *
 */
public class StringReorderDistance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "abacd";
		System.out.println(reorderString(input, 4));
		String input1 = "bbaaaccdd";
		System.out.println(reorderString(input1, 3));
		String input2 = "bbaaaccdd";
		System.out.println(reorderString(input2, 1));
	}
	
	
	public static Map<Character, Integer> summarizeChars(String input){
		char[] content = input.toCharArray();
		Map<Character, Integer> ccMap = new HashMap<Character, Integer>();
		for(char c: content){
			if(ccMap.get(c) == null){
				ccMap.put(c, 1);
			}else{
				int count = ccMap.get(c);
				ccMap.put(c, ++count);
			}
		}
		return ccMap;
	}
	
	public static char findMax(Map<Character, Integer> ccMap, Map<Character, Integer> distMap){
		Set<Character> keys = ccMap.keySet();
		int max = Integer.MIN_VALUE;
		char maxC = 0;
		for(Character c: keys){
			Integer d = distMap.get(c);
			if(ccMap.get(c) > max && (d == null || d<=0)){
				max = ccMap.get(c);
				maxC = c;
			}
		}
		return maxC;
	}
	public static String reorderString(String input, int d){
		Map<Character, Integer> ccMap = summarizeChars(input);
		Map<Character, Integer> distMap= new HashMap<Character, Integer>(); //dist array for all the chars.
		
		char maxC = 0;
		char[] output = new char[input.length()];
		int freq = 0;
		for(int i = 0; i< input.length(); i++){
			maxC = findMax(ccMap, distMap);
			if (maxC == 0) throw new IllegalArgumentException("wrong input");
			output[i] = maxC;
			freq = ccMap.get(maxC);
			ccMap.put(maxC, --freq);
			distMap.put(maxC, d);
			//drop dist measure for all the chars.
			Set<Entry<Character, Integer>> entries = distMap.entrySet();
			for(Entry<Character, Integer> entry: entries){
				int value = entry.getValue().intValue();
				 entry.setValue(--value); 
			}
		}
		
		return new String(output);
	}
	

}
