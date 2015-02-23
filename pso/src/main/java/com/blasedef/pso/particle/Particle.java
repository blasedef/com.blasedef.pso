package com.blasedef.pso.particle;

import com.blasedef.pso.rng.IRandomNumberGenerator;
import com.blasedef.pso.space.ISpace;

public abstract class Particle implements IParticle, Comparable<IParticle>, Runnable {

	protected IPosition position;
	protected IPosition velocity;
	protected boolean isFinished = false;
	protected Integer group = 0;
	protected int generation = 0;
	protected int family;
	protected IRandomNumberGenerator rng;
	protected ISpace space;
	
	public static final String OVPROP = "Original velocity proportion";
	public static final String LOPROP = "Local best position proportion";
	public static final String GPPROP = "Group best position proportion";
	public static final String GBPROP = "Global best position proportion";
	public static final String TOPROP = "Total prop";

	
	public Particle(){
	}
	
	public Particle(IRandomNumberGenerator rng, ISpace space){
		
		this.position = new Position(space, true);
		this.velocity = new Position(space, false);
		this.rng = rng;
		this.space = space;
		this.setFamily(rng.getFamily());
		
	}
	
	public IPosition getPosition() {
		return position;
	}

	public IPosition getVelocity() {
		return velocity;
	}

	public Double getPosition(int i) {
		return this.position.getPosition(i);
	}

	public Double getCost() {
		return this.position.getCost();
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean b) {
		this.isFinished = b;
	}

	public abstract void assessCost();

	public abstract void updateVelocity();

	public abstract void move(boolean jumps);

	public void setGeneration(int i) {
		this.generation = i;
	}

	public void setFamily(int family) {
		this.family = family;
	}

	public abstract void run();

	public int compareTo(IParticle o) {
		Double theirCost = o.getCost();
		return this.getCost().intValue() - (theirCost).intValue();
	}
	
	public int getGeneration() {
		return generation;
	}
	
	public void updateGeneration(){
		this.generation++;
	}

	public int getFamily() {
		return family;
	}
	
	public int getGroup() {
		return group;
	}
	
	public void setGroup(int i) {
		this.group = i;
	}

}
