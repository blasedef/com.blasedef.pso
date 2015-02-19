package com.blasedef.pso.particle;

import java.util.ArrayList;

public interface IPosition {

	public int getSize();
	
	public Double getPosition(int i);
	
	public void setPosition(ArrayList<Double> position);
	
	public void move(IPosition velocity);
	
	public void move(IPosition velocity, IPosition jumps);
	
	public Double getCost();
	
	public void setCost(Double d);
}
