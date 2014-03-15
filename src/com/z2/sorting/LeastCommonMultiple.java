package com.z2.sorting;

public class LeastCommonMultiple {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LCM(4,  6));
	}
	
	/**
	 * GCD * LCM = m* n.
	 * now calculate the greated commond divider.
	 * 
	 * Formal description of the Euclidean algorithm
*Input Two positive integers, a and b.
*Output The greatest common divisor, g, of a and b.
*Internal computation
*If a<b, exchange a and b.
*Divide a by b and get the remainder, r. If r=0, report b as the GCD of a and b.
*Replace a by b and replace b by r. Return to the previous step.
	 * @param m
	 * @param n
	 */
	public static int LCM(int m, int n){
		int a = m;
		int b = n;
		if( m < n){
			int tmp = n;
			n = m;
			m = tmp;
		}
		
		
		int r = m%n;
		while(r != 0){
			m = n;
			n = r;
			r = m%n;
		}
		
		return a*b/n;
	}

}
