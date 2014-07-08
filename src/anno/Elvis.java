package anno;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Elvis implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Elvis instance = new Elvis();
	
	private Elvis(){
		
	}
	
	private Object readResolve() throws ObjectStreamException{
		return instance;
		
	}
	
}
