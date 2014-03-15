package com.z2.primeNumber;

/**
 * find all prime number up to integer n
 * @author zhouzhou
 *
 */
public class PrimeNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(isPrime(1));
		System.out.println(isPrime(2));
		System.out.println(isPrime(3));
		System.out.println(isPrime(4));
		System.out.println(isPrime(25));
		System.out.println(isPrime(30));
		System.out.println(isPrime(7));
		
		findPrime(100);
	}
	
	/**
	 * prime number can only divide by 1 and self.
	 * no other number.
	 * @param n
	 */
	public static void findPrime (int n){
		boolean [] isPrime = new boolean[n];
		isPrime[0] = false;
		isPrime[1] = true;
		
		for(int i = 2; i< n; i++){
			isPrime[i]= true;
		}
		int limit = (int)Math.sqrt(n);
		for(int i = 2; i<= limit; i++){
			if(isPrime[i])
			{
				for(int j= i * i ; j < n; j+= i){
					isPrime[j] = false;
				}
			}
		}
		
		for(int i=0; i< n; i++){
			if(isPrime[i])
			System.out.println(i);
		}
	}
	
	public static boolean isPrime(int n){
		boolean res = true;
		for(int i = 2; i<= Math.sqrt(n); i++){
			if((n%i) == 0){
				res = false;
			}
		}
		return res;
	}

}
