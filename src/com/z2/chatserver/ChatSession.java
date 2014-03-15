package com.z2.chatserver;

import java.util.List;
import java.util.UUID;

public class ChatSession {
	private UUID id;

	private User thisUser;
	private List<Message> msgList;
	private User[] users;
	
	void sendJoinRequest(User user){
		//get connection to server.
	}
	
	void destroySession(){
		//when last user close their connectoin to the session.
	}
	
}
