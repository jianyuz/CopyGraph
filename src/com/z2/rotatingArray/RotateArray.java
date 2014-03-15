package com.z2.rotatingArray;

import java.util.Arrays;

public class RotateArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String [] input = new String[] {
				"a", "b", "c", "d", "e", "f", "g"
		};
		rotateArray(input, 3);
		System.out.println(Arrays.toString(input));
		
		String[] input1 = new String[] {
				"a", "b"
		};
		rotateArray(input1, 0);
		System.out.println(Arrays.toString(input1));
		
		String[] input2 = new String[] {
				"a"
		};
		rotateArray(input2, 0);
		System.out.println(Arrays.toString(input2));
	}
		
		/**
		 * inplace array rotation in place.
		 */
		public static void rotateArray(String[] input, int position){
			if(position > input.length){
				return;
			}
			reverseArray(input, 0, position);
			reverseArray(input, position + 1, input.length -1);
			reverseArray(input, 0, input.length -1);
			
		}
		
		private static void reverseArray(String[] input, int start, int end){
			int s = start;
			int e = end;
			if(s == e) return;
			while(s <e){
				String tmp = input[s];
				input[s] = input[e];
				input[e] = tmp;
				s++;
				e--;
			}
		}
	

}
