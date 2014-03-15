package com.z2.jukebox;

import java.util.Queue;

public class PlayList {
	
	private String name;
	private Queue<Song > queue;
	
	public PlayList(String name, Queue<Song> q){
		this.name = name;
		this.queue = q;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addToPlayList(Song s){
		queue.offer(s);
	}
	
	public Song getNextToPlay(){
		return queue.peek();
	}
	
	public Song removeNextToPlay(){
		return queue.remove();
	}
	
	public boolean isEmpty(){
		return queue.size() == 0;
	}

}
