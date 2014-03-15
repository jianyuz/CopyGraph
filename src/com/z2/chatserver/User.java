package com.z2.chatserver;

import java.util.UUID;

import javax.net.ssl.SSLEngineResult.Status;

public class User {
	private UUID id;
	private String displayname;
	private Status status;
	private UserImage image;
	private UserInfo info;
	
	void sendMessage(Message msg){
		
	}
	
	void receiveMesage(Message msg){
		
	}
	
	void approveChatRequest(User user){
		
	}
	
	void denyRequest(User user){
		
	}
}
