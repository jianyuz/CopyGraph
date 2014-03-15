package com.z2.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * lazy init.
 * eager init.
 * thread safe
 * serialization
 * beat reflection
 * above are considerations
 * 
 * 
 * using enum to support singleton in jdk1.5 and above.
 * provide serialization free.
 * no worry several instances are created after deserialization
 * 
 * @author zhouzhou
 *
 */
public enum MySingleton {
	instance;
	private List<String> contentList = new ArrayList<String>();
	
	private MySingleton(){
		contentList.add("Item1");
	}
	
	public List<String> getContent(){
		return contentList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(MySingleton.instance.getContent());
		
		MySingleton instance2 = MySingleton.instance;
		instance.getContent().add("item3");
		System.out.println(instance.getContent());
		instance2.getContent().add("Item2");
		
		System.out.println(MySingleton.instance.getContent());
	}

}
