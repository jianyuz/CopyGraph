package anno;

public class Unbelievable {
	static Integer i;

	public static void main(String[] args) {
		if (i == 42)//i auto unboxed and generate nullpointer exception.
			System.out.println("Unbelievable");
	}
}