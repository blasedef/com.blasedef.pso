package com.blasedef.pso.space;

import java.util.ArrayList;

import com.blasedef.pso.particle.IPosition;
import com.blasedef.pso.rng.IRandomNumberGenerator;

public interface ISpace {

	public IPosition filter(IPosition position);

	public IBoundaryAction getBoundaryRule();
	
	public Double fit(int index, Double d);

	public ArrayList<Double> initialisePosition();

	public ArrayList<Double> initialiseVelocity();
	
	public void addDimension(Double min, Double max, IRandomNumberGenerator rng);
	
	public void addDimension(Tuple tuple);

}
