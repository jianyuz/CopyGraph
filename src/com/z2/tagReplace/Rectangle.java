package com.z2.tagReplace;

public class Rectangle extends Shape{

	final double width;
	final double height;
	
	public Rectangle(double w, double h){
		this.width = w;
		this.height = h;
	}
	
	@Override 
	public double area(){
		return this.width * this.height;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
