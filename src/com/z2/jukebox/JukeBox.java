package com.z2.jukebox;

import java.util.List;

public class JukeBox {
	
	private CDPlayer cdPlayer;
	private User user;
	private List<CD> cdSelection;
	
	public JukeBox(CDPlayer cp, List<CD> selection){
		this.cdSelection = selection;
		this.cdPlayer = cp;
		
	}
	
	public String displayCDContent(int index){
		return cdSelection.get(index).toString();
	}
	
	public void addSelection(int cdIndex, int songIndex){
		Song s = cdSelection.get(cdIndex).getSongByIndex(songIndex);
		cdPlayer.getPlayList().addToPlayList(s);
		if(!cdPlayer.isPlaying()){
			cdPlayer.play();
		}
	}

}
