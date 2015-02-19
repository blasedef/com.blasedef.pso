package com.blasedef.pso.particle;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.blasedef.pso.costfunction.ICostFunction;
import com.blasedef.pso.rng.IRandomNumberGenerator;
import com.blasedef.pso.space.ISpace;

public class Particle implements IParticle, Comparable<IParticle>, Runnable {

	private IPosition position;
	private IPosition velocity;
	private PriorityQueue<IParticle> myBest;
	private IPosition jumps;
	private int group;
	private Double originalVelocityProportion, 
	personalBestProportion, 
	groupProportion, 
	globalBestProportion;
	private IRandomNumberGenerator rng;
	private PriorityQueue<IParticle> globalQueue;
	private ArrayList<PriorityQueue<IParticle>> groupList;
	private Barrier barrier;
	private ICostFunction costFunction;
	private boolean isFinished;
	private ISpace space;
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double globalBestProportion,
			IRandomNumberGenerator rng,
			PriorityQueue<IParticle> globalQueue,
			ArrayList<PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		this.position = new Position(space);
		this.velocity = new Position(null);
		this.myBest = new PriorityQueue<IParticle>();
		this.group = 0;
		this.originalVelocityProportion = originalVelocityProportion;
		this.personalBestProportion = personalBestProportion;
		this.groupProportion = 0.0;
		this.globalBestProportion = globalBestProportion;
		this.rng = rng;
		this.globalQueue = globalQueue;
		this.groupList = groupList;
		this.barrier = barrier;
		this.costFunction = costFunction;
		this.space = space;
		
		this.myBest.add(this);
		this.globalQueue.add(this);
		this.groupList.get(group).add(this);
		this.barrier.add(this);
		isFinished = false;
		this.position.setPosition(this.space.initialisePosition());
		this.velocity.setPosition(this.space.initialiseVelocity());
	}
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double groupProportion,
			Double globalBestProportion,
			int group,
			IRandomNumberGenerator rng,
			PriorityQueue<IParticle> globalQueue,
			ArrayList<PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		this.position = new Position(space);
		this.velocity = new Position(null);
		this.myBest = new PriorityQueue<IParticle>();
		this.group = 0;
		this.originalVelocityProportion = originalVelocityProportion;
		this.personalBestProportion = personalBestProportion;
		this.groupProportion = groupProportion;
		this.globalBestProportion = globalBestProportion;
		this.rng = rng;
		this.globalQueue = globalQueue;
		this.groupList = groupList;
		this.barrier = barrier;
		this.costFunction = costFunction;
		this.space = space;

		this.myBest.add(this);
		this.globalQueue.add(this);
		this.groupList.get(group).add(this);
		this.barrier.add(this);
		isFinished = false;
		this.position.setPosition(this.space.initialisePosition());
		this.velocity.setPosition(this.space.initialiseVelocity());
	}
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double groupProportion,
			Double globalBestProportion,
			int group,
			IRandomNumberGenerator rng,
			IPosition jumps,
			PriorityQueue<IParticle> globalQueue,
			ArrayList<PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		this.position = new Position(space);
		this.velocity = new Position(null);
		this.myBest = new PriorityQueue<IParticle>();
		this.group = 0;
		this.originalVelocityProportion = originalVelocityProportion;
		this.personalBestProportion = personalBestProportion;
		this.groupProportion = groupProportion;
		this.globalBestProportion = globalBestProportion;
		this.rng = rng;
		this.jumps = jumps;
		this.globalQueue = globalQueue;
		this.groupList = groupList;
		this.barrier = barrier;
		this.costFunction = costFunction;
		this.space = space;

		this.myBest.add(this);
		this.globalQueue.add(this);
		this.groupList.get(group).add(this);
		this.barrier.add(this);
		isFinished = false;
		this.position.setPosition(this.space.initialisePosition());
		this.velocity.setPosition(this.space.initialiseVelocity());
	}

	public IPosition getPosition() {
		return position;
	}

	public IPosition getVelocity() {
		return velocity;
	}

	public IPosition getMyBest() {
		return this.myBest.peek().getPosition();
	}

	public int getGroup() {
		return group;
	}
	
	public void assessCost(){
		costFunction.assessCost(this.space.filter(this.position));
		isFinished = true;
	}
	
	public void updateVelocity(){
		
		IParticle global = this.globalQueue.peek();
		IParticle group = this.groupList.get(this.group).peek();
		
		if(group == null)
			group = new NullParticle();
		
		Position newVelocity = new Position(null); 
		
		for(int i = 0; i < this.position.getSize(); i++){
			
			Double personalBestWeight = rng.getRandomNumber(personalBestProportion);
			Double groupBestWeight = rng.getRandomNumber(groupProportion);
			Double globalBestWeight = rng.getRandomNumber(globalBestProportion);
			
			Double velocity = (this.velocity.getPosition(i)*this.originalVelocityProportion) +
					(this.myBest.peek().getPosition(i)*personalBestWeight) +
					(group.getPosition(i)*groupBestWeight) +
					(global.getPosition(i)*globalBestWeight);
			
			newVelocity.setPosition(i, velocity);
			
		}
		
	}

	public Double getPosition(int i) {
		return this.position.getPosition(i);
	}
	
	public void move(boolean jumps){
		if(jumps)
			this.position.move(this.velocity, this.jumps);
		else
			this.position.move(this.velocity);
	}

	public Double getCost() {
		return this.position.getCost();
	}

	public int compareTo(IParticle o) {
		Double theirCost = o.getCost();
		return this.getCost().intValue() - (theirCost).intValue();
	}

	public void run() {
		
		this.barrier.increment1();
		while(this.barrier.barrier1())
		this.barrier.increment2();	
		assessCost();
		while(this.barrier.barrier2())
		updateVelocity();
		move(false);
		
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean b) {
		isFinished = b;
	}

}