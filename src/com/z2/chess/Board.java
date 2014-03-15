package com.z2.chess;

public class Board {

	public static final int PAWN = 0;
	public static final int KNIGHT = 2;
	public static final int BISHOP = 4;
	public static final int ROOK = 6;
	public static final int QUEEN = 8;
	public static final int KING = 10;
	
	public static final int SQUARES = 64;
	public static final int PIECES = 12;
	
	public static final int BLACK_KING = KING + Player.SIDE_BLACK;
	
	public static long squareBits[];
			
	static
	{
		squareBits = new long[SQUARES];
		for( int i = 0; i < SQUARES; i++ ){
	  
	      // Note: the 1L specifies that the 1 we are shifting is a long int
	      // Java would, by default, make it a 4-byte int and be unable to
	      // shift the 1 to bits 32 to 63
	      squareBits[ i ] = ( 1L << i );
	    }

	}
	
   //each of them flags for the square where to find the type of piece.
	private long BitBoards[];
	
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	//find if a black piece reside at the square.
	public int findBlackPiece(int square){
		if((BitBoards[BLACK_KING] & squareBits[square]) != 0)
			return BLACK_KING;
		return Empty_Square;
	}
	//empty square is 0.
	//occupied square is 1.
	//minmax search root of all game programming.
	//bitboard commonly used in chess programming
	//hold massive number of positions in memory 
	/**
	 * analyze positions wit few CPU instructions.
	 * answer questions about the game state using one logical operation
	 * stuff related boolean variables into one machine word.
	 * bitboard larger than the width of instruction set, 
	 * the instructions may take more than one cycles to finish.
	 * performance hit.
	 * some queries take longer time cmopared to arrays.
	 * 12 types of pieces each got their ownebitboard.
	 * additional bitboards to keep track of attack positions etc.
	 * rotated bitboards.
	 * magics hashfunctionright shift
	 * a or b conditional replacemane
	 * a ^ b ^ var
	 * all the numbers in java is singed two's complement numbers
	 * leftmost bit is the sign bit.
	 * change between x and -x ~x + 1
	 * think 1000000 -128 start from smallest then the biggest -1
	 * 11111111 -1
	 * bit flag stuff options into one word
	 * 1<<5 = 32
	 * all 0 bit
	 * y = 0;
	 * all 1 bit;
	 * y = -1;
	 * all 0 except the right most.
	 * y = 1;
	 * all 0 except the sign bit.
	 * int y = Integer.MIN_VALUE
	 * rightmost one bit x & -x
	 * standard board array of 64 with enum of constant
	 * to represent 12 pieces plus one empty space.
	 * 
	 * bitboard
	 * 12 64 bit bitsets
	 * each for different kind of pieces.
	 * a-H file
	 * 1 to 8 rank vertically.
	 * 
	 */
	 
	static long W_PAWN, W_BISHIP, W_ROOK, W_KNIGHT, W_KING, W_QUEEN,
	     B_PAWN, B_BISHIP, B_ROOK, B_KNIGHT, B_KING, B_QUEEN;
	
	long not_empty = ~(W_PAWN| W_BISHIP| W_ROOK| W_KNIGHT| W_KING|
			W_QUEEN|
		     B_PAWN| B_BISHIP| B_ROOK| 
		     B_KNIGHT| B_KING| B_QUEEN);
	private int currentPlayer;
	
	/**
	 * determine the attack position of a piece.
	 */
	static long attackKingB2 = 1L | 1L <<1 | 1L << 2 |
			1L << 8 | 1L<<10|
			1L << 16 | 1L << 17 | 1L<<18;
	
	static long shifted_left = attackKingB2 >>> 1;
	
	final static long FILE_H=
			1L << 7       |  (1L<<15) | (1L<<23) | (1L<<31) |
			(1L<<39) | (1L<<47) | (1L<<55) | (1L<<63);
	public static final int Empty_Square = 12;
	
	/**
	 * shifted left
	 */
	static long attackKingA2 = (attackKingB2 >>> 1) & (~FILE_H);
	
	/**
	 * shifted down
	 */
	static long attackKingB1 = attackKingB2 >>> 8;//only unsigned shift for right shift.
	/**
	 * shifted up
	 */
	static long attackKingB3 = attackKingB2 << 8;
	/**
	 * shifted right
	 * @param args
	 */
	static long attackKingC2 = (attackKingB2 << 1);
	
	static long FILE_A = 1L | 1L<<8 | 1L << 16 | 1L << 24| 1L<<32 | 1L << 40 | 1L << 48
			| 1L << 56 ;
	/**
	 * parallel bit operation.
	 */
	static long pawnAttcks =( W_PAWN << 7 & ~FILE_A) & ((W_PAWN >> 9) & ~FILE_H);
	
	/**
	 * 3d tic tac toe
	 * using 32 bit set only use 27.
	 * 
	 * @param args
	 */
	
	/**
	 * life game
	 * original layer
	 * 8 layers for the neighbors
	 * get the sum layer from 0 to 8 
	 * recursively compute
	 * 
	 * S0 = ~(L1 | L2);
	 * S1 = (L1 | L2);
	 * S2 = L1 & L2;
	 * 
	 * S3 = S2 & L3;
	 * S2 = (S2 & (~L3) ) | (S1 & L3);
	 * S1 = (S1 & (~L3)) | (S0 & L3);
	 * S0 = S0 & ~L3;
	 * @param args
	 */
	
	/**
	 * transpostion table.
	 * different move to same resutl
	 * 12 * 64 random number xor to get hashkey
	 * preprocessing possible move for each piece each position.
	 * earch ray until to the end.
	 * enemy piece, capture
	 * otherwise, impossible move.
	 * how to select from all possible moves.
	 * search all possible moves , rank them. see if any lead to cutoff
	 * select a few important move, hope it reach cutoff.
	 * generate attack bitboard for a piece.
	 * genearte the position of the pieces that the attacked queen 
	 * deep search.
	 * minmax evaluation of the move between two players.
	 * one takes advantage over another.
	 * branching factor b
	 * search depth N-ply 
	 * b ^ N complexity.
	 * if some moves combo better than others. prune them
	 * alpha beta.
	 * evaluate possible move, from tranposition table.
	 * check for captures.
	 * killer moves.
	 * history tabel.
	 * order the search results before search deeper.
	 * iterative deepening alhpabeta
	 * computer can't detect trap beyond search depth.
	 * play conservatively
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(Long.toBinaryString(attackKingB2));
		System.out.println(Long.bitCount(attackKingB2));
		System.out.println(Long.toBinaryString(shifted_left));
		System.out.println(Long.toBinaryString(~FILE_H));
		System.out.println(Long.toBinaryString(attackKingA2));
		System.out.println(Long.toBinaryString(attackKingC2));
		System.out.println(Long.toBinaryString(attackKingB1));
		System.out.println(Long.toBinaryString(attackKingB3));
	}

	public int findWhitePiece(int sourceSquare) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//type of pieces plus?
	public static final int ALL_BitBoards = 14;
	private static final int BLACK_ROOK = 0;
	private static final int WHITE_ROOK = 0;
	
	public Board(){
			BitBoards = new long[ALL_BitBoards];
			startingboard();
	}

	void startingboard() {
		emptyBoard();
		addPiece(0, BLACK_ROOK);
		///....
		addPiece(63, WHITE_ROOK);
		setCurrentPlayer(Player.SIDE_WHITE);
		
	}
	
	private void setCurrentPlayer(int sideWhite) {
		// TODO Auto-generated method stub
		
	}

	private void emptyBoard() {

		for(int i = 0; i< this.ALL_BitBoards; i++){
			BitBoards [i] = 0;
		}
	}

	public boolean display(){
		return true;
	}
	
	public int switchSide(){
		//toggle player
		return this.currentPlayer;
	}
	
	//update the board state.
	public boolean applyMove(Move m){
		if(m.moveType == Move.MOVE_NORMAL){
			removePiece(m.sourceSquare, m.movingPiece);
			addPiece(m.destSquare, m.movingPiece);
		}
		
		if(m.moveType == Move.MOVE_CAP_ORD){
			removePiece(m.sourceSquare, m.movingPiece);
			removePiece(m.sourceSquare, m.capturedPiece);
			addPiece(m.destSquare, m.movingPiece);
		}
		return true;
	}

	private void removePiece(int square, int piece) {
		BitBoards[piece] ^= squareBits[square];
	}

	private void addPiece(int square, int piece) {
		BitBoards[piece] |= this.squareBits[square];
	}

	public void startingBoard() {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
