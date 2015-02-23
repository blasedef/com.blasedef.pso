package com.blasedef.pso.particle;

import java.util.HashMap;
import java.util.PriorityQueue;

import com.blasedef.pso.costfunction.ICostFunction;
import com.blasedef.pso.rng.IRandomNumberGenerator;
import com.blasedef.pso.space.ISpace;

public class ActiveParticle extends Particle {


	private PriorityQueue<IParticle> myBest = new PriorityQueue<IParticle>();
	private IPosition jumps;
	private PriorityQueue<IParticle> globalQueue;
	private HashMap<Integer,PriorityQueue<IParticle>> groupList;
	private Barrier barrier;
	private ICostFunction costFunction;
	private HashMap<String,Double> proportions;

	
	public ActiveParticle(IRandomNumberGenerator rng, ISpace space){
		super(rng,space);
	}
	
	public ActiveParticle(HashMap<String,Double> proportions,
			Integer group,
			IRandomNumberGenerator rng,
			IPosition jumps,
			PriorityQueue<IParticle> globalQueue,
			HashMap<Integer,PriorityQueue<IParticle>> groupList,
			Barrier barrier,
			ICostFunction costFunction,
			ISpace space){
		
		super(rng,space);
		
		this.proportions = proportions;
		proportions.put(TOPROP, proportions.get(OVPROP) 
				+ proportions.get(LOPROP) 
				+ proportions.get(GPPROP)
				+ proportions.get(GBPROP));
		
		for(String key : proportions.keySet()){
			if(!key.equals(TOPROP))
				proportions.put(key, this.proportions.get(key)/this.proportions.get(TOPROP)); 
		}
		
		this.group = group;

		this.jumps = jumps;
		if(this.jumps == null)
			this.jumps = new NullPosition();
			
		this.globalQueue = globalQueue;
		this.groupList = groupList;
		
		this.barrier = barrier;
		
		this.costFunction = costFunction;

		this.myBest.add(this);
		
		this.globalQueue.add(this);
		if(!this.groupList.keySet().contains(this.group))
			this.groupList.put(this.group,new PriorityQueue<IParticle>());
		this.groupList.get(group).add(this);
		
		this.barrier.add(this);
	}

	public IPosition getMyBest() {
		return this.myBest.peek().getPosition();
	}
	
	@Override
	public void assessCost(){
		
		costFunction.assessCost(this.space.filter(this.position));
		isFinished = true;
	}
	
	@Override
	public void updateVelocity(){
		
		IParticle global = this.globalQueue.peek();
		IParticle group = this.groupList.get(this.group).peek();
		
		if(group == null)
			group = new NullParticle();
		
		Position newVelocity = new Position(); 
		
		for(int i = 0; i < this.position.getSize(); i++){
			
			Double personalBestWeight = rng.getRandomNumber(this.proportions.get(LOPROP));
			Double groupBestWeight = rng.getRandomNumber(this.proportions.get(GPPROP));
			Double globalBestWeight = rng.getRandomNumber(this.proportions.get(GBPROP));
			
			Double velocity = (this.velocity.getPosition(i)*(this.proportions.get(OVPROP))) +
					(this.myBest.peek().getPosition(i)*personalBestWeight) +
					(group.getPosition(i)*groupBestWeight) +
					(global.getPosition(i)*globalBestWeight);
			
			newVelocity.setPosition(i, velocity);
			
		}
		
		this.velocity.setPosition(newVelocity.getPosition());
		
	}
	
	@Override
	public void move(boolean jumps){
		
		ExpiredParticle temporaryParticle = new ExpiredParticle();
		
		((ExpiredPosition) temporaryParticle.getPosition()).setPosition(this.position);
		((ExpiredPosition) temporaryParticle.getVelocity()).setPosition(this.velocity);
		((ExpiredPosition) temporaryParticle.getPosition()).setCost(this.getCost());
		temporaryParticle.setGeneration(this.getGeneration());
		temporaryParticle.setFamily(this.family);
		this.updateGeneration();
		
		this.groupList.get(this.group).add(temporaryParticle);
		this.globalQueue.add(temporaryParticle);
		this.position.move(this.velocity, this.jumps);

		
	}

	@Override
	public void run() {
		
		System.out.println(this);
		
		this.barrier.increment1();
		while(this.barrier.barrier1())
		this.barrier.increment2();	
		assessCost();
		while(this.barrier.barrier2())
		updateVelocity();
		move(false);
		
	}

	
	@Override
	public String toString(){
		String name = "";
		
		name = "pos:" + this.position.toString();
		name = name + " vel:" + this.velocity.toString();
		name = name + " gen:" + this.generation;
		name = name + " fam:" + this.family;
		
		return name;
	}

	/**
	 * For testing purposes only
	 * @param d
	 * @param e
	 * @param f
	 */
	public void setPosition(double d, double e, double f) {
		this.position.setPosition(d,e,f);
	}

}