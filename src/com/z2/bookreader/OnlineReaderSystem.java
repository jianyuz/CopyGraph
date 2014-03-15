package com.z2.bookreader;

public class OnlineReaderSystem {
	
	public User login(Credential c){
		return null;
	}
	
	public void logout(User user){
		
	}
	
	public User register(String name, int accountType){
		int id = 0;
		Users.INSTANCE.addUser(id, name, accountType);
		return null;
	}
	
	public Book searchBook(long id){
		return Library.INSTANCE.find(id);
	}
	
	public void display(Book b ){
		
	}

}
