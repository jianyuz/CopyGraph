package memoryVisibility;
/**
 * kn solution
 * @author zhouzhou
 *
 */
public class RemoveDuplicate {

	public static void main(String[] args){
		//String input = "abscabbcd";
		//String input = "abbbcd";
		//String input = "abcd";
		String input = "ab";
		System.out.println(removeDup(input));
	}
	
	public static String removeDup(String input){
		if(input == null ||
				input.length() == 0
	 || input.length() == 1) return input;
		
		char[] chars = input.toCharArray();
		
		int tail = 1; //end of unique chars.
		int len = chars.length;
		
		for(int i = tail; i< len; i++){
			int j;
			for(j =0; j< tail; j++){//check against uniq chars
				if(chars[i] == chars[j]){
					break;
				}
			}
			if(j == tail){ //move the uniuqe char to tail.
				chars[tail] = chars[i];
				tail++;
			}
		}
		
		StringBuilder res = new StringBuilder();
		for(int i=0; i< tail; i++)
			res.append(chars[i]);
		
		return res.toString();
	}
}
