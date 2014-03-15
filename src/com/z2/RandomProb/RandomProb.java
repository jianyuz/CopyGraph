package com.z2.RandomProb;

import java.util.Random;

/**
 * generate random number with any probability with a fair coin
 * @author zhouzhou
 *
 */
public class RandomProb {

	private static  Random r = new Random();

	static boolean prob(){
		return r.nextInt(2) == 0;
	}
	
	/**
	 * find 0.4 true
	 * find 0.6 false;
	 * if flip is 0.5, we found it.
	 * if flip is over 0.5 , (0.6 - 0.5) * 2, expected.
	 * @param p
	 * @param expected
	 * @return
	 */
	public static boolean prob2(double p, boolean expected){
		
		if(p < 0.5){
			return prob2 (1-p, !expected);
		}else{
			if(prob() == expected){
				return expected;
			}else{
				double diff = p - 0.5;
				return prob2(diff * 2, expected );
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i =0; i< 10; i++){
			System.out.println(prob2(0.4, true));
		}
	}

}
