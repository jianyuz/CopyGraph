package anno;

import java.util.ArrayList;
import java.util.List;

public class SampleTest3 {

	@ExceptionTest1({NullPointerException.class, IndexOutOfBoundsException.class})//array values
	public static void doubleBad(){
		List<String> list = new ArrayList<String>();
		list.addAll(5, null);
	}
	
	@ExceptionTest1({NullPointerException.class, IndexOutOfBoundsException.class})//array values
	public static void doubleBad1(){
		List<String> list = new ArrayList<String>();
	}
}
