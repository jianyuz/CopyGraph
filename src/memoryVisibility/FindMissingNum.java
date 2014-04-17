package memoryVisibility;
/**
 * Add arithmetic operators (plus, minus, times, divide) to make the following expression true: 3 1 3 6 = 8. You can use any parentheses you’d like.
 * (3 + 1) * 6 / 3
 * cover chessboard using dominos
 * color the square alternatively.
 * number of the squars?
 * x2 + x -200 >=0
 * sqrt(1 + 800) + 2 / 2
 * 
 * find out jump trials
 * minimum steps using two eggs.
 * the worst case
 * first try doesn't break the egg.
 * try to make sure if second try break.
 * we still have room to finish in x-2 try
 * x + (x-1) + ... 1 >=100
 * 14
 * 14, 27, 39, 50 ....
 * 
 * 
 * open locks
 * o o o o o o o o o o
 * o c o c o c o c o c
 * o c c c o o o c c c
 * o c c o o o o o c c 
 * o c c o c o o o c o
 * o c c o c c o o c o
 * o c c o c c c o c o
 * o c c o c c c c c o
 * o c c o c c c c o o
 * o c c o c c c c o c
 * even number of switch, o 
 * 2 3 4 5 6 7 8 ... 100
 * 
 * 100 = 2 4 5 10 20 25 50 100 o
 * 1 o
 * 2 = 2 c
 * 3 = 3 c
 * 4 = 2 4 o
 * 5 = 5 c
 * 6 = 2 3 6 c
 * 7 = 7 c
 * 8 = 2 4 8 c
 * 9 = 3 9 o
 * 10 = 2 5 10 c
 * 11 = 11 c
 * 12 = 2 3 4 6 12 c
 * 13 = 12 c
 * 14 = 2 7 14 c
 * 15 = 3 5 15 c
 * 16 = 2 4 8 16 o
 * 17 = 17 c
 * 18 = 2 3 6 9 18 c
 * 19 = 19 c
 * 20 = 2 4 5 10 20 c
 * 
 * 1 4 9 16 25 36 49 64 81 100
 * how to make number of factors odd.
 * i/a = a
 * so the number is square.
 * 
 * 
 * 
 * @author zhouzhou
 *
 */
public class FindMissingNum {

	public static void main(String[] args){
		int[] input = new int[]{1, 3, 4, 5, 8, 7, 6};
		int n = 8;
		System.out.println("missing number is " + missingNum(input, n));
		System.out.println("missing number is " + missingNum1(input, n));
		input = new int[] {1, 3, 4, 5, 5, 8, 7, 6};
		findRepeatingAndMissing(input, n);
	}
	public static int missingNum(int[] input, int n){
		if(input == null) return -1;
		
		int sum = (n + 1) * n / 2;
		for(int i=0; i< input.length; i++){
			sum -= input[i];
		}
		return sum;
	}
	
	public static int missingNum1(int[] input, int n){
		if(input == null || input.length == 0) return -1;
		
		int res = input[0];
		for(int i=1; i< input.length; i++){
			res ^= input[i];
		}
		
		for(int i=1; i<=n; i++){
			res ^= i;
		}
		return res;
	}
	
	public static void findRepeatingAndMissing(int[] input, int n){
		//xor of everything 
		//become xor of missing and repeating number.
		
		if(input == null || input.length == 0) return ;
		int res = input[0];
		for(int i=1; i< input.length; i++){
			res ^= input[i];
		}
		
		for(int i=1; i<=n; i++){
			res ^= i;
		}
		
		//rightmost set bit, must be coming from x or y.
		//divide the array into one contains x and one contains y.
		int rightMost = rightMostSetBit(res);
		
		int x = 0;
		int y = 0;
		
		for(int i=0; i< input.length; i++){
			if((input[i] & rightMost) == 1){
				x ^= input[i];
			}else{
				y ^= input[i];
			}
		}
		
		
		for(int i=1; i<=n; i++){
			if((i & rightMost) == 1){
				x ^= i;
			}else{
				y ^= i;
			}
		}
		System.out.println("x = " + x + " y= " + y);
	}
	
	public static int rightMostSetBit (int input){
		int bits = 0;
		
		while(input >0  && (input & 1) == 0){
			input >>= 1;
			bits++;
		}
		
		return 1 << bits;
	}
}
