package anno;

//note annotated will be ignored by testing tools.
public class SampleTest {

	@Test
	public static void testCase1(){
		
	}
	
	public static void testCase2(){
		
	}
	
	@Test public static void testCase3(){
		throw new RuntimeException("error");
	}
	
	public static void m4() { }
	
	@Test public void m5() { } // INVALID USE: nonstatic method
	
	public static void m6() { }
	
	@Test public static void m7() { // Test should fail
	throw new RuntimeException("Crash");
	}
	
	public static void m8() { }
}
