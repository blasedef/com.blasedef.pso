package com.blasedef.pso.rng;

import java.security.SecureRandom;

public class SecureRandomNumberGenerator implements IRandomNumberGenerator {
	
	private SecureRandom random;
	
	public SecureRandomNumberGenerator(){
		random = new SecureRandom();
	}
	
	public synchronized Double getRandomNumber(Double w){
		return random.nextDouble() * w;
	}

	public int getFamily(){
		return random.nextInt();
	}

	public Double getDoubleInRange(Double min, Double max, boolean canNegative) {
		Double result = (random.nextDouble() * ((max-min)+1)) + min;
		if(canNegative){
			int negative = random.nextInt();
			if(negative < 0)
				result = 0 - result;
		}
		return result;
	}

}
