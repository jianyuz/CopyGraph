package com.z2.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * valid combination of n pair of parentheses.
 * @author zhouzhou
 *
 */
public class ParentheseCombo {
	public static void main(String[] args){
		Set<String> res = combineParent(3);
		System.out.println(Arrays.deepToString(res.toArray()));
		printPar(3);
	}
	
	public static Set<String> combineParent(int pairs){
		if(pairs < 1) {
			return null;
		}
		if(pairs == 1){
			Set<String> res = new HashSet<String>();
			res.add("( )");
			return res;
		}
		
		Set<String> resList = combineParent(pairs -1);
		Set<String> finalRes = new HashSet<String>();
		for(String item: resList){
			finalRes.add("( ) " + item);
			if(item.length() > 2){
				finalRes.add(item + " ( )");
			}
		}
		
		for(String item: resList){
			finalRes.add("( " + item + " )");
		}
		
		
		
		return finalRes;
		
	}
	
	/**
	 * 
	 * @param l number of left paren unassigned
	 * @param r number of right paren unassigned.
	 * @param str str with parens.
	 * @param index index of str char to print.
	 */
	public static void printPar(int l, int r, char[] str, int index){
		if(l <0 || l > r){
			return;
		}
		if( l==0 && r ==0){
			System.out.println(str);
			return;
		}
		
		if(l > 0){
			str[index] = '(';
			printPar( l-1, r, str, index +1);
		}
		
		if(r > l){
			str[index] = ')';
			printPar(l, r-1, str, index+1);
		}
	}
	
	public static void printPar(int count){
		if(count <=1) return;
		char[] str = new char[count * 2]; //holds the paren;
		printPar(count, count, str, 0);
	}
}
