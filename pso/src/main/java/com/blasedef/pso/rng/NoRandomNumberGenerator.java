package com.blasedef.pso.rng;

import java.security.SecureRandom;


public class NoRandomNumberGenerator implements IRandomNumberGenerator {

	private SecureRandom random;
	
	public NoRandomNumberGenerator(){
		random = new SecureRandom();
	}
	
	public Double getRandomNumber(Double w) {
		return w;
	}

	public Double getDoubleInRange(Double min, Double max) {
		return (max - min)/100 + min;
	}
	
	public int getFamily(){
		return random.nextInt();
	}

}
