package memoryVisibility;

public class ReverseString {

	public static void main(String[] args){
		String input= " adbdccd";
		
		System.out.println(reverse(input));
	}
	
	public static String reverse(String input){
		if(input == null || input.length() == 0) return input;
		char[] chars = input.toCharArray();
		for(int i=0; i< chars.length/2; i++){
			swap(chars, i, chars.length-1-i);
		}
		return new String(chars);
		
	}

	public static void swap(char[] chars, int i, int j){
		char c = chars[i];
		chars[i] = chars[j];
		chars[j] = c;
	}
}
