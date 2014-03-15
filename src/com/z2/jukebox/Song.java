package com.z2.jukebox;

public class Song {
	private String songName;
	private String singerBand;
	private byte[] contents;
	
	
	public Song(String songName, String singerBand) {
		super();
		this.songName = songName;
		this.singerBand = singerBand;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSingerBand() {
		return singerBand;
	}
	public void setSingerBand(String singerBand) {
		this.singerBand = singerBand;
	}
	
	

}
