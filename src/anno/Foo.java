package anno;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Foo extends AbstractFoo implements Serializable{
	private static final long serialVersionUID = 1;
	
	public Foo(int x, int y){
		super(x,  y);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
		s.defaultReadObject();
		int x = s.readInt();
		int y = s.readInt();
		this.initialize(x, y);
	}
	
	private void writeObject(ObjectOutputStream s)
			throws IOException {
			s.defaultWriteObject();
			// Manually serialize superclass state
			s.writeInt(getX());
			s.writeInt(getY());
	}
}
