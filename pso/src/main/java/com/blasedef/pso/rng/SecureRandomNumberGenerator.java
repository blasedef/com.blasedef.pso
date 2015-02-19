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

	public Double getDoubleInRange(Double min, Double max) {
		return (random.nextDouble() * ((max-min)+1)) + min;
	}

}
