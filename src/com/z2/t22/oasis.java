package com.z2.t22;

public class oasis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(maxAmountTransported(150, 20, 50, 1));
		//System.out.println(maxAmountTransported(101, 20, 50, 1));
		System.out.println(maxAmountTransported(99, 20, 50, 1));
	}
	
	/**
	 * N amount to transport
	 * D distance
	 * C carry per cat
	 * F consume by car per km
	 * 
	 * find the transit point the amount of remaining nuts should be divisible by C and requires one less round trip to next transit point
	 * remaining nuts divisible by C but less than N. because if not, we need one more round trip to carry it over.
	 * 
	 * recursive solution.
	 * 
	 * @param N
	 * @param D
	 * @param C
	 * @param F
	 */
	public static double maxAmountTransported(double N, double D, double C, double F){
		
		if(N <= C){
			double nutsLeft = N - F*D;
			return nutsLeft >= 0? nutsLeft: 0;
		}
		
		int trips =  (int)(2 * (Math.ceil(N/C) -1) +1 );
		double costPerKm = trips * F;
		double remaining = C * (Math.ceil(N/C) -1);
		
		double travelD = (N - remaining)/costPerKm;
		if(travelD >= D){
			return N - D*costPerKm;
		}
		
		return maxAmountTransported(remaining, D-travelD, C, F);
	}

}
