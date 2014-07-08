package anno;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ReflectMain {

	public static void main(String[] args){
		if(args.length > 0){
			String className = args[0];
			Class<?> myClass = null;
			try {
				myClass = Class.forName(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Class not found");
				System.exit(1);
			}
			
			try {
				Set<String> mySet = (Set<String>) (myClass.newInstance());
				List<String> eles = Arrays.asList(args).subList(1, args.length);
				mySet.addAll(eles);
				System.out.println(mySet);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
