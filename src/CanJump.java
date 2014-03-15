
public class CanJump {

	/**
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

	 * @param A
	 * @return
	 */
	public boolean canJump(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(A == null || A.length == 0) return false;
        
        int pos = 0, lastPos = -1;
        
        while(pos < A.length && pos != lastPos){ //make sure it is not stuck there.
            lastPos = pos; //update lastPos first.
            pos += A[pos];
            
        }
        
        if(pos >= (A.length -1)) {//if the index can go beyond the last one. then return true.
            //reach to the last index
            return true;
        }else{
            return false;
        }
    }
}
