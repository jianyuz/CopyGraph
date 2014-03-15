package com.z2.t23;

/**
 * letter key, ctrl-a, ctr-c and ctrl v
 * given n operations, generate the longest text
 * the operation is the number of letter type followed by couple of ctrl -v
 * number of character is the multiplication of letters  and copy operations
 * mAnDlD....
 * for N <=7
 * maximum numbter letters generated is N
 * other than that, we need to maximize the multiplicatoin 
 * product.
 * max(a1*a2*a3 ... ak)
 * a1 + ..... + ak = n - 2(k-1)  since each copy action got 2 ctrl a and ctrl c
 * any ai aj different shouldn't be bigger than 1.* 2
 * 
 * @author zhouzhou
 *
 */
public class CtrlCopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(maxLetters(54));
		System.out.println(maxLetters(66));
		System.out.println(maxLettersDP(54));
		System.out.println(maxLettersDP(66));
	}
	
	/**
	 * F(n) = f(n-k) * (k-2)
	 * suppose last ctrl a is pressed after n-k.
	 * 
	 * F(n) = max(f(n-4) * 2, f(n-5) * 2 .... f(n-9) * 2)
	 * f(n-4) 2 = f(n-4 - (k-4)) * (k=-4 -2) * 2 = f(n-k) * 2 * (k-6) 
	 * compare it w9th f(n-k) * (k -2) for K >=10 f(n-4)*2 is always bigger
	 * no need to consider k >=10.
	 * 
	 * o(n) space o(n)
	 * @param n
	 * @return
	 */
	public static long maxLettersDP(int n){
		long [] res = new long[n+2];
		
		for(int i=1; i<= 7; i++){
			res[i+1] = i;
		}
		
		long max = -1;
		long product;
		for(int i=8; i<=n; i++){
			max = -1;
			for(int j= 4; j<=9; j++){
				product = res[i + 1- j] * (j-2);
				if(product > max){
					max = product;
				}
			}
			res[i+1] = max;
		}
		return res[n+1];
	}
	/**
	 * O(n) space O(1).
	 * @param n
	 * @return
	 */
	public static long maxLetters(int n){
		if (n <=7) return n;
		int k = 2;
		
		//maxK
		//n-2(k-1) = ax + (a+1) (k-x) = ak + k -x >= ak
		// n - 2k + 2 > ak 
		//(a + 2)k < n + 2
		//k < = (n+2)/(a+2) = (n+2)/4;
		int maxK = (n+2)/4;
		
		int sum = 0;
		long product = 1;
		
		long maxProduct = 1;
		
		int average;
		int module;
		while(k <= maxK){
			sum = n - 2 * (k-1);
			average = sum/k;
			module = sum%k; //add 1 to each of item other than average.
			product = (long)Math.pow(average, k-module) * (long)Math.pow(average+1, module);
			
			if(product > maxProduct){
				maxProduct = product;
			}
			k ++;
		}
		
		return maxProduct;
	}

}
