package memoryVisibility;

import java.util.HashMap;
import java.util.Map;

/**
 * note this is not a parameterized class
 * only method is parameterized.
 * otherwise we got warning on the compilation of the class.
 * 
 * doesn't work with non-reifiable type
 * type information not availalbe at runtime
 * like List<String>
 * List<Integer> all have the same List.class type.
 * 
 * @author zhouzhou
 *
 * @param <T>
 */
public class HeteroContainer {

	private Map<Class<?>, Object> store = new HashMap<Class<?>, Object>();
	
	public <T> void putFavorites(Class<T> key, T value ){
		//store.put(key,  value);
		if(key == null){
			throw new NullPointerException("incorrect type for key");
		}
		store.put(key, key.cast(value));//enforce the type.
	}
	
	public <T> T getFavorite(Class<T> key){
		//note here that requires type cast, class's cast method.
		return key.cast(store.get(key));
	}
	
	/**
	 * for bounded type token
	 * assubclass to convert type token to subclass.
	 * we can also use a generic type as the key like Column<T>
	 * to store a database row into a map.
	 * @param type
	 * @param subType
	 * @return
	 */
	public <T extends Annotation> T getAnnotation(Class<T> type, String subType){
		Class<?> subTypeClass = null;
		try{
			subTypeClass = Class.forName(subType);
		}catch(Exception e){
			throw new IllegalArgumentException("wrong type");
		}
		
		Object ret = store.get(subTypeClass.asSubclass(type));
		
		return type.cast(ret);
	}
	
	public static void main(String[] args){
		HeteroContainer container = new HeteroContainer();
		container.putFavorites(String.class, "test");
		//container.putFavorites(Integer.class, "test");
		container.putFavorites(Integer.class, 2);
		container.putFavorites(Class.class, HeteroContainer.class);
		
		String sValue = container.getFavorite(String.class);
		int iValue = container.getFavorite(Integer.class);
		Class<?> cValue = container.getFavorite(Class.class);
		
		System.out.printf("%s, %d, %s%n", sValue, iValue, cValue.getName());
		System.out.println("me");
		
	}
}

class Annotation{
	
}

class MyAnnotation extends Annotation{
	
}
