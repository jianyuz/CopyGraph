package com.z2.tagReplace;

public class Circle extends Shape {
	
	private final double radius;
	public Circle(double radius){
		this.radius = radius;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return Math.PI * radius * radius;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
