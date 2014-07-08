package memoryVisibility;

import java.util.HashMap;
import java.util.Map;

public enum OperationEnum {

	
	PLUS("+") {
		@Override
		double apply(double a, double b) {
			return a  + b ;
		}
	}, 
	MINUS ("-"){
		@Override
		double apply(double a, double b){
			return a - b;
		}
	},
	MULTIPLY ("*"){
		@Override
		double apply(double a, double b){
			return a * b;
		}
	}
	;
	
	private static final Map<String, OperationEnum> stringMap = new HashMap<String, OperationEnum>();
	static{
		for(OperationEnum op : OperationEnum.values()){
			stringMap.put(op.toString(), op);
		}
	}
	private final String symbol;
	OperationEnum(String symbol){
		this.symbol = symbol;
	}
	
	abstract double apply(double a, double b);

	public String toString(){
		return symbol;
	}
	
	//replace dfault valueOf converter
	public static OperationEnum fromString(String str){
		return stringMap.get(str);
	}
	
	
	public static void main(String[] args){
		System.out.println(PLUS.apply(1, 2));
		
		for(OperationEnum value : OperationEnum.values()){
			System.out.printf("%2.2f %s %2.2f = %2.2f%n", 1f, value, 2f, value.apply(1, 2));
		}
		
		System.out.println(OperationEnum.fromString("+"));
	}
}
