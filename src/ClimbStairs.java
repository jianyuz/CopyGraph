
public class ClimbStairs {

	/*
	You are climbing a stair case. It takes n steps to reach to the top.

	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
   */
	
	/**
	 * one d dynamic programming.
	 * Note that we need to output n==1 first
	 * since array init and init value assign means requires n > 1;
	 * 
	 * 
	 * To reach to the nth step, you have only two choices:

Advance one step from the n-1th step.
Advance two steps from the n-2th step. advance one step to n-1 then to n is the same as advance one step from n-1 to n.
Therefore, f(n) = f(n-1) + f(n-2), which is the exact same recurrence formula defined by the Fibonacci sequence (with different base cases, though).
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(n ==0) return 0;
        if(n== 1) return 1;
        if(n== 2) return 2;
        
        int[] ways = new int[n+1];
        
        ways[1] = 1;
        ways[2] = 2;
        
        for(int i=3; i<=n; i++){
            ways[i] = ways[i-1] + ways[i-2]; 
            //it seems that n-2 to n got 2 ways ways[i-2] * 2;
            //but ways[i-1] to contains one duplicate ways [i-2] 
            //so minus ways[i-2] again.
        }
        
        return ways[n];
    }
	
	/**
	 * combinations'
	 * for 6 steps stairs
	 * say 2 1 steps and 2 2 steps.
	 * c4 2 how many ways to put the 2 steps. don't care about the order.
	 * 
	 * 
	 * Clearly, the above solution is of O(n) time complexity. In fact, there are more efficient way to get a Fibonacci number. One is to use the formulation of the sequence as stated in the Wikipedia page:

Fn=15‾‾√(1+5‾‾√2)n−15‾‾√(1−5‾‾√2)n.
By using an O(logn) algorithm (e.g., LeetCode - Pow(x, n)) to compute the pow operations, it needs only O(logn) time to get a desired Fibonacci number.

	 * x2 = x + 1 solution
	 * two value essential in FN computation.
	 * use formula to approximate it.
	 */
	
	public int climbStairs1(int n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		//approximate FN value fibonacci sequence.
		double s = Math.sqrt(5);
		double a = 0.5 + s/2; //(1 + sqrt(5))/2
		return (int)Math.floor(Math.pow(a, n+1)/s + 0.5);
	}
	
}
