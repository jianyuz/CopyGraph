package puzzle;

import java.util.HashSet;
import java.util.Set;

public class Taxi {

	
	private Point location, destination;
	private final Dispatcher dispatcher = new Dispatcher();
	public synchronized Point getLocation(){
		return location;
	}
	
	public void updateLocation(Point p){
		boolean rechDest = false;
		synchronized(this){
			this.location = p;
			if(location.equals(destination)){
				rechDest = true;
			}
		}
		
		if(rechDest) dispatcher.notifyAvailable(this);
	}
}

class Dispatcher{
	private Set<Taxi> taxis = new HashSet<Taxi>();
	private Set<Taxi> availTaxis = new HashSet<Taxi>();
	
	public synchronized void notifyAvailable(Taxi taxi){
		availTaxis.add(taxi);
	}
	
	public Image getImage(){
		Set<Taxi> copy;
		synchronized(this){
			copy = new HashSet<Taxi>(taxis);
		}
		
		Image image = new Image();
		for(Taxi taxi : copy){
			image.drawMark(taxi.getLocation());
		}
		return image;
	}

	
}

class Point{
	
}

class Image{
	public void drawMark(Point p){
		
	}
}