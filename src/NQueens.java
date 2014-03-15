import java.util.Arrays;


public class NQueens {

	public static void main(String[] args){
		System.out.println(totalNQueens(4));
	}
	public static int totalNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(n<=0) return 0;
        
        //every row must have one.
        //need to determine the column of the piece in each row.
        
        //once the column is determined.
        //no piece can select the same column 
        //plus can't stay at the position |i-j| = |row-col| diaganal.
        //and |i + j| = | row + col| reverse diaganal.
        
        int[] colPos = new int[n];
        
        boolean[] colFlags = new boolean[n];
        //data structure -- an array to record the col positions.
        //for each row.
        
        //use a count to keep track of total configuration found so far.
        //will be the return of recursive function.
        //use an int to represent current col we are find solution for.
        
        return findSolution(colPos, 0, colFlags);
        
    }
    
    public static  int findSolution(int[] colPos, int curRow, boolean[] colFlags){
        
        if(curRow == colPos.length){
            //termination condition.
            return 1;//found one.
        }
        
        int count = 0;
        for(int i=0; i< colFlags.length; i++){
            if(!colFlags[i]){ //col not used.
                //determine if it is feasible to use col i.
                boolean canUse = true;
                for(int j=0; j < curRow; j++){//need to check all current selections.
                    int diff = j- colPos[j];//here note don't need to use math.abs.
                    int diff1 = curRow - i;
                    int sum = j + colPos[j];
                    int sum1 = curRow + i;
                    if(diff == diff1 || sum == sum1){
                        canUse = false;
                        break;
                    }//else not possible
                }
                
                if(canUse){
                //found the pos.
                    colFlags[i] = true;
                    colPos[curRow] = i;
                    System.out.println("row " + curRow + " col " + i );
                    System.out.println(Arrays.toString(colFlags));
                //continue to search
                    count += findSolution(colPos, curRow + 1, colFlags);
                    colFlags[i] = false;//reset the flag after depth first search.
                }
            }
        }
        
        
        return count;
    }
}
