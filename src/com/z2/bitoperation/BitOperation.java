package com.z2.bitoperation;

import java.util.ArrayList;
import java.util.Arrays;

public class BitOperation {

	public static int setMBits(int n, int m, int i, int j){
		int v = (1 << (j-i +1)) -1;
		System.out.println(Integer.toBinaryString(v));
		v = v << i; //left shift by i;
		int mask = ~v; //reverse
		System.out.println(Integer.toBinaryString(mask));
		return (n & mask) | (m<<i);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int n = Integer.parseInt("10000110100", 2);
		int m = Integer.parseInt("10101", 2);
		int res = setMBits(n, m, 2, 6);
		System.out.println(Integer.toBinaryString(res));
		
		System.out.println(Float.toHexString(3.72f));
		System.out.println(printBinary("3.75"));
		
		System.out.println(Integer.toBinaryString(
				nextNumberSameBitPattern(Integer.parseInt("11100", 2))));
		System.out.println(bitSwapRequired(5, 3));
		System.out.println(Integer.toBinaryString(swapOddEvenBits(5)));
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(0, 1, 2, 3, 4, 6, 7));
		System.out.println(findMissing(list));
	}
	
	/**
	 * for decimals times 2 and minus 1.
	 * 
	 * @param n
	 * @return
	 */
	public static String printBinary (String n){
		int intPart = Integer.parseInt(n.substring(0, n.indexOf(".")));
		double decPart = Double.parseDouble(n.substring(n.indexOf(".")));
		String iPart = "";
		int bit;
		while(intPart > 0){
			bit = intPart %2; //take mod result as the last digit.
			intPart >>= 1;
			iPart = bit + iPart;
		}
		
		String dPart = "";
		double val;
		while (decPart > 0) {//any left over value?
			if(dPart.length() > 32) return "Error";//too long to represent
			val = decPart * 2;
			if(val >= 1) {
				decPart = val - 1;
				dPart += 1;
			} else {
				decPart = val;
				dPart += 0;
			}
		}
		return iPart + "." + dPart;
	}
	
	public static boolean bitSet(int n, int index){
		return (n & (1 << index)) > 0;
	}
	
	public static int setBit(int n, int index, boolean b){
		if(b){
			return n | ( 1<<index);
		}else{
			int mask = ~(1<<index);
			return n& mask;
		}
	}
	
	public static int nextNumberSameBitPattern(int m){
		if(m <=0) return -1;
		int index = 0;
		
		//find the lowest bit one
		while(!bitSet(m, index)){
			index ++;
		}
		
		int countOnes = 0;
		while(bitSet(m, index)){
			index ++;
			countOnes ++;
		}
		
		m = setBit(m, index, true); //turn on the bit.
		index --;
		//turn off the previous bit.
		m = setBit(m, index, false);
		countOnes --;
		
		//now set all higher bits as 0 and lower bits as one.
		for(int i=index -1; i>=countOnes; i--){
			m = setBit(m, i, false);
		}
		
		for(int i=countOnes-1; i>=0; i--){
			m = setBit(m, i, true);
		}
		
		return m;
	}

	
	public static int bitSwapRequired(int a, int b){
		int res = 0;
		for(int i= a ^ b; i> 0; i= i>>1){
			res += i & 1;
		}
		return res;
	}
	
	public static int swapOddEvenBits(int a){
		return (a & 0xaaaaaaaa) >> 1 | (a&0x55555555) << 1;
	}
	
	public static int findMissing(ArrayList<Integer> array){
		return findMissing(array, 0, 3);
	}
	
	public static int getBit(int n, int column){
		return n & (1 << column);
	}
	/**
	 * each time remove half O(n) complexity.
	 * @param array
	 * @param column
	 * @param limit
	 * @return
	 */
	public static int findMissing(ArrayList<Integer> array, int column, int limit){
		if(column > limit){
			return 0;
		}
		ArrayList<Integer> odds = new ArrayList<Integer>();
		ArrayList<Integer> even = new ArrayList<Integer>();
		
		for(Integer t: array){
			if(getBit(t, column) == 0){
				even.add(t);
			}else{
				odds.add(t);
			}
		}
		
		if(odds.size()>= even.size()){//missing 0;
			return findMissing(even, column+1, limit) <<1 | 0;
		}else{
			return findMissing(odds, column+1, limit) <<1 | 1;
		}
	}
}
