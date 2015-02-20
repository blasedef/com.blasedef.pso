package com.blasedef.pso.particle;

public class ExpiredParticle extends Particle {
	
	public ExpiredParticle(){
		super();
		this.position = new ExpiredPosition();
		this.velocity = new ExpiredPosition();
	}
	
	@Override
	public IPosition getPosition() {
		return position;
	}

	@Override
	public IPosition getVelocity() {
		return velocity;
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
