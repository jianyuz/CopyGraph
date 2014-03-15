package com.z2.recursion;

import java.util.List;

/**
 * number of ways to repsent n cents.
 * number of wayt to repsent n cents using 0 demom
 * 1 denom 2 denom until runs out of space.
 * @author zhouzhou
 *
 */
public class PresenNCents {
	
	public static void main(String[] args){
		System.out.println(makeChange(10, 5));
		System.out.println(makeChange(100, 25));
	}
	
	public static int makeChange(int n, int denom){
		int nextDenom = 0;
		switch(denom){
		case 25:
			nextDenom = 10;
			break;
		case 10:
			nextDenom = 5;
			break;
		case 5:
			nextDenom = 1;
			break;
		case 1:
			return 1; // all one cent, only one way to represent it.
		}
	
		int ways = 0;
		for(int i = 0; (n - denom * i) >= 0; i++){
			ways += makeChange(n - denom *i, nextDenom);
		}
		return ways;
	}

}
