package com.blasedef.pso.rng;


public class NoRandomNumberGenerator implements IRandomNumberGenerator {

	public NoRandomNumberGenerator(){
	}
	
	public Double getRandomNumber(Double w) {
		return w;
	}

	public Double getDoubleInRange(Double min, Double max) {
		return (max - min)/2 + min;
	}

}
