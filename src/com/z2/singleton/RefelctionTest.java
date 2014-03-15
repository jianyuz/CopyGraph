package com.z2.singleton;

import java.lang.reflect.Constructor;

public class RefelctionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(StaticClassSingleton.getInstance().hashCode());
		System.out.println(createMore().hashCode());
	}
	
	public static StaticClassSingleton createMore(){
		StaticClassSingleton s = null;
		try{
			Constructor[] cs = StaticClassSingleton.class.getDeclaredConstructors();
			for(Constructor c: cs){
				c.setAccessible(true);
				s= (StaticClassSingleton)c.newInstance();
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}

}
