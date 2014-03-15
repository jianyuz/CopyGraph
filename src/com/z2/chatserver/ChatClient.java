package com.z2.chatserver;

public class ChatClient {
	private User user;
	private User[] contatct_list;
	
	
	boolean signIn(Credentials c){
		return true;
	}
	
	boolean signOut(){
		return true;
	}
	
	void updateUserStatus(Status s){
		
	}
	
	void addUserToContactList(User user){
		
	}
	
	void removeUserToContactList(User user){
		
	}
	
	ChatSession startChatSession(User user){
		return null;
	}
	
	boolean addUserToChatSession(ChatSession session, User user){
		return true;
	}
	
	boolean removeUserFromChatSession(ChatSession session, User user){
		return true;
	}
	
	public void run(){
		//runnable interface start thread to receive message from server
		
	}
	
	public boolean handleEvent(){
		//handle event to send message.
		return true;
	}

}
