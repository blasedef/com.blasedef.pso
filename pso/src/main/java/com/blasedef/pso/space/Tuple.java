package com.blasedef.pso.space;

import com.blasedef.pso.rng.IRandomNumberGenerator;

public class Tuple {
	
	private Double min, max;
	private IRandomNumberGenerator rng;
	
	public Tuple(Double min, Double max, IRandomNumberGenerator rng){
		this.min = min;
		this.max = max;
		this.rng = rng;
	}
	
	public Double getPosition(){
		return rng.getDoubleInRange(min, max);
	}
	
	public boolean isInsideBounds(Double d){
		return (d >= min) && (d <= max);
	}
	
	public boolean lessThanMin(Double d){
		return (d < min);
	}
	
	public boolean greaterThanMax(Double d){
		return (d > max);
	}

	public Double getMin() {
		return min;
	}
	

	public Double getMax() {
		return max;
	}

}
