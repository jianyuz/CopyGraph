package com.z2.innerClass;

public class Calculator {

	String brand;
	
	public Calculator(String brand){
		this.brand = brand;
	}
	
	public int add(int a, int b){
		return Operation.PLUS.operate(a, b);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator cal = new Calculator("Toshiba");
		int res = cal.add(5,  5);
		System.out.println(res);
	}
	
	public static enum Operation{
		PLUS, MINUS, TIMES, DIVIDE;
		
		public int operate(int a, int b){
			//System.out.println(this.brand);
			//no way to access field in enclosing instance.
			switch(this){
			case PLUS:
				return a + b;
			case MINUS:
				return a-b;
			case TIMES:
				return a*b;
			case DIVIDE:
				return a/b;
			}
			throw new UnsupportedOperationException();
		}
	}

}
