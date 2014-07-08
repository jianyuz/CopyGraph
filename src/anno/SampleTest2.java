package anno;


public class SampleTest2 {

	@ExceptionTest(ArithmeticException.class)
	public static void test1(){
		int a = 1 /0;
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void test2(){
		int[] a = new int[1];
		int b = a[1];
	}
	
}
