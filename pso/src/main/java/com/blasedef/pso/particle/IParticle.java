package com.blasedef.pso.particle;

public interface IParticle {

	public IPosition getPosition();
	
	public Double getPosition(int i);

	public IPosition getVelocity();
	
	public Double getCost();

	public boolean isFinished();
	
	public void setFinished(boolean b);
}
