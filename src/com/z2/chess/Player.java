package com.z2.chess;

/**
 * a player base for all type of players.
 * computer player and human players.
 * @author zhouzhou
 *
 */
abstract public class Player {

	public static final int SIDE_BLACK = 1;
	public static final int SIDE_WHITE = 0;
	
	public static final String PlayerStrings[] = { "WHITE", "BLACK" };

	private int Side; //which side this player belongs to.
	
	public Player(){
		
	}
	
	public int getSide(){
		return this.Side;
	}
	
	public void setSide(int s){
		this.Side =s ;
	}
	
	/**
	 * get next move give board situation.
	 * @param board
	 * @return
	 */
	public abstract Move getMove(Board board);
	
}
