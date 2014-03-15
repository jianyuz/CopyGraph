import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * @author zhouzhou
 *
 */
public class NQueen {

	public static void main(String[] args){
		ArrayList<String[]> res = solveNQueens(8);
		
		System.out.println("output: ");
		for(String[] item: res){
			System.out.println(Arrays.toString(item));
		}
	}
	public static ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<String[]> res = new ArrayList<String[]>();
        if(n<=0) return res;
        
        int[] sol = new int[n]; //keep track of which row we selected in each column.
        //we pin down solution per column use a array is enough to track the taken position
        //and solution.
       
        helper(n, 0, sol, res);
        
        return res;
        
    }
    
	/**
	 * get solution col by col
	 * @param n
	 * @param j
	 * @param sol
	 * @param res
	 */
    private static void helper(int n, int j, int[] sol, ArrayList<String[]> res){
        
    	if(j == n){ //find solution for all the columns.
        	res.add(transform(sol)); //add result 
        	return;
        }
    	//check if row is taken
    	for(int i=0; i<n; i++){
                if(!taken(i, j, sol)){
                    sol[j] = i; //find solution for the current column, no need to recover value of s[j]. next solution override it.
                    helper(n, j +1, sol, res);//increment column
                }
        }
    }
    
    private static String[] transform(int[] sol){
    	String[] res = new String[sol.length];
    	for (int i = 0; i < sol.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < sol.length; j++) {
                if (j == sol[i]) {
                    sb.append("Q");
                }
                else {
                    sb.append(".");
                }
            }
            res[i] = sb.toString();
        }
    	return res;
    }
    
    private static boolean taken(int row, int col, int[] sol){
    	for(int i=0; i< col; i++){
    		if(row == sol[i]){ //if the row is taken
    			return true;
    		}
    		if(col - i == Math.abs(sol[i] - row)){ //if the diag position is taken.
    			return true; //diagnal check.
    		}
    	}
    	return false;
    }
}
