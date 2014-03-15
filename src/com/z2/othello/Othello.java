package com.z2.othello;

public class Othello implements Runnable{
	private Board board;
	
	public Othello(){
		board = new Board();
	}
	
	public void run(){
		while(board.moveIsPossibleForAll()){
			/*
			 * getTurn
			 * get move (player);
			 * moveisPossible
			 * updateboard
			 * 
			 */
		}
		//print score.
		
	}
	

}
