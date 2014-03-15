package com.z2.t15;

public class MedianTwoArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrayA = new int[]{2, 5, 7, 8, 10, 16}; //6
		int[] arrayB = new int[]{1, 4, 6, 11, 18, 20}; //7
		
		System.out.println(mOfArrays(arrayA, 0, arrayA.length, arrayB, 0, arrayB.length));
		//should be 8. invariant
	    // i + j = m/2 + n/2;
		
		//chop both ends equally.
		//treat even number with special handling.
		//base case number = 1 or number = 2 (media in same array.
		//two median, take average of them.
		
		
		
		
	}
	
	public static float medianOfThree(int a, int b, int c){
		int min = Math.min(a, Math.min( b, c));
		int max = Math.max(a,  Math.max(b, c));
		
		return a + b + c - min - max;
	}
	
	public static float medianOfFour(int a, int b, int c, int d){
		int min = Math.min(a,  Math.min(b,  Math.min(c, d)));
		int max = Math.max(a,  Math.max(b,  Math.max(c, d)));
		return ((float)(a + b + c + d -min - max)) /2f;
	}
	//lenA < lenB
	
	public static float mOfArrays(int[] arrayA, int startA, int lenA,  int[] arrayB, int startB, int lenB ){
		assert(arrayA != null);
		assert(arrayB != null);
		//System.out.println("startA " + startA + " lenA " + lenA + " startB " + startB + " lenB " + lenB);
		
		if(lenA == 1){
			if(lenB == 1){
				return ((float)(arrayA[startA] + arrayB[startB]))/2f;
			}else if(lenB%2 ==0){//even
				return medianOfThree(arrayA[startA], arrayB[lenB/2+startB], arrayB[lenB/2-1+startB]);
			}else if(lenB%2 ==1){
				return (medianOfThree(arrayA[startA], arrayB[lenB/2 + 1 +startB], arrayB[lenB/2 -1 + startB]) + arrayB[lenB/2+ startB])/2f;
			}
		}
		
		if(lenA == 2){
			if(lenB == 2){
				return medianOfFour(arrayA[startA], arrayA[startA +1], arrayB[startB], arrayB[startB+1]);
			}else if(lenB%2 ==0){
				return medianOfFour(arrayB[lenB/2 + startB], arrayB[lenB/2-1 + startB], Math.max(arrayB[lenB/2 -2 + startB],  
						arrayA[startA + 1]), Math.min(arrayB[lenB/2+1 + startB], arrayA[startA]));
			}else if(lenB%2 == 1){
				return medianOfThree(arrayB[lenB/2 + startB], Math.max(arrayB[lenB/2 -1 + startB], arrayA[startA + 1]), 
						Math.min(arrayB[lenB/2 + 1 + startB], arrayA[startA]));
			}
		}
		
		int midA = lenA/2 + startA;
		int midB = lenB/2 + startB;
		//System.out.println("midA " + midA + " midB " + midB);
		int truncateSize;
		
		if(arrayA[midA] < arrayB[midB]){
			if(lenA%2 ==0){ midA --;}
			truncateSize = Math.min(midA-startA, lenB-midB + startB-1);
			return mOfArrays(arrayA, startA + truncateSize, lenA -truncateSize, arrayB, startB, lenB-truncateSize);
		}else{
			if(lenB%2 ==0){midB --;} //equal median.
			truncateSize = Math.min(midB-startB, lenA - midA + startA -1);
			return mOfArrays(arrayA, startA, lenA -truncateSize, arrayB, startB+truncateSize, lenB-truncateSize);

		}
		
	}
	
	/**---------ignore -------**/
	
	public static int medianOfTwoArrays(int[] arrayA, int[] arrayB){
		int lowA = 0, highA = arrayA.length-1;
		int lowB = 0, highB = arrayB.length-1;
		int midA, midB, medianMid;
		
		midA = lowA + (highA - lowA)/2;
		midB = lowB + (highB - lowB)/2;
		medianMid = midA + midB;
		
		int num, temp;
		int[] tempArray;
		
		while (true) {
			// search midA in arrayB.
			midA = lowA + (highA - lowA)/2;
			num = binarySearch(arrayB, lowB, highB, midA);
			if (num + midA < medianMid) {
				lowB = num;
			} else if (num + midA > medianMid) {
				highB = num;
			} else {
				return arrayA[midA];
			}
			
			temp = lowA;
			lowA = lowB;
			lowB = temp;
			
			temp = highA;
			highA = highB;
			highB = temp;
			
			tempArray = arrayA;
			arrayA = arrayB;
			arrayB = tempArray;
		}
		
	}
	

	//number of elements that are smaller than input.
	public static int binarySearch(int[] array, int low, int high, int input){
		int mid;
		//find the largest index that array[i] < input.
		while(low < high){
			mid = low + (high-low + 1)/2;
			if(array[mid] >= input){
				high = mid-1;
			}else{
				low = mid;
			}
		}
		
		if(array[low] > input){
			return low;
		}
		return low + 1;
	}

}
