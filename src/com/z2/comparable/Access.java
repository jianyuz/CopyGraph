package com.z2.comparable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * how to expose mutable public static final 
 * field.
 * @author zhouzhou
 *
 */
public class Access {

	private static final Integer[] internalArray ={1, 2};
	public static final List<Integer> values =
			Collections.unmodifiableList(
					Arrays.asList(internalArray));
	
	private static final Integer[] internalArray1 = {3,4};
	
	public static final Integer[] values(){
		return internalArray1.clone();
	}
	
}
