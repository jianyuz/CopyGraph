package com.z2.comparable;

/**
 * immutable
 * @author zhouzhou
 *
 */
public class Complex {
	
	public static final Complex ZERO = new Complex(0,0);
	public static final Complex ONE = new Complex(1,0);
	public static final Complex I = new Complex(0, 1);

	private final double re;
	private final double im;
	
	/**
	 * make sure the instance is not derived if we depend 
	 * on the immutability.
	 * @param val
	 * @return
	 */
	public static Complex safeInstance(Complex val){
		if(val.getClass() != Complex.class){
			return new Complex(val.re, val.im);
		}
		return val;
	}
	/**
	 * factory method.
	 * the calss is final .
	 * @param re
	 * @param im
	 * @return
	 */
	public static Complex valueOf(double re, double im){
		return new Complex(re, im);
	}
	
	/**
	 * factory method can create object using the same signature.
	 * constructor can't.
	 * @param r
	 * @param theta
	 * @return
	 */
	public static Complex valueOfPolar(double r, double theta){
		return new Complex(r * Math.cos(theta),
				r * Math.sin(theta));
	}
	
	private Complex(double re, double im){
		this.re = re;
		this.im = im;
	}
	
	public double realPart()
	{
		return re;
	}
	
	public double imginaryPart() {
		return im;
	}
	
	public Complex add(Complex c){
		return new Complex(re+ c.re, im + c.im);
	}
	
	public Complex multiply(Complex c){
		return new Complex(re * c.re - im * c.im,
				re * c.im + im *c.re);//operation return new instance.
		//functional pattern . not procedure patter.
	}
	
	@Override
	public int hashCode() {
		final int prime = 31; //use a prime
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(im);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(re);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override public String toString(){
		return "(" + re + " + " + im + "i)";
	}
	
	
}
