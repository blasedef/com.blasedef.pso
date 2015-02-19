package com.blasedef.pso.particle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.blasedef.pso.costfunction.ICostFunction;
import com.blasedef.pso.rng.IRandomNumberGenerator;
import com.blasedef.pso.space.ISpace;

public class Particle implements IParticle, Comparable<IParticle>, Runnable {

	private IPosition position;
	private IPosition velocity;
	private PriorityQueue<IParticle> myBest;
	private IPosition jumps;
	private Integer group;
	private Double originalVelocityProportion, 
	personalBestProportion, 
	groupProportion, 
	globalBestProportion;
	private IRandomNumberGenerator rng;
	private PriorityQueue<IParticle> globalQueue;
	private HashMap<Integer,PriorityQueue<IParticle>> groupList;
	private Barrier barrier;
	private ICostFunction costFunction;
	private boolean isFinished;
	private ISpace space;
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double globalBestProportion,
			IRandomNumberGenerator rng,
			PriorityQueue<IParticle> globalQueue,
			HashMap<Integer,PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		this.position = new Position(space,true);
		this.velocity = new Position(space,false);
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
		if(!this.groupList.keySet().contains(this.group))
			this.groupList.put(this.group,new PriorityQueue<IParticle>());
		this.groupList.get(group).add(this);
		this.barrier.add(this);
		isFinished = false;

	}
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double groupProportion,
			Double globalBestProportion,
			Integer group,
			IRandomNumberGenerator rng,
			PriorityQueue<IParticle> globalQueue,
			HashMap<Integer,PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		this.position = new Position(space,true);
		this.velocity = new Position(space,false);
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
		if(!this.groupList.keySet().contains(this.group))
			this.groupList.put(this.group,new PriorityQueue<IParticle>());
		this.groupList.get(group).add(this);
		this.barrier.add(this);
		isFinished = false;
	}
	
	public Particle(Double originalVelocityProportion,
			Double personalBestProportion,
			Double groupProportion,
			Double globalBestProportion,
			Integer group,
			IRandomNumberGenerator rng,
			IPosition jumps,
			PriorityQueue<IParticle> globalQueue,
			HashMap<Integer,PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		this.position = new Position(space,true);
		this.velocity = new Position(space,false);
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
		if(!this.groupList.keySet().contains(this.group))
			this.groupList.put(this.group,new PriorityQueue<IParticle>());
		this.groupList.get(group).add(this);
		this.barrier.add(this);
		isFinished = false;
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
		
		Position newVelocity = new Position(); 
		
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
		
		this.velocity.setPosition(newVelocity.getPosition());
		
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