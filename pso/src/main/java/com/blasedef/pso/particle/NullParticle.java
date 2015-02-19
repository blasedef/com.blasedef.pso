package com.blasedef.pso.particle;

public class NullParticle implements IParticle {

	private IPosition position;
	private IPosition velocity;
	
	public NullParticle(){
		this.position = new NullPosition();
		this.velocity = new NullPosition();
	}

	public IPosition getPosition() {
		return this.position;
	}

	public IPosition getVelocity() {
		return this.velocity;
	}

	public Double getPosition(int i) {
		return 0.0;
	}

	public Double getCost() {
		return 0.0;
	}

	public boolean isFinished() {
		return true;
	}

	public void setFinished(boolean b) {
	}

}
