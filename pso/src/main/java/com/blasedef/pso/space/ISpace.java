package com.blasedef.pso.space;

import java.util.ArrayList;

import com.blasedef.pso.particle.IPosition;

public interface ISpace {

	IPosition filter(IPosition position);

	IBoundaryAction getBoundaryRule();
	
	Double fit(int index, Double d);

	public ArrayList<Double> initialisePosition();

	public ArrayList<Double> initialiseVelocity();
	

}
