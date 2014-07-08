package anno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VarargTest {

	/** the array of stock data */
	private static Integer[] mystock;
	
	/**
	 * Convert stock list array to a list.
	 * 
	 * @return the list of stocks.
	 */
	public static List<Integer> toStockList(){
		if(mystock.length == 0){
			return Collections.EMPTY_LIST;
		}else{
			return Arrays.asList(mystock);
		}
		
	}
	
	/**
	 * Sum up variable length of args
	 * @param args the list of arguments
	 * @return sum of the arguments {@literal number of count < 10}
	 * @throws NullPointerException if the passed in args is null {@code nullPointerException}
	 */
	static int sum(int... args) {
		int sum = 0;
		for (int arg : args)
			sum += arg;
		return sum;
	}
	
	//ugly, no null checking.
//	static int min(int... args){
//		if(args.length == 0)
//			throw new IllegalArgumentException("illegal");
//		int min = args[0];
//		for(int arg: args){
//			if(arg < min){
//				min = arg;
//			}
//		}
//		return min;
//	}
	
	/**
	 * make sense to use one or more pattern.
	 * note this one is in conflict with the previous one. compiler can't differentiate.
	 * @param first
	 * @param args
	 * @return
	 */
	static int min(int first, int... args){
		int min = first;
		for(int arg: args){
			if(arg < min){
				min = arg;
			}
		}
		return min;
	}
	
	public static void main(String[] args){
		System.out.println(sum());
		System.out.println(min(3, 1, 4));
		int[] digits = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 4 };
		//asList accepts objects , this allow varargs now.
		//covert array address into object[] with one element
		System.out.println(Arrays.asList(digits));
		System.out.println(Arrays.toString(digits));
	}
	
}
