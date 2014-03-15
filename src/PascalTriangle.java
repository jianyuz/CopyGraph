import java.util.ArrayList;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 * @author zhouzhou
 *
 */
public class PascalTriangle {

	public static void main(String[] args){
		generate(2);
	}
	
	public static ArrayList<ArrayList<Integer>> generate(int numRows) {
		if(numRows < 0) return null;
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(numRows < 1) return res;
        
        ArrayList<Integer> row1 = new ArrayList<Integer>();
        row1.add(1);
        res.add(row1);
        
        ArrayList<Integer> parentRow, currRow;
        int left, right;
        for(int row=1; row< numRows; row++){
            currRow = new ArrayList<Integer>();
            parentRow = res.get(row -1);
            for(int i=0; i<=row; i++){
                left = (i-1 >=0) ? parentRow.get(i-1): 0;
                right = (i < row) ? parentRow.get(i): 0;
                currRow.add(left+right);
            }
            res.add(currRow);
        }
        return res;
    }
	
	/**
	 * Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
	 * @param rowIndex
	 * @return
	 */
	public static ArrayList<Integer> getRow(int rowIndex) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(rowIndex < 0) return null;
        
        int size = rowIndex +1 ;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0; i< size; i++){
            res.add(0); //senitel.
        }
        
        res.set(0, 1);//set row 0
        
        int left, right;
        for(int row=1; row<=rowIndex; row++){
            for(int i=row; i>=0; i--){
                left = (i-1 >=0) ?res.get(i-1): 0;
                right = (i < row) ? res.get(i): 0;
                res.set(i, left+right);
            }
        }
        return res;
    }
}
