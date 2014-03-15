package com.z2.serviceProvider;

public class JackServiceProvider implements Provider{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Service newService() {
		return new MyService();
	}

}
