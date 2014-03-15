package com.z2.chess;

public class HumanPlayer extends Player{

	public HumanPlayer(int side){
		super.setSide(side);
	}
	
	
	@Override
	public Move getMove(Board board) {
		//receive move from the click event.
		//click position translate to piece clicked and source locatoin
		//then next click postion translate to destnation
		Move m = new Move();
		
		if(board.getCurrentPlayer() == Player.SIDE_WHITE){
			m.movingPiece = board.findWhitePiece(m.sourceSquare);
			if(m.movingPiece == board.Empty_Square){
				//print error.
			}
			
			if(board.findWhitePiece(m.destSquare) != board.Empty_Square)
			{
				//can't capture own.
			}
			
			m.capturedPiece = board.findBlackPiece(m.destSquare);
			if(m.capturedPiece != board.Empty_Square){
				m.moveType = Move.MOVE_CAP_ORD;
			}else{
				m.moveType = Move.MOVE_NORMAL;
			}
					
		}
		return m;
	}
	
	

}
