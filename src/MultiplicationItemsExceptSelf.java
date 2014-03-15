import java.util.Arrays;

/**
 * calculate multiplication of the item except itself.
 * how about solve it without using division
 * dynamic programming
 * 
 * @author zhouzhou
 *
 */
public class MultiplicationItemsExceptSelf {
	
	public static void main(String[] args){
		//int[] a = {2, 4, 2, 0, 5, -1, 0};
		//int[] a = {2, 4, 0, 5};
		int[] a = {2, 4,-5};
		int[] res = multiply(a);
		System.out.println(Arrays.toString(res));
		int[] res1 = multiply(a);
		System.out.println(Arrays.toString(res1));
		int[] res2 = multiply(a);
		System.out.println(Arrays.toString(res2));
		int[] res3 = multiply(a);
		System.out.println(Arrays.toString(res3));
	}

	public static int[] multiply(int[] a){
		if(a == null) return null;
		int n = a.length;
		int[] res = new int[n];
		if(a.length == 0) return res;
		
		//multiplication and divide doesn't work with value 0.
		//if more than 2 zeroes.
		//all the res =0 
		int multi = 1;
		boolean hasZero = false;
		for(int i=0; i< n; i++){
			if(a[i] == 0 && !hasZero){
				hasZero = true;
			}else if(a[i] == 0 && hasZero){
				return res; //all zero
			}else{
				multi *= a[i];
			}
		}
		
		for(int i=0; i< n; i++){
			if(a[i] == 0){
				res[i] = multi;
			}else if(hasZero){
				res[i] = 0;
			}else{
				res[i] = multi/a[i];
			}
		}
		
		return res;
	}
	
	/**
	 * method doesn't use division
	 * but does consume space.
	 * 
	 * @param a
	 * @return
	 */
	public static int[] multiply1(int[] a){
		if(a == null) return null;
		int n = a.length;
		int[] res = new int[n];
		if(a.length == 0) return res;

		int[] left = new int[n];
		left[0] = 1;
		for(int i=1; i< n; i++){
			left[i] = a[i-1] * left[i-1];
		}
		
		int[] right = new int[n];
		right[n-1] = 1;
		for(int i=n-2; i>=0; i--){
			right[i] = a[i+1] * right[i+1];
		}
		
		for(int i=0; i< n; i++){
			res[i] = left[i] * right[i];
		}
		return res;
	}
	
	/**
	 * keep one left and right variable.
	 * @param a
	 * @return
	 */
	public static int[] multiply2(int[]a ){
		if(a == null) return null;
		int n = a.length;
		int[] res = new int[n];
		if(a.length == 0) return res;
		
		int left = 1, right = 1;
		for(int i=0; i< n; i++){
			res[i] = 1 * left;
			left *= a[i];
		}
		
		for(int i=n-1; i>=0; i--){
			res[i] = res[i] * right;
			right *= a[i];
		}
		return res;
	}
	
	//combine left right accumulation into one loop
	public static int[] multiply3(int[]a ){
		if(a == null) return null;
		int n = a.length;
		int[] res = new int[n];
		if(a.length == 0) return res;
		for(int i=0; i< n; i++){
			res[i] = 1;//must init with 1 for all res.
		}
		
		int left = 1, right = 1;
		for(int i=0; i< n; i++){
			res[i] *= left;
			res[n-1-i] *=  right;
			left *= a[i];
			right *= a[n-1-i];
		}
		
		return res;
	}
}
