package com.z2.othello;

public class Board {
	
	private int[][] board;
	private static final int EMPTY = 0;
	private static final int WHITE = 1;
	private static final int BLACK = 2;
	private Score score;
	public Board(){
		board = new int[10][10];
		
		for(int i=0; i< 10; i++){
			for(int j=0; j< 10; j++){
				board[i][j] = EMPTY; 
			}
		}
		//set score 2 2 each.
		
		board[4][4] = WHITE;
		board[5][5] = WHITE;
		board[5][4] = BLACK;
		board[4][5] = BLACK;
	}
	
	public int getSquareColor(int x, int y){
		return board[x][y];
	}
	
	public boolean moveIsLegal(Move m){
		if(board[m.getX()][m.getY()] != EMPTY) return false;

	    int player = m.getPlayer();
	    int opponent = getOpponent(player);

		for (int xinc = -1; xinc <= 1; xinc++){//use the offset as base
			for (int yinc = -1; yinc <= 1; yinc++){
				if (xinc != 0 || yinc != 0) {
					int x, y;

					for (x = m.getX() + xinc, y = m.getY() + yinc; //start to fan out.
							board[x][y] == opponent; x += xinc, y += yinc)
						;
					//find another own piece. and piece next to it is not own
					if (board[x][y] == player 
							&& (x - xinc != m.getX() || y - yinc != m.getY()))
						return true;
				}
			}
		}

	    return false;
	}
	
	public void updateBoard(Move m){
		int player = m.getPlayer();
		int opponent = getOpponent(player);
		board[m.getX()][m.getY()] = player;
		
		for (int xinc=-1; xinc<=1; xinc++)
		    for (int yinc=-1; yinc<=1; yinc++)
		    if (xinc != 0 || yinc != 0)
		    {
		      int x, y;

		      for (x = m.getX()+xinc, y = m.getY()+yinc; board[x][y] == opponent;
			   x += xinc, y += yinc)
			;//fan out

					if (board[x][y] == player)//update all the way to start position.
						for (x -= xinc, y -= yinc; x != m.getX()
								|| y != m.getY(); x -= xinc, y -= yinc) {
							board[x][y] = player;
							score.addScore(player, 1);
							score.addScore(player, -1);
						}
		    }

	}

	private int getOpponent(int player) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean moveIsPossible(int player){
		//check all possible move to see if they are leagl.
		return false;
	}
	
	public boolean moveIsPossibleForAll(){
		//check if black or white got legal moves.
		return false;
	}

}
