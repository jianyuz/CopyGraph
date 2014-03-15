package com.z2.utilClass;

import java.util.WeakHashMap;
/**
 * weakhaspmap refers to weakreference directly.
 * key is referred weakly by weak reference.
 * value is not directly gc collectable but after key is gced. value can be too.
 * weakreference is eagerly reclaimed by gc.
 * 
 * @author zhouzhou
 *
 */
public class WeakHashMapTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WeakHashMap<Data, String> map = new WeakHashMap<Data,String>();
		Data some = new Data("foo");
		map.put(some, "other");
		
		System.out.println("map contains some data? " + map.containsKey(some));
		
		some = null; //now make it eligible for GC by dereferencing the key
		System.gc();
		for(int i= 0; i< 100; i++){
			Thread.sleep(1000);
			if(map.size() !=0){
				System.out.println("at iteration " + i + " map holds the reference");
			}else{
				System.out.println("some has been reclaimed by GC!");
				break;
			}
		}
		
		System.runFinalizersOnExit(true);
		Runtime.runFinalizersOnExit(true);
	}
	
	
	static class Data{
		String value;
		Data(String value){
			this.value = value;
		}
	}

}
