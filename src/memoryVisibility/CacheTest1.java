package memoryVisibility;

import java.util.concurrent.ConcurrentHashMap;

public class CacheTest1 {
	private ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<String, String>();
	private Computable<String, String> service = new LengthService();
	
	public CacheTest1(LengthService service){
		this.service = service;
	}
	
	public String retrieveValue(String key){
		String value = cache.get(key);
		if(value == null){
			value = service.compute(key);
			cache.put(key,  value);
		}
		return value;
	}
}
