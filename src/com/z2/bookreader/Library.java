package com.z2.bookreader;

import java.util.HashSet;
import java.util.Set;

public enum Library {
	INSTANCE;
	//singleton
	private static Set<Book> books = new HashSet<Book>();
	
	public void addBook(long id, String content){
		books.add(new Book(id, content));
	}
	
	public void delete(Book b){
		books.remove(b);
	}
	
	public Book find(long id){
		for(Book b: books){
			if(b.getID() == id){
				return b;
			}
		}
		return null;
	}

}
