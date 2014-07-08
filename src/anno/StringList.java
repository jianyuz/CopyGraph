package anno;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class StringList implements Serializable{

	static final long serialVersionUID = 1;
	private transient int size;
	private transient Entry head;
	
	static class Entry{
		String data;
		Entry next;
		Entry previous;
	}
	
	public final void add(String s){
		
	}
	
	public void writeObject(ObjectOutputStream s) throws IOException{
		s.defaultWriteObject();
		s.writeInt(size);
		for(Entry e = head; e!= null ; e= e.next){
			s.writeObject(e.data);
		}
	}
	
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
		s.defaultReadObject();
		this.size = s.readInt();
		for(int i=0; i< size; i++){
			add((String) s.readObject());
		}
	}
}
