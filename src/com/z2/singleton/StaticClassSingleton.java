package com.z2.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class StaticClassSingleton implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StaticClassSingleton(){
		if(SingletonHolder.instance != null){
			throw new IllegalStateException(); //prevent reflection attack.
		}
	}
	
	private static class SingletonHolder{
		public static final StaticClassSingleton instance = new StaticClassSingleton();
	}
	
	public static StaticClassSingleton getInstance(){
		return SingletonHolder.instance;
	}
	
	/**
	 * prevent deserialization to create multiple instances.
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException{
		return SingletonHolder.instance;
	}
	
	/**
	 * thread safe. eager init though.
	 * doesn't beat reflection attack
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
