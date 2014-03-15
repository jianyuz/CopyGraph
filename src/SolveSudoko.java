import java.util.Arrays;
import java.util.BitSet;


public class SolveSudoko {

	
	public static void main(String[] args){
		String[] input ={
				"..9748...",
				"7........",
				".2.1.9...",
				"..7...24.",
				".64.1.59.",
				".98...3..",
				"...8.3.2.",
				"........6",
				"...2759.."};
		char[][] board = new char[9][9];
		
		for(int i=0; i< input.length; i++){
			for(int j=0; j< input[i].length(); j++){
				board[i][j] = input[i].charAt(j);
			}
		}
		 for(int i=0; i<9; i++){
	        	System.out.println(Arrays.toString(board[i]));
	        }
	        
		
		solveSudoku1(board);
		System.out.println();
	
		 for(int i=0; i<9; i++){
	        	System.out.println(Arrays.toString(board[i]));
	        }
	        
	}
	
	/**
	 * depth first search to solve sudoku.
	 * 
	 * @param board
	 */
	 public static void solveSudoku(char[][] board) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        //keep 9 * 3 27 bit maps for each column, each line
	        //and each square.
	        //for each filled in value.
	        //check if the board is valid.
	        
	        int n = board.length;
	        
	        BitSet [] rows = new BitSet[n];
	        BitSet [] cols = new BitSet[n];
	        BitSet [] blocks = new BitSet[n];
	        
	        for(int i=0; i<n; i++){
	            rows[i] = new BitSet();
	        }
	        
	        for(int i=0; i<n; i++){
	            cols[i] = new BitSet();
	        }
	        
	         for(int i=0; i<n; i++){
	            blocks[i] = new BitSet();
	        }
	        
	        //init the bitset arrays using the existing values.
	        for(int i=0; i<n; i++){
	            for(int j=0; j<n; j++){
	                if(board[i][j] != '.'){
	                   rows[i].set(board[i][j] - '1');
	                   cols[j].set(board[i][j] - '1');
	                   
	                   int index = i/3 * 3 + j/3;
	                   blocks[index].set(board[i][j] -'1');
	                } 
	            }
	        }
	        
	        solve(board, rows, cols, blocks, n, 0, 0);
	       
	    }
	    
	    public static boolean solve(char[][] board, BitSet[] rows, BitSet[] cols, BitSet[] blocks, 
	                    int n, int i, int j){
	        
	        if(i == n  || j == n) { return true; } //one by one increment
	        //if i or j reaches n we are done.
	            
	        if(board[i][j] == '.'){
	            //what are possible values.
	            //look at the row bitset.
	            //look at the col bitset.
	            //and the clock bitset.
	            BitSet bs = new BitSet();
	            bs.or(rows[i]);
	            bs.or(cols[j]);
	            int index = i/3 * 3 + j/3; //can't simply the formula.
	            bs.or(blocks[index]);
	            
	            int curBit = 0;
	            int clearBit = -1;
	            while((clearBit = bs.nextClearBit(curBit)) < n){
	                //fill the slot with index.
	                board[i][j] = (char)('1' + clearBit);
	                rows[i].set(clearBit);
	                cols[j].set(clearBit);
	                blocks[index].set(clearBit);
	                curBit = clearBit +1 ; //curBit should start one more.
	                //otherwise we got into dead loop.
	                int oldi = i;//keep track of old i and old j to revert back.
	                int oldj = j;
	                j ++;
	                if(j >= n) {
	                    i++;
	                    j = j%n;
	                }
	                
	                boolean res = solve(board, rows, cols, blocks, n, i, j);
	                if(!res){//revert the selection. continue try next.
	                	board[oldi][oldj] = '.';
	                    rows[oldi].clear(clearBit);
	                    cols[oldj].clear(clearBit);
	                    blocks[index].clear(clearBit);
	                    i = oldi;
	                    j = oldj;
	                }else{
	                	return true;
	                }
	            }
	            
	            //tried all no solution. return false.
	            return false;
	        }else{//advance if we see the char is not .
	            j ++;
	            if(j >= n) {
	                i++;
	                j = j%n;
	            }
	            return solve(board, rows, cols, blocks, n, i, j);
	        }
	        
	    }
	    
	    
	    public static void solveSudoku1(char[][] board) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        int n = board.length;
	        solve(board, n);
	    }
	    
	    public static boolean solve(char[][] board, int n){
	        
	        for(int i=0; i< n; i++){
	            for(int j=0; j<n; j++){
	                if(board[i][j] == '.'){
	                    for(int k=0; k<n; k++){
	                        board[i][j] = (char)('1' + k);
	                        if(valid(board, n,  i, j) && solve(board, n)){
	                            return true;
	                        }
	                        board[i][j] = '.';
	                    }
	                    //tried all the 9 solution.
	                    return false;
	                }
	            }
	        }
	        return true; //all done no '.' found return true.
	    }
	    
	    public static boolean valid(char[][] board, int n, int a, int b){
	        //check rows, cols and blocsk to make sure they are all correct.
	        BitSet bs = new BitSet();
	        
	        //for the particular row.
	        //check if it is correct.
	        for(int i=0; i<n; i++){
	            if(board[a][i] != '.'){
	                if(bs.get(board[a][i]-'1')) return false;
	                else bs.set(board[a][i]-'1');
	            }
	        }
	        
	        bs.clear();
	        
	        for(int i=0; i<n; i++){
	            if(board[i][b] != '.'){
	                if(bs.get(board[i][b]-'1')) return false;
	                else bs.set(board[i][b]-'1');
	            }
	        }
	        
	        bs.clear();
	        //verify block;
	        int x = a/3;
	        int y = b/3;
	        
	        for(int i = x * 3; i < (x+1) *3 ; i++){
	            for(int j = y * 3; j < (y+1) * 3; j++){
	                if(board[i][j] != '.'){
	                    if(bs.get(board[i][j]-'1')) return false;
	                    else bs.set(board[i][j]-'1');//set index , must minus '1'
	                }
	            }
	        }
	        
	        return true;
	        
	    }
}
