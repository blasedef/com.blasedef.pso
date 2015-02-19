package com.blasedef.pso.particle;

import java.util.ArrayList;

import com.blasedef.pso.space.ISpace;

public class Position implements IPosition {
	
	private ArrayList<Double> position;
	private Double cost;
	private ISpace iSpace;
	private boolean isVelocity = false;
	
	public Position(ISpace iSpace){
		this.setPosition(new ArrayList<Double>());
		this.cost = Double.MAX_VALUE;
		if(iSpace == null){
			this.isVelocity = true;
		}
		this.iSpace = iSpace;
		
	}

	public ArrayList<Double> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Double> position) {
		this.position = position;
	}
	
	public Double getPosition(int i) {
		return position.get(i);
	}

	public void setPosition(int i, Double d) {
		this.position.set(i, d);
	}
	
	public int getSize(){
		return this.position.size();
	}
	
	public void move(IPosition velocity, IPosition jumps){
		if(!isVelocity){
			for(int index = 0; index < velocity.getSize(); index++){
				Double x = iSpace.fit(index, this.position.get(index) + velocity.getPosition(index) * jumps.getPosition(index));
				this.position.set(index, x);
			}
		}
	}
	
	public void move(IPosition velocity){
		if(!isVelocity){
			for(int index = 0; index < velocity.getSize(); index++){
				Double x = iSpace.fit(index,this.position.get(index) + velocity.getPosition(index));
				this.position.set(index, x);
			}
		}
	}
	
	public void setCost(Double cost){
		this.cost = cost;
	}
	
	public Double getCost(){
		return this.cost;
	}


}