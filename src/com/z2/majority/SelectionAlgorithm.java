package com.z2.majority;

/**
 * selection algorithm
 * similiar to quick sort.
 * 
 * @author zhouzhou
 *
 */
public class SelectionAlgorithm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a = new int[] {1, 2, 4, 5, 6, 7, 9};
		System.out.println(selection(a, 0, a.length-1, 1));
		System.out.println(selection(a, 0, a.length-1, 7));
		System.out.println(selection(a, 0, a.length-1, 3));
		System.out.println(selection(a, 0, a.length-1, 4));
	}
	/**
	 * pick an element.
	 * move smaller elements to left.
	 * larger elements to right.
	 * @param a
	 * @param left
	 * @param right
	 * @param pivotIndex
	 * @return
	 */
	public static int partition (int a[], int left, int right, int pivotIndex){
		//move pivotVale to right;
		
		int temp;
        temp = a[pivotIndex];
        a[pivotIndex] = a[right];
        a[right] = temp;
        
        //then divide the elements bigger and smaller than pivot.
        int storeIndex = left;
        for(int i = left; i<= right -1; i++){
        	if(a[i] <= a[right]){//compare with pivot value.
        		//swap with storeIndex element
        		temp = a[storeIndex];
        		a[storeIndex] = a[i];
        		a[i] = temp;
        		//increment storeIndex
        		storeIndex ++;
        	}
        }
        //store the pivot value back to its location
        temp = a[right];
        a[right] = a[storeIndex];
        a[storeIndex] = temp;
        return storeIndex;
	}
	
	/**
	 * find the kth element.
	 * @param a
	 * @param left
	 * @param right
	 * @param k
	 */
	public static int selection(int a[], int left, int right, int k){
		
		//simply select the mid element as pivot point.
		while (left <= right) {
			int pivotIndex = left + (right - left)/2;
			int newPivotIndex = partition(a, left, right, pivotIndex);
			int pivotDist = newPivotIndex - left + 1;
			//distance, the order of the elements in the subarray.
			if (pivotDist == k) {//update left and right, pivot index choice.
				//compare pivotDist with kth.
				return a[newPivotIndex];
			} else if (pivotDist < k) {
				left = newPivotIndex + 1;
				k = k - pivotDist;
			} else {
				right = newPivotIndex - 1;
			}
		}
		return Integer.MIN_VALUE;
		
	}
	

}
