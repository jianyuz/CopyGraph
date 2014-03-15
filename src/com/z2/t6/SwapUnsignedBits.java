package com.z2.t6;

public class SwapUnsignedBits {
	public static void main(String[] args){
		long testUnsigned = unsigned(22122456);
		System.out.println(String.format("%32s", Long.toBinaryString(testUnsigned)).replaceAll(" ", "0"));
		long swap1 = swapBits(testUnsigned);
		System.out.println(String.format("%32s", Long.toBinaryString(swap1)).replaceAll(" ", "0"));
		long swap2 = reverseMask(testUnsigned);
		System.out.println(String.format("%32s", Long.toBinaryString(swap2)).replaceAll(" ", "0"));
	}
	
	public static long reverseMask(long unsignedInt){
		unsignedInt = (unsignedInt & 0x55555555L) << 1 | (unsignedInt & 0xAAAAAAAAL) >>1 ;
		unsignedInt = (unsignedInt & 0x33333333L) << 2 | (unsignedInt & 0xCCCCCCCCL) >>2;
		unsignedInt = (unsignedInt & 0x0F0F0F0FL) << 4 | (unsignedInt & 0xF0F0F0F0L) >> 4;
		unsignedInt = (unsignedInt & 0x00FF00FFL) << 8 | (unsignedInt & 0xFF00FF00L) >> 8;
		unsignedInt = (unsignedInt & 0x0000FFFFL) << 16 | (unsignedInt & 0xFFFF0000L) >> 16;
		return unsignedInt;
	}
	
	public static long swapBits(long unsignedInt){
		int size = 32;
		for(int i = 0; i< size/2; i++){
			unsignedInt = swapBits(unsignedInt, i, size -1 -i);
		}
		return unsignedInt;
	}
	
	public static long swapBits(long unsignedInt, int i, int j){
		long bitA = (unsignedInt >> i) & 1; //right shift move significant bit lower
		long bitB = (unsignedInt >> j) & 1;
		if((bitA ^ bitB) == 1){
			//swap
			unsignedInt ^= ((1L << i ) | (1L << j)); //left shift move less significant bit higher.
		}
		return unsignedInt;
	}
	
	private static long unsigned(int input){
		long val = input;
		if (val < 0){
			val += 1L << 32;
		}
		return val;
	}

}
