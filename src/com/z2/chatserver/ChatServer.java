package com.z2.chatserver;

/**
 * message acknowledgement
 * lost of message
 * online state
 * scalability of chat server
 * dividing user among chat server.
 * communicating between chat server.
 * in memory data grid coherance.
 * 
 * hold message in queue when user is offline.
 * 
 * XMPP? Erlang Jabber
 * 
 * @author zhouzhou
 *
 */
public class ChatServer {

	User[] signedInUser;
	
	ChatSession[] sessions;
	
	void login(){
		//send he user contact list and their status.
		//keep a conneciton to user.
		acceptUserConnection();
	}
	
	void logout(){
		//terminate pinging thead.
		closeUserConnection();
	}
	
	ChatSession startChatSession(User user){
		return null;
	}
	
	void addUserToChatSession(User user, ChatSession session){
		
	}
	
	boolean closeChatSession(ChatSession session){
		return true;
	}
	
	private void acceptUserConnection(){
		
	}
	
	private void closeUserConnection(){
		
	}
	
	
}
