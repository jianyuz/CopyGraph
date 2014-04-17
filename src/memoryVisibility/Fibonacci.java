package memoryVisibility;

public class Fibonacci {

	public static void main(String[] args){
		System.out.println(fib(6));
		System.out.println(fib1(6));
		System.out.println(pow(3, 4));
		System.out.println(fib2(5));
		System.out.println(fib2(6));
		System.out.println(fib2(7));
	}
	
	public static int fib( int n){//recursive version.
		if(n < 1 ) return -1;
		if(n== 1 || n==2) return 1;
		
		
		return fib(n-1) + fib(n-2);
	}
	
	public static int fib1(int n){//iteration version
		if(n < 1) return -1;
		if(n == 1 || n==2) return 1;
		
		int a = 1;
		int b = 1;
		
		int res = 0;
		for(int i = 3; i<= n; i++){
			res = a + b;
			a = b;
			b = res;
		}
		return res;
	}
	
	/** the n power of m logn solution.*/
	public static int pow(int m, int n){
		int res = 1;
		while(n > 0){
			if((n & 1) == 1) res *=m; //calculate the bit value at pos. if bit is 1, times it.
			m *=m; //double the m;
			n >>= 1; //next bit;
		}
		return res;
		
	}
	
	/**
	 * matrix version of fib logn solution.
	 */
	
	public static void pow(int[][] s, int[][] a, int n){
		while(n > 0){
			if((n & 1) ==1) mul(s, s, a);
			mul(a, a , a);
			n >>= 1;
		}
	}
	
	public static void mul(int[][] s, int[][] a, int[][] b){
		int r1 = a[0][0] * b[0][0] + a[0][1] * b[1][0];
		int r2 = a[0][0] * b[0][1] + a[0][1] * b[1][1];
		
		int r3 = a[1][0]*b[0][0] + a[1][1]*b[1][0];
	    int r4 = a[1][0]*b[0][1] + a[1][1]*b[1][1];
		s[0][0] = r1;
		s[0][1] = r2;
		s[1][0] = r3;
		s[1][1] = r4;
	}
	
	public static int fib2(int n){
		if(n < 1) return -1;
		if(n == 1 || n == 2) return 1;
		
		int[][] s = new int[][]{{1,0}, {0, 1}};
		int[][] a = new int[][]{ {1, 1}, {1, 0 }};
		
		pow(s, a, n-2);
		
		return s[0][0] + s[0][1];
	}
}
