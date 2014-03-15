package com.z2.chatserver;

import java.util.Date;
import java.util.UUID;

public class Message {
	private UUID msgID;
	private UUID sessionID;
	private User fromUser;
	private User[] toUser;
	private Date timestamp;
	//message action. goodby login
	
	private String msgContent;
	

}
