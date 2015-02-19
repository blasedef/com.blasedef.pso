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

	public Double getDoubleInRange(Double min, Double max, boolean canNegative) {
		Double result = ((max - min)/2) + min;
		if(canNegative){
			result = 1.0;
		}
		return result;
	}
	
	public int getFamily(){
		return random.nextInt();
	}

}
