import java.util.BitSet;


public class ValidSudoko {

	public boolean isValidSudoku(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
        BitSet bs = new BitSet(9);
        
        int n = board.length;
        
        for(int i = 0; i< n; i++){
            bs.clear();
            for(int j=0; j< n; j++){
               
                if(board[i][j] != '.'){
                    int val = Integer.parseInt("" + board[i][j]);
                    if(bs.get(val-1)) return false;
                    bs.set(val-1);
                }
            }
        }//check each row
        
        for(int i = 0; i< n; i++){
            bs.clear();
            for(int j=0; j< n; j++){
               
                if(board[j][i] != '.'){
                    int val = Integer.parseInt("" + board[j][i]);
                    if(bs.get(val-1)) return false;
                    bs.set(val-1);
                }
            }
        }//check each column
        
        //check small square
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(!checkSmall(board , i * 3, j * 3)) return false;
            }
        }
        return true;
    }
    
    private boolean checkSmall(char[][] board, int si, int sj){
        BitSet bs = new BitSet();
        
        int ci, cj;
        for(int i=0; i<3; i++){
            for(int j=0; j< 3; j++){
                ci = si + i;
                cj = sj + j;
                 if(board[ci][cj] != '.'){
                    int val = Integer.parseInt("" + board[ci][cj]);
                    if(bs.get(val-1)) return false;
                    bs.set(val-1);
                }       
            }
        }
        return true;
    }
}
