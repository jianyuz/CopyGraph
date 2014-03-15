
public class TicTacToe {

	static int[] boardConfig = new int[(int)Math.pow(4,  9)];
	public static void main(String[] args){
		initWinningPattern();
	}
	
	public static void initWinningPattern(){
		//use 32 bit word to represent the board.
		//each position in board is represented in 2 bit.
		//00, 01, 11 
		//00 represent no one has taken a step for the board postion.
		//01 taken by O 
		//11 taken by 1
		
		//by default boardConfig is empty all int are zeroes.
		//initialize the winning pattern.
		//win by O
		//8 ways, horizontally or vertically plus diagonally.
		//win by x
		//8 ways, ... in total 16 ways.
		int xRow1 = Integer.valueOf("010101", 2);
		int xRow2 = xRow1 << 6;
		int xRow3 = xRow1 << 12;
		int xCol1 = Integer.valueOf("01000001000001", 2);
		int xCol2 = xCol1 << 2;
		int xCol3 = xCol2 << 4;
		int xDiag1 = Integer.valueOf("010000000100000001", 2);
		int xDiag2 = Integer.valueOf("01000100010000", 2);
		int oRow1 = Integer.valueOf("101010", 2);
		int oRow2 = oRow1 << 6;
		int oRow3 = oRow1 << 12;
		
		int n = boardConfig.length;
		for(int i=0; i< n; i++){
			if( (i & xRow1) == xRow1 || (i & xRow2) == xRow2 || (i & xRow3) == xRow3
					|| (i & xCol1) == xCol1 || (i & xCol2) == xCol2 || (i & xCol3) == xCol3
					|| (i & xDiag1) == xDiag1 || (i & xDiag2) == xDiag2){
				boardConfig[i] = 1;
			}else if((i & oRow1) == oRow1 || (i & oRow2) == oRow2 || (i & oRow3) == oRow3){
				boardConfig[i] = 2;
			}
		}
		
		//how to translate the position into the board pos int.
		//   x x o
		//     x
		//   o o x
		int cur = 0;
		cur = cur | Integer.valueOf("01", 2);//fill in 1
		cur = cur | (Integer.valueOf("10", 2) << 4);//fill in 3
		cur = cur | (Integer.valueOf("01", 2) << 2); //fill in 2;
		cur = cur | (Integer.valueOf("10", 2) << 12); // fill in 7
		cur = cur | (Integer.valueOf("01", 2) << 8); // fill in 5
		cur = cur | (Integer.valueOf("10", 2) << 14);// fill in 8
		cur = cur | (Integer.valueOf("01", 2) << 16); //fill in 9
		
		System.out.println("board pos " + Integer.toBinaryString(cur));
		System.out.println("winner is "  + boardConfig[cur]);
	}
}
