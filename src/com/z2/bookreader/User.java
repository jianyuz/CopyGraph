package com.z2.bookreader;

public class User {
	private long ID;
	private String name;
	private int accountType;
	private Credential credential;
	
	public User(long id2, String name2, int type) {

	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	public Credential getCredential(){
		return this.credential;
	}
	

}
