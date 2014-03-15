package com.z2.callhandler;

public class Employee {

	private int rank;
	private String name;
	private boolean free;
	
	public Employee(String name, int rank){
		this.name = name;
		this.rank = rank;
		this.free = true;
	}
	
	public void receiveCall(Call c){
		c.receive();
		c.reply("hello");
		this.free = false;
	}
	
	public void callHandled(Call c){
		c.reply("bye");
		this.free = true;
	}
	
	public void promoteCall(CallManager manager, Call c){
		c.increaseRank();
		manager.dispatchCall(c);
		this.free = true;
	}
	
	public boolean isFree(){
		return this.free;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
