package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * indiate the method was annotated is for testing.
 * @author zhouzhou
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {

	/** method must throw an exception or its subclass to pass the est */
	Class<? extends Exception>value();
}
