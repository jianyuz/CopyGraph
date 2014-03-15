package com.z2.bookreader;

import java.util.Set;

public enum Users {
	INSTANCE;
	
	private static Set<User> users;
	
	public User find(long ID){
		return null;
	}
	
	public void addUser(long id, String name, int type){
		users.add(new User(id, name, type));
	}
	
}
