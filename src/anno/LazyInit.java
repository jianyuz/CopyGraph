package anno;

public class LazyInit {
	private volatile MyField field;
	
	//tmp variable ensure feidl is read only once when it is initialized.
	public MyField getField(){
		MyField result = field;
		if(result == null){
			synchronized(this){
				result = field;
				if(result == null){
					field = result = new MyField();
				}
			}
		}
		return result;
	}
	
}

class MyField{
	
}
