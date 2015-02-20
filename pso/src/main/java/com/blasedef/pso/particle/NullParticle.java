package com.blasedef.pso.particle;

public class NullParticle extends Particle {

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

	@Override
	public void assessCost() {
		
	}

	@Override
	public void updateVelocity() {
		
	}

	@Override
	public void move(boolean jumps) {
		
	}

	@Override
	public void run() {
		
	}

}
