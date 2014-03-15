import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * in an array find the odd number of elements in it.
 * @author zhouzhou
 *
 */
public class OddNumberElementsInArray {
	
	public static void main(String[] args){
		int[] input = new int[] {2, 2, 3, 1, 3, 1, 1};
		
		System.out.println(findOddElements(input));
		
		input = new int[] {2, 2, 3, 3, 3, 1, 1, 1};
		
		System.out.println(findEvenElements(input));
	}
	
	public static int  findOddElements(int[] input){
		if(input == null || input.length == 0) return -1;
		
		int res = input[0];
		for(int i=1; i< input.length; i++){
			res ^= input[i];
		}
		return res;
	}
	
	/**
	 * modify to get odd number of elements.
	 * O(n) time complexity 
	 * and O(n) space.
	 * @param input
	 * @return
	 */
	public static int findEvenElements(int[] input){
		if(input == null || input.length == 0) return -1;
		Set<Integer> intSet = new HashSet<Integer>();
		for(int item: input){
			intSet.add(item);
		}
		int res = input[0];
		for(int i=1; i< input.length; i++){
			res ^= input[i];
		}
		
		//padd the unique values.
		for(Iterator<Integer> it=intSet.iterator(); it.hasNext(); ){
			res ^= it.next();
		}
		return res;
	}

}
