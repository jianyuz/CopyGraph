
public class FindMinimunInRotatedArray {

	public static void main(String[] args){
		int[] A = {5, 6, 9, 0, 1, 2, 3, 4};
		
		System.out.println(minimum(A));
		
		int[] B = {1, 3, 4, 5};
		System.out.println(minimum(B));
		
		int[] C = {1};
		System.out.println(minimum(C));
		
		int[] D = {4, 3};
		System.out.println(minimum(D));
		
		int[] E = {6, 3, 3, 5};
		System.out.println(minimum(E));
		
		int[] F = {6, 3, 3, 2, 2};
		System.out.println(minimum(F));
		
		int[] G = {8, 1, 8, 8, 8};
		System.out.println(minimum(G));
	}
	
	/**
	 * return index or return the value.
	 * @param A
	 * @return
	 */
	public static int minimum(int [] A){
		
		if(A == null || A.length == 0){
			throw new IllegalArgumentException("invalid argument");
		}
		
		int low = 0;
		int high = A.length -1;
		
		while(low < high){
			int mid = low + (high - low)/2;
			if(A[mid] > A[high]){//keep search in unsorted half.
				low = mid+1;
			}else{
				high = mid;
			}
		}
		return low;
	}
}
