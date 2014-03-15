package com.z2.t16;

/**
 * strategy to maximize the sum of coins we pick.
 * take coins from either end alternatively with your opponent
 * 
 * non-fail (prevent from losing) strategy force opponent to pick only even or odd number of coin.
 * visually scan if even sum or order sum is bigger.
 * then pick the even or odd number coin consistently.
 * 
 * @author zhouzhou
 *
 */
public class CoinLine {

	private static int[] cumSum;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] coins = {2, 4, 5, 7, 8, 4};
		//int[] coins = { 3, 2, 2, 3, 1, 2 };
		cumSum(coins);
		int maxPick = maxCoinPick(coins);
		System.out.println("maximum coin pick is:" + maxPick);
		maxPick = maxMoney(coins);
		System.out.println("maximum coin pick is:" + maxPick);
	}
	
	public static void cumSum(int[] coins){
		cumSum = new int[coins.length];
		cumSum[0] = coins[0];
		for(int i=1; i<coins.length; i++){
			cumSum[i]= cumSum[i-1] + coins[i];
		}
	}
	
	public static int sum(int[] coins, int i, int j){
		int sum=0;
		for(int k =i; k<=j; k++){
			sum += coins[k];
		}
		return sum;
	}
	
	//two steps ahead
	
	public static int maxCoinPick(int[] coins){
		
		//assume the max pick is P(i, j)
		// if I picks a(i)
		// best for opponent P(i+1,j)
		// if picks j
		// P(i, j-1)
		// max( sum(i,j) - p(i+1,j), sum(i,j) - p(i, j-1))
		// = sum(i,j) - min(p(i+1, j), p(i, j-1))
		
		int len = coins.length;
		int[][] pMatrix = new int[len][len];
		
		for(int i=0; i<len; i++){
			for(int m=0, n=i; n<len; m++, n++){ //from distance 0 to distance n-1
				System.out.println("m " + m + " n " + n);
				int summn = cumSum[n] - cumSum[m] + coins[m];
				int a = (m+1 <= len-1) ?pMatrix[m+1][n]: 0;
				int b = (n-1 >= 0)? pMatrix[m][n-1]: 0;
				pMatrix[m][n] = summn - Math.min(b, a);
			}
		}
		
		return pMatrix[0][len-1];
	}
	
	public static int maxMoney(int coins[]) {
		int len = coins.length;
		  int[][] P = new int[len][len];
		  int a, b, c;
		  for (int i = 0; i < len; i++) {
		    for (int m = 0, n = i; n < len; m++, n++) {
		      a = ((m+2 <= len-1)             ? P[m+2][n] : 0);
		      b = ((m+1 <= len-1 && n-1 >= 0) ? P[m+1][n-1] : 0);
		      c = ((n-2 >= 0)               ? P[m][n-2] : 0);
		      P[m][n] = Math.max(coins[m] + Math.min(a,b),
		                    coins[n] + Math.min(b,c));
		    }
		  }
		  //printMoves(P, A, N);
		  return P[0][len-1];
		}

}
