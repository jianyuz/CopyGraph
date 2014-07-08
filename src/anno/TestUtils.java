package anno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestUtils {

	public static void main(String[] args){
		try {
		Class<?> testClass = Class.forName(args[0]);
			Method[] methods = testClass.getDeclaredMethods();
			int pCount = 0;
			int fCount = 0;
			for(Method m: methods){
				if(m.isAnnotationPresent(ExceptionTest.class)){
					boolean failed= true;
					try{
						m.invoke(null);
					}catch(InvocationTargetException e){
						Throwable t = e.getCause();
						 ExceptionTest a = m.getAnnotation(ExceptionTest.class);
						if(a != null){
							Class<? extends Exception> v = a.value();
							if(v.isInstance(t)){
								pCount ++;
								failed = false;
							}
						}
					}catch(Exception e){
					}
					if(!failed){
						System.out.println(m + " succeeded!");
					}else{
						fCount ++;
					}
				}
				if(m.isAnnotationPresent(ExceptionTest1.class)){
					boolean failed= true;
					try{
						m.invoke(null);
					}catch(InvocationTargetException e){
						Throwable t = e.getCause();
						 ExceptionTest1 a = m.getAnnotation(ExceptionTest1.class);
						if(a != null){
							Class<? extends Exception>[] v = a.value();
							for(Class<? extends Exception> value : v){
								if(value.isInstance(t)){
									pCount ++;
									failed = false;
									break;
								}
							}
						}
					}catch(Exception e){
					}
					if(!failed){
						System.out.println(m + " succeeded!");
					}else{
						fCount ++;
					}
				}
				if(m.isAnnotationPresent(Test.class)){
					boolean failed = false;
					try {
						m.invoke(null);
					} 
					catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						Throwable throwable = e.getCause();
						System.out.println(m + " failed with cause " + throwable);
						failed = true;
						fCount ++;
						
					}catch(Exception e){
						System.out.println("Invalid test " + m);
						SampleTest st = new SampleTest();
						try {
							m.invoke(st);
							System.out.println("invoke instance method " + m);
						} catch (IllegalArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						failed = true;
						fCount++;
					}
					if(!failed){
						System.out.println(m + " succeeded");
						pCount ++;
					}
				}
			}
			System.out.println(pCount + " passed " + fCount + " failed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
