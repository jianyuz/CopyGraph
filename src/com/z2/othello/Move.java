package com.z2.othello;

public class Move {
	private int x, y;
	private int player;
	
	public Move(int x, int y, int player){
		this.x = x;
		this.y =y;
		this.player = player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	

}
