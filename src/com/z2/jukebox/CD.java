package com.z2.jukebox;

public final class CD {
	private final Song[] songs;

	public CD(Song[] songs){
		this.songs = songs;
	}
	
	public Song getSongByName(int name){
		for(int i=0; i< songs.length; i++){
			if(songs[i].getSongName().equals(name)){
				return songs[i];
			}
		}
		return null;
	}
	
	public Song getSongByIndex(int index){
		return songs[index];
	}
	public int getTotalCount(){
		return songs.length;
	}
}
