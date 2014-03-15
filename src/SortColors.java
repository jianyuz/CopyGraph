
public class SortColors {

	/**
	 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
	two passes alrogithm
	found number frequency then assign.

	 * @param A
	 */
	public void sortColors(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0 || A.length == 1) return;
        
        int[] counts = new int[3];
        
        for(int i=0; i< A.length; i++){
            counts[A[i]] ++;
        }
        
        int index = 0;
        for(int i=0; i< counts.length; i++){
            for(int j=0; j< counts[i]; j++){
                A[index++] = i;
            }
        }
    }
	
	
	public void sortColors1(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A== null || A.length == 0 || A.length == 1) return;
        
        int redPos = 0;
        int bluePos = A.length -1;
        
        int cur =0;
        
        int tmp;
        //cur is between redPos and bluePos.
        while(cur >= redPos && cur <= bluePos){
            if(A[cur] == 0 ){ //if cur is 0 swap with redpos
                //swap
                if(A[cur] != A[redPos]){
                    tmp = A[redPos];
                    A[redPos] = A[cur];
                    A[cur] = tmp;
                }else{
                    cur ++; //not swap, A[redPos} already 0. need to move cur by one.
                    //a[cur] is 0 too. cur need to move in sync with redPos. cur >=redPos.
                    //cause cur == redPos.
                }
                redPos ++; //increase redpos. no matter swap or not.
            }else if(A[cur] == 2){
               if(A[cur] != A[bluePos]){
                    tmp = A[bluePos];
                    A[bluePos] = A[cur];
                    A[cur] = tmp;
                }
                bluePos --;//reduce bluePos by one.
            }else{
                cur++;
            }
            
        }
    }
}

