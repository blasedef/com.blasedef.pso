package com.blasedef.pso.particle;

import com.blasedef.pso.costfunction.ICostFunction;

public class Particle {

	private IPosition position;
	private IPosition velocity;
	private IPosition myBest;
	private int group;
	private Double originalVelocityProportion, 
	personalBestProportion, 
	groupProportion, 
	globalBestProportion;
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double globalBestProportion){
		
		this.position = new Position();
		this.velocity = new Position();
		this.myBest = new Position();
		this.group = 0;
		this.originalVelocityProportion = originalVelocityProportion;
		this.personalBestProportion = personalBestProportion;
		this.groupProportion = 0.0;
		this.globalBestProportion = globalBestProportion;
	}
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double groupProportion,
			Double globalBestProportion,
			int group){
		
		this.position = new Position();
		this.velocity = new Position();
		this.myBest = new Position();
		this.group = 0;
		this.originalVelocityProportion = originalVelocityProportion;
		this.personalBestProportion = personalBestProportion;
		this.groupProportion = groupProportion;
		this.globalBestProportion = globalBestProportion;
	}

	public IPosition getPosition() {
		return position;
	}

	public IPosition getVelocity() {
		return velocity;
	}

	public IPosition getMyBest() {
		return myBest;
	}

	public int getGroup() {
		return group;
	}
	
	public Particle assessCost(ICostFunction c){
		c.assessCost(this.position);
		return this;
	}

	public Double getOriginalVelocityProportion() {
		return originalVelocityProportion;
	}

	public void setOriginalVelocityProportion(Double originalVelocityProportion) {
		this.originalVelocityProportion = originalVelocityProportion;
	}

	public Double getPersonalBestProportion() {
		return personalBestProportion;
	}

	public Double getGroupProportion() {
		return groupProportion;
	}

	public Double getGlobalBestProportion() {
		return globalBestProportion;
	}
	
	public void updateVelocity(){
		
	}
}