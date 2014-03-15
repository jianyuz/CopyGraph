package com.z2.t14;

/**
 * continuous work blocks, k workers
 * divide the work blocks among the k workers so noone get overloaded.
 * maximum sun is minimized.
 * 
 * recursive thinking first.
 * k-1 partiion available, add one more partition.
 * 
 * then optimize it with DP
 * exchange storage space with time complexity.
 * 
 * input integer array that defines the range of work pieces.
 * number k the numbers of workers --> divisions of the work blocks.
 * 
 * 
 * @author zhouzhou
 *
 */
public class FairWorkLoad {

	private static int [] cumSum;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] n = new int[] { 100, 200, 300, 400, 500, 600, 700, 800,  900};
		int k = 3;
		
		cumSum = cumSum(n);
		int minMax = maxFairWorkLoad(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadDP(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadB(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		System.out.println("------");
		
		n = new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		k = 3;
		cumSum = cumSum(n);
		minMax = maxFairWorkLoad(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadDP(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadB(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		System.out.println("------");
		
		n = new int[]{ 568, 712, 412, 231, 241, 393, 865, 287, 128, 457, 238, 98, 980, 23, 782 };
		k = 4;
		cumSum = cumSum(n);
		minMax = maxFairWorkLoad(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadDP(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadB(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		System.out.println("------");

		n = new int[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1000 };
		k = 2;
		cumSum = cumSum(n);
		minMax = maxFairWorkLoad(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadDP(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		minMax = maxFairWorkLoadB(n, n.length, k);
		System.out.println("min of max work load is : " + minMax);
		System.out.println("------");
		
		
	}
	
	
	public static int[] cumSum(int[] n){
		if(n == null || n.length == 0) return null;
		int[] cumSum = new int[n.length];
		cumSum[0] = n[0];
		for(int i=1; i < n.length; i++){
			cumSum[i] = cumSum[i-1] + n[i];
		}
		return cumSum;
	}
	
	/**
	 * 
	 * @param n
	 * @param len length of the work piece array.
	 * @param k
	 * @return
	 */
	public static int maxFairWorkLoad(int[] n, int len,  int k){
		if(len ==1) {
			return n[0];
		}
		if(k == 1){
			return cumSum[len -1];
		}
		
		int minMax = Integer.MAX_VALUE;
		for(int j=1; j< len; j++){
			minMax = Math.min(minMax, Math.max(maxFairWorkLoad(n, j, k-1), cumSum[len-1] - cumSum[j-1] ));  
		}
	
		return minMax;
	}
	
	/**
	 * max[n, k] n * k table.
	 * @param n
	 * @param len
	 * @param k
	 * @return
	 */
	public static int maxFairWorkLoadDP(int[] n, int len, int k){
		int [][] res= new int[len+1][k+1];
		//base cases.
		for(int i=0; i<= k; i++){//len = 1
			res[1][i] = n[0];
		}
		
		for(int i=1; i<=len; i++){ //k=1;
			res[i][1] = cumSum[i-1];
		}
		
		int minMax;
		for(int i=2; i<= k; i++){
			for(int j=2; j<=len; j++){
				minMax = Integer.MAX_VALUE;
				for(int p=1; p<j; p++){
					minMax = Math.min(minMax, Math.max(res[p][i-1], cumSum[j-1]-cumSum[p-1]));
				}
				res[j][i] = minMax;
			}
		}
		
		return res[len][k];
	}
	
	
	public static int maxInArray(int[] n){
		int maxRes = Integer.MIN_VALUE;
		for(int i = 0; i< n.length; i++){
			if(n[i] > maxRes){
				maxRes = n[i];
			}
		}
		return maxRes;
	}
	
	public static int requiredWorkers(int[] n, int limit){
		int workers = 1;
		int cum = 0;
		for(int i = 0; i< n.length; i++){
			cum = cum + n[i];
			if(cum > limit){
				workers ++;
				cum = n[i];
			}
		}
		return workers;
	}
	
	/**
	 * binary search to find the lowest number to satisfy the condition rquired works <= k.
	 * 
	 * @param n
	 * @param len
	 * @param k
	 * @return
	 */
	public static int maxFairWorkLoadB(int[] n, int len, int k){
		int lowRange = maxInArray(n);
		int highRange = cumSum[len-1];
		
		int low = lowRange;
		int high = highRange;
		int mid;
		while(low < high){
			mid = low + (high - low)/2;
			if(requiredWorkers(n, mid) <= k){
				high = mid;
			}else{
				low = mid + 1;
			}
		}
		return low;
	}
	
}
