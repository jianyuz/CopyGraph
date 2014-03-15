package com.z2.container;

import java.util.HashMap;
import java.util.Map;

/**
 * class is parameterized.
 * hetereogenous container.
 * parameterize the key.
 * @author zhouzhou
 *
 */
public class ContainerMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ContainerMap container = new ContainerMap();
		container.putFavorite(String.class, "My favorite");
		container.putFavorite(Integer.class, 1);
		System.out.printf("%s %d", container.getFavorite(String.class),
				container.getFavorite(Integer.class));
		
		
	}
	
	private Map<Class<?>, Object> store = new HashMap<Class<?>, Object>();
	
	public <T> void putFavorite(Class<T> key, T value){
		store.put(key, key.cast(value));//run time checking what's put in is valid type.
	}
	
	public <T> T getFavorite(Class<T> key){
		return key.cast(store.get(key));
	}

}
