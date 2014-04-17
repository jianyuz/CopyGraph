package memoryVisibility;

/**
 * dp solution?
 * @author zhouzhou
 *
 */
public class WaysForChanges {

	public static void main(String[] args){
		System.out.println(ways(27, 25));
		System.out.println(sum(27));
		int[] denom = new int[] {25, 10, 5, 1};
		System.out.println(ways1(27, denom, 0));
	}
	
	/**
	 * iteration.
	 * @param total
	 * @return
	 */
	public static int sum(int total){
		
		int res = 0;
		for(int i=0; i<=total/25; i++){
			int tmp = total - i * 25;
			for(int j=0; j<= tmp /10; j++){
				int tmp1 = tmp - j * 10;
				for(int k=0; k<= tmp1 /5; k ++){
					res ++; //for 1s there is only one way.
				}
			}
		}
		
		return res;
	}
	
	/**
	 * recursive solution.
	 * @param total
	 * @param denom
	 * @return
	 */
	public static int ways(int total, int denom){
		
		int next = 0;
		switch(denom){
		case 25:
			next = 10;
			break;
		case 10:
			next = 5;
		    break;
		case 5:
			next = 1;
			break;
		case 1:
			return 1; //just one way to represent.
		}
		
		int res = 0;
		for(int i = 0; i*denom < total; i++){
			res += ways(total - i * denom, next);
		}
		return res;
	}
	
	public static int ways1( int total, int[] denom, int curIndex){
		if(total <=0 ) return 0;
		if(curIndex == denom.length -1) return 1;
		int denominater  = denom[curIndex];
		int sum = 0;
		for(int i=0; i * denominater <= total; i++){
			sum += ways1(total-i*denominater, denom, curIndex + 1);
		}
		return sum;
	}
}
