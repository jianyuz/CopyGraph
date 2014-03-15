
/**
 * find median of two sorted arrays.
 * 
 * divide condition into two
 * m + n is even 
 * or m+n is odd
 * convert problem to solve finding kth element
 * 
 * take care of special case
 * one of the array is empty.
 * 
 * then divide the k/2
 * assign k/2 to m array
 * the other k/2 to n array.
 * guess the position of Kth element.
 * chop off the half of array that is not possible to contain element k.
 * either array m is empty
 * then k-1 element of array B
 * or k is 1, compare element of A and B.
 * @author zhouzhou
 *
 */
public class MedianOfSortedArray {

	public static void main(String[] args){
		double res = findMedianSortedArrays(new int[] {1, 2}, new int[]{3, 4});
		System.out.println(res);
	}
	 public static double findMedianSortedArrays(int A[], int B[]) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	      
	            
	        int m = A.length;
	        int n = B.length;
	        
	        if(m == 0){
	        	if(n%2 == 0){
	        		return (double)(B[n/2-1] + B[n/2])/2;
	        	}else{
	        		return B[n/2];
	        	}
	        }
	        
	        if(n == 0){
	        	if(m%2 == 0){
	        		return (double)(A[m/2-1] + A[m/2])/2;
	        	}else{
	        		return A[m/2];
	        	}
	        }
	        
	        int sum = m+n;
	        
	        if(sum %2 == 1){//odd number, only one median.
	            return findMedian(A, 0, m-1, B, 0, n-1, sum/2+1);//the k th element is su/2 + 1.
	        }else{
	            double t1 = findMedian(A, 0, m-1, B, 0, n-1, sum/2) ;
	            double t2 = findMedian(A, 0, m-1, B, 0, n-1, sum/2 + 1);
	            //System.out.println("t1, t2 " + t1 + " " + t2);
	            return (t1 + t2)/2;
	        }
	    }
	    
	    public static double findMedian(int[] A, int aStart, int aEnd, 
	        int[] B, int bStart, int bEnd,  int k){
	        //find the kth elements and remove segment.
	        
	        //ending condition.
	        
	        int m = aEnd - aStart + 1;
	        int n = bEnd - bStart + 1;
	        if(m > n){
	            return findMedian(B, bStart, bEnd, A, aStart, aEnd, k); //reverse array.
	            //let shorter one stay first.
	        }
	        if(m == 0) return B[bStart + k-1]; // A length run out first. not to add bStart.
	        if(k == 1) return Math.min(A[aStart], B[bStart]);
	        
	        int pa = Math.min(k/2, m); //k/2 may be bigger than m.
	        int pb = k - pa;
	        if(A[aStart + pa -1] <= B[bStart + pb -1]){
	            //remove lower A
	            return findMedian(A, aStart + pa, aEnd, B, bStart, bEnd, k - pa);//note to add start.
	        }else{
	            //remove lowerB
	            return findMedian(A, aStart, aEnd, B, bStart + pb, bEnd, k - pb); 
	        }
	        
	    }
}
