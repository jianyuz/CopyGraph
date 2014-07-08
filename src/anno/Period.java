package anno;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Period implements Serializable{
	private static final long serialVersionUID = 1;
	
	private final Date start;
	private final Date end;
	
	public Period(Date start, Date end){
		this.start = start;
		this.end = end;
	}
	
	private static class SerializationProxy implements Serializable{
		private final Date start;
		private final Date end;
		SerializationProxy(Period p){
			this.start = p.start;
			this.end = p.end;
		}
		private static final long serialVersionUID = 1;
		private Object readResolve(){
			return new Period(start, end);
		}
	}
	
	private Object writeReplace(){
		return new SerializationProxy(this);
	}
	
	private void readObject(ObjectInputStream s) throws InvalidObjectException{
		throw new InvalidObjectException("invalid");
	}
	
	public static void main(String[] args){
		Period p = new Period(new Date(), new Date());
		FileOutputStream fileOut  = null;
		try
	      {
	         fileOut =
	         new FileOutputStream("/tmp/peroid.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(p);
	         out.close();
	    
	         System.out.printf("Serialized data is saved in /tmp/period.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }finally{
	    	 try {
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	      }
		
	}
}
