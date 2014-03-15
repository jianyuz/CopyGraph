package com.z2.bitone;

/**
 * power of 2 number fot only one bit at the left most.
 * subtract it off will make the number become 0.
 * @author zhouzhou
 *
 */
public class BitOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numOfBits(7));
		System.out.println(powerOf2(0));
		System.out.println(Integer.highestOneBit(7));
		System.out.println(highestOneBit(7));
		int x = 2;
		System.out.println( x | (x+1)); //right most 0 turn off. become 3
		System.out.println(Integer.numberOfLeadingZeros(128));
		System.out.println(Integer.numberOfTrailingZeros(128));
		System.out.println(Integer.bitCount(128));
		System.out.println(Integer.reverse(128));
		System.out.println(Integer.rotateLeft(128, 1));
		System.out.println(Integer.rotateRight(128, 1));
		System.out.println(Integer.reverseBytes(128));
		System.out.println( ~127  & (127 + 1));//rightmost 0 bit.
	}
	
	/**
	 * change i to have all the fields to be 1.
	 * except change the highest bit.
	 * then using unsigned shift
	 * deduct it from i.
	 * 
	 * @param i
	 * @return
	 */
	public static int highestOneBit(int i){
		i |= (i>>1);
		i |= (i>>2);
		i |= (i>>4);
		i |= (i>>8);
		i |= (i>>16);
		return i - (i >>> 1);
	}
	
	public static int numOfBits(int input){
		int count = 0;
		while(input != 0){
			input = input & (input -1); //remove right most bit 1.
			//input = input & (-input); //get the right most bit
			count ++;
		}
		return count;
	}
	
	/**
	 * rightmost 1 will become 0.
	 * if the left bits are all 0. there is only 1 1 bit in the bit pattern
	 * @param input
	 * @return
	 */
	public static boolean powerOf2(int input){
		return input != 0 && ((input & (input-1)) == 0);
	}

}
