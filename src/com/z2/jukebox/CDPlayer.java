package com.z2.jukebox;

public class CDPlayer {
	private CD cd;
	private PlayList pl;
	private boolean isPlaying;
	public CDPlayer(CD c){
		this.cd = c;
		isPlaying = false;
	}
	
	public PlayList getPlayList(){
		return pl;
	}
	
	public void setPlayList(PlayList p){
		 this.pl = p;
	}
	
	public CD getCD(){
		return cd;
	}
	
	public void setCD(CD cd){
		this.cd = cd;
	}
	
	public boolean isPlaying(){
		return this.isPlaying;
	}
	
	public void play(){
		isPlaying = true;
		while(pl.isEmpty()){
			Song s = pl.removeNextToPlay();
			//sent to speaker.
			
		}
		isPlaying = false;
	}
	
	public void stop(){
		isPlaying = false;
	}
	public void play(Song s){
		//sent to speaker.
	}

}
