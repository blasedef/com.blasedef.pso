package com.blasedef.pso.particle;

public interface IParticle {

	public IPosition getPosition();
	
	public Double getPosition(int i);

	public IPosition getVelocity();
	
	public Double getCost();

	public boolean isFinished();
	
	public void setFinished(boolean b);
	
	public void assessCost();
	
	public void updateVelocity();
	
	public void move(boolean jumps);
	
	public void setGeneration(int i);
	
	public void setFamily(int i);
}
