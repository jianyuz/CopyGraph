package com.z2.chess;

public class Move {
	
	//types of moves
	public static final int MOVE_NORMAL =0;
	public static final int MOVE_CAP_ORD = 1;
	public static final int MOVE_CAPTURE_EN_PASSANT = 2;
	  public static final int MOVE_CASTLING_KINGSIDE = 4;
	  public static final int MOVE_CASTLING_QUEENSIDE = 8;
	  public static final int MOVE_RESIGN = 16;
	  public static final int MOVE_STALEMATE = 17;
	  public static final int MOVE_PROMOTION_KNIGHT = 32;
	  public static final int MOVE_PROMOTION_BISHOP = 64;
	  public static final int MOVE_PROMOTION_ROOK = 128;
	  public static final int MOVE_PROMOTION_QUEEN = 256;
	  
	  public int movingPiece;
	  //constants defined by board.
	  
	  public int capturedPiece;
	  
	  public int sourceSquare, destSquare;
	  public int moveType;
	  
	  public Move()
	  {
		  
	  }
}
