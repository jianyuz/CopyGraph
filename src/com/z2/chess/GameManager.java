package com.z2.chess;

/**
 * evaluate next steps.
 * capture, promotion or check is always good
 * minmax strategy.
 * search depth of computer.
 * predefine the search ply
 * then select those nonquiet position
 * continue to search quiet position 
 * then start to evaluate.
 * 
 * nullmove could save search
 * 
 * material balance calculation
 * value of piece and number of piece
 * sum them
 * 
 * @author zhouzhou
 *
 */
public class GameManager {

	Player[] players;
	Board board;
	
	public GameManager(){
		initGame();
	}

	private void initGame() {
		players = new Player[2];
		board = new Board();
		board.startingboard();
	}
	
	public boolean runGame() throws InterruptedException{
		Player currentPlayer;
		Move m;
		do{
			currentPlayer = players[board.getCurrentPlayer()];
			m = currentPlayer.getMove(board);
			
			board.applyMove(m);
			Thread.currentThread().sleep(2000);
			
		}while(m.moveType != m.MOVE_RESIGN
				&& m.moveType != m.MOVE_STALEMATE);
		return true;
	}
	
	
}
