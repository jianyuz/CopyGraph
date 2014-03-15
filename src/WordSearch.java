
public class WordSearch {

	/**
	 * dynamic programming word search.
	 * can't make sure doesn't repeat visit.
	 * 
	 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(board == null) return false;
        if(board.length == 0 || board[0].length == 0) return false;
        
        int len = word.length();
        int rows = board.length;
        int cols = board[0].length;
        
        if(len > rows * cols) return false; // word length is more than what's available
        //since each char is used for once at most.
        
        
        //dp matrix that indicates words with len that ends at elements[rows][cols]
        boolean [][][] dp = new boolean[len][rows][cols];
       
        
        //build base for word of len == 1.
        for(int i=0; i < rows; i++){
            for(int j=0; j< cols; j++){
                if(board[i][j] == word.charAt(0)){
                    dp[0][i][j] = true;
                }
            }    
        }
        
        boolean res = false;
        char curChar;
        for(int k = 2; k<=len; k++){
            curChar = word.charAt(k-1);
            
            for(int i=0; i< rows; i++){
                for(int j=0; j<cols; j++){
                    res = false;
                    if(i -1 >=0){
                        res = res || (dp[k-2][i-1][j] && (board[i][j] == curChar));
                    }
                    if(i +1 <rows){
                        res = res || (dp[k-2][i+1][j] && (board[i][j] == curChar));
                    }
                    if(j -1 >=0){
                        res = res || (dp[k-2][i][j-1] && (board[i][j] == curChar));
                    }
                    if(j +1 < cols){
                        res = res || (dp[k-2][i][j+1] && (board[i][j] == curChar));
                    }
                    dp[k-1][i][j] = res;
                }
            }
            
        }
        
        for(int i=0; i < rows; i++){
            for(int j=0; j< cols; j++){
                if(dp[len-1][i][j] ==true) return true;
            }    
        }
        return false;
    }
	
	
	/**
	 * this works but fails the speed test.
	 * 4 power of k * m * n complexity.
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist1(char[][] board, String word) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        
        //use backtrack
        if(board == null) return false;
        if(board.length == 0 || board[0].length == 0) return false;
        
        int rows = board.length;
        int cols = board[0].length;
        
        
        for(int i=0; i< rows; i++){
            for(int j=0; j<cols; j++){
                boolean[][] mask = new boolean[rows][cols];
                if(search(board, word, 0, mask, i, j)) return true;                  
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, String word, int index, boolean[][] mask,  int i, int j){
         
        
         if(board[i][j] == word.charAt(index) && !mask[i][j]){
            if(index == word.length()-1) return true; 
            else{ //continue to search
                mask[i][j] = true; //mark visited.
                int rows = board.length;
                int cols = board[0].length;
                index ++;
                if(i-1 >= 0)
                    if(search(board, word, index, mask , i-1, j)) return true;
                if(i+1 < rows)
                    if(search(board, word, index, mask, i+1, j)) return true;
                if(j-1 >=0)
                    if(search(board, word, index, mask , i, j-1)) return true;
                if(j+1 < cols)
                    if(search(board, word, index, mask , i, j+1)) return true;
                mask[i][j] = false; //reverse mask flag so it can be reused.   
                return false;
            }
        }
        return false;
        
    }
    
    
    public boolean exist2(char[][] board, String word) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        
        //use backtrack
        if(board == null) return false;
        if(board.length == 0 || board[0].length == 0) return false;
        
        int rows = board.length;
        int cols = board[0].length;
        
        
        for(int i=0; i< rows; i++){
            for(int j=0; j<cols; j++){
                if(search2(board, word, 0, i, j)) return true;                  
            }
        }
        return false;
    }
    
    /**
     * this version modify existing board number and recover it later 
     * to avoid using mask.
     * 
     * @param board
     * @param word
     * @param index
     * @param i
     * @param j
     * @return
     */
    private boolean search2(char[][] board, String word, int index, int i, int j){
         
        
         if(board[i][j] == word.charAt(index)){
            if(index == word.length()-1) return true; 
            else{ //continue to search
                char rem = board[i][j];
                board[i][j] = '#'; //mark visited.
                int rows = board.length;
                int cols = board[0].length;
                index ++;
                if(i-1 >= 0)
                    if(search2(board, word, index, i-1, j)) return true;
                if(i+1 < rows)
                    if(search2(board, word, index, i+1, j)) return true;
                if(j-1 >=0)
                    if(search2(board, word, index, i, j-1)) return true;
                if(j+1 < cols)
                    if(search2(board, word, index, i, j+1)) return true;
                board[i][j] = rem; //reverse mask flag so it can be reused.   
                return false;
            }
        }
        return false;
        
    }
}
