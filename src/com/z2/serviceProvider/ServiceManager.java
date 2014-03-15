package com.z2.serviceProvider;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

	private ServiceManager(){} //prevent instantiation
	private static final Map<String, Provider> providers = new HashMap<String, Provider>();
	private static final String DEFAULT_PROVIDER_NAME = "myservice";
	
	public static void registerDefaultProvider(Provider p){
		registerProvider(DEFAULT_PROVIDER_NAME, p);
	}
	
	public static void registerProvider(String name, Provider p){
		providers.put(name, p);
	}
	/**
	 * static factory method.
	 * @param name
	 * @return
	 */
	public static Service newInstance(String name){
		Provider p = providers.get(name);
		if(p == null)
			throw new IllegalArgumentException();
		return p.newService();
	}
	
	public static Service newInstance(){
		return newInstance(DEFAULT_PROVIDER_NAME);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceManager.registerDefaultProvider(new JackServiceProvider());
		Service s = ServiceManager.newInstance();
		s.printString(); 
	}

}
