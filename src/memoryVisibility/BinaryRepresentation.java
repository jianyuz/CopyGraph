package memoryVisibility;

public class BinaryRepresentation {

	public static void main(String[] args){
		System.out.println(binaryRepresent("3.625"));
	}
	
	
	public static String binaryRepresent(String fNum){
		if(fNum == null || fNum.length() == 0) return "";
		String[] parts = fNum.split("\\.");
		
		StringBuilder sb = new StringBuilder();
		if(parts.length >= 1){
			//integer part.
			int intPart = Integer.parseInt(parts[0]);
			
			int res = intPart;
			while(res > 0){
				int digit = res%2 ;
				sb.insert(0, digit);
				res = res/2;
			}
			
		}
		
		if(parts.length >=2){
			sb.append(".");
			float decimalPart = Float.parseFloat("." + parts[1]);
			
			float res = decimalPart;
			int counter = 0;
			
			while(counter < 32 && res > 0){
				float  decimal = res * 2;
				if(decimal >= 1){
					sb.append(1);
					res = decimal - 1;
				}else{
					sb.append(0);
					res = decimal;
				}
				counter ++;
			}
			
			if(counter >=32){
				return "ERROR";
			}
		}
		
		return sb.toString();
	}
}
