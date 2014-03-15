package com.z2.chess;

public class ComputerPlayer extends Player{

	private SearchAgent agent;
	
	public ComputerPlayer(int which){
		agent = new SearchAgent();
	}
	@Override
	public Move getMove(Board board) {
		// TODO Auto-generated method stub
		return agent.pickBestMove(board);
	}

}
