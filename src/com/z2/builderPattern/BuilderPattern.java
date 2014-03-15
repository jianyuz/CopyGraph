package com.z2.builderPattern;

/**
 * it is ideal when constructor or staic factories got more than a hand full of  parameters
 * @author zhouzhou
 *
 */
public class BuilderPattern {

	private final int wheels;
	private final String brand;
	private final String bodyMake;
	private final int doors;
	private final boolean leatherSeat;//private final fields
	
	public static class Builder{
		private final String brand;
		private final String bodyMake;//same parameters here. private final
		
		//optinal parameters initialized
		private int wheels = 4;
		private int doors = 4;
		private boolean leatherSeat = false;
		
		public Builder(String brand, String bodyMake){
			this.brand = brand;
			this.bodyMake = bodyMake;
		}
		
		/**
		 * set optional parameters. return builder back.
		 * @param count
		 * @return
		 */
		public Builder wheels(int count){
			this.wheels = count;
			return this;
		}
		
		public Builder doors(int count){
			this.doors = count;
			return this;
		}
		/**
		 * build method to build parent class using inner class data fields.
		 * @return
		 */
		public BuilderPattern build(){
			return new BuilderPattern(this);
		}
	}
	
	private BuilderPattern(Builder builder){
		this.wheels = builder.wheels;
		this.bodyMake = builder.bodyMake;
		this.brand = builder.brand;
		this.doors = builder.doors;
		this.leatherSeat = builder.leatherSeat;
	}
	
	
	public int getWheels() {
		return wheels;
	}


	public String getBrand() {
		return brand;
	}


	public String getBodyMake() {
		return bodyMake;
	}


	public int getDoors() {
		return doors;
	}


	public boolean isLeatherSeat() {
		return leatherSeat;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BuilderPattern bp = new BuilderPattern.Builder("toyota", "sedan").wheels(2).doors(2).build();
		System.out.println(bp.getBodyMake());
		System.out.println(bp.getWheels());
	}

}
