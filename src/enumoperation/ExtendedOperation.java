package enumoperation;

import java.util.Arrays;
import java.util.Collection;

public enum ExtendedOperation implements Operation{
	EXP("^"){
		public double apply(double x, double y){
			return Math.pow(x, y);
		}
	};

	private final String symbol;
	ExtendedOperation(String symbol){
		this.symbol = symbol;
	}
	
	public String toString(){
		return this.symbol;
	}
	
	public static void main(String[] args){
		test(ExtendedOperation.class, 10, 5);
		test1(Arrays.asList(ExtendedOperation.values()), 10, 5);
	}
	
	//both enum and operation extension
	public static <T extends Enum<T> & Operation> void test(Class<T> opset, double x, double y){
		for(Operation op: opset.getEnumConstants()){
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
	
	public static void test1(Collection<? extends Operation> c, double x, double y){
		for(Operation op : c){
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
}
