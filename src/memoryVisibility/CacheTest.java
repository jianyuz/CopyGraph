package memoryVisibility;

import java.util.HashMap;
import java.util.Map;

public class CacheTest {
	
	private Map<String, String> cache = new HashMap<String, String>();
	private Computable<String, String> service = new LengthService();
	
	public CacheTest(LengthService service){
		this.service = service;
	}
	
	public synchronized String retrieveValue(String key){
		String value = cache.get(key);
		if(value == null){
			value = service.compute(key);
			cache.put(key,  value);
		}
		return value;
	}
	
	
}
