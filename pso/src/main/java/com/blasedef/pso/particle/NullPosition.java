package com.blasedef.pso.particle;

import java.util.ArrayList;

import com.blasedef.pso.space.IBoundaryAction;

public class NullPosition implements IPosition {
	
	public NullPosition(){
		
	}
	
	public int getSize(){
		return 1;
	}
	
	public Double getPosition(int i) {
		return 0.0;
	}

	public void move(IPosition velocity, IPosition jumps) {
	}

	public void move(IPosition velocity) {
	}

	public Double getCost() {
		return 0.0;
	}

	public void move(IPosition velocity, IBoundaryAction iBoundaryAction) {
		
	}

	public void move(IPosition velocity, IPosition jumps,
			IBoundaryAction iBoundaryAction) {
		
	}

	public void setPosition(ArrayList<Double> position) {
		
	}

	public void setCost(Double d) {
	}

	/**
	 * For testing only
	 */
	public void setPosition(double d, double e, double f) {
		// TODO Auto-generated method stub
		
	}
}
