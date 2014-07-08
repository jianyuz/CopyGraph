package anno;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StringIntern {

	private static ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
	
//	public static String intern(String s){
//		String ret = map.putIfAbsent(s, s);
//		return ret == null? s: ret;
//	}
	
	/**
	 * here for more efficiency.
	 * we got double check logic 
	 * to make the intern method fast in multi-threaded env.
	 * @param s
	 * @return
	 */
	public static String intern(String s){
		String ret = map.get(s);
		if(ret == null){
			ret = map.putIfAbsent(s, s);
			if(ret == null) return s;
		}
		return ret;
	}
	
	public static void main(String[] args){
		System.out.println(intern("me"));
		System.out.println(intern(new String("me")));
	}
}
