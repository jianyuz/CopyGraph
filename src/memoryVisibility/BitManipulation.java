package memoryVisibility;

public class BitManipulation {

	public static int add(int m, int n, int i, int j){
		int mask = 0xffffffff;
		mask = mask << j; //filled in with 0.
		
		int mask1 = 0xffffffff;
		mask1 = mask1 << i;
		mask1 = ~mask1;
		
		mask = mask | mask1;
		
		m = m & mask; //clear from i to j.
		
		n = n << i; //filled with 0;
		
		return m | n;
		
	}
	
	public static void main(String[] args){
		 int n = 1<<10, m = 21;
		 int ans = add(n, m, 2, 6);
		 System.out.println(Integer.toBinaryString(ans));
		    		
	}
}
