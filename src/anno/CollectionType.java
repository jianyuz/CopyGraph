package anno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** overloading is determined at compile time */

public class CollectionType {

	public static String classifier(Collection<?> c) {
		return "unknown";
	}

	public static String classifier(Set<?> s) {
		return "set";
	}

	public static String classifier(List<?> l) {
		return "list";
	}

	public static void main(String[] args) {
		Collection<?>[] all = { new HashMap<String, String>().values(),
				new HashSet<String>(), new ArrayList<String>() };

		for (Collection<?> c : all) {
			System.out.println(classifier(c));
		}
		
		int[] array = new int[] {2, 3};
		int sum = 0;
		for(int i=0, n=array.length; i< n; i++){
			sum += array[i];
		}
		System.out.println(sum);

	}
}
