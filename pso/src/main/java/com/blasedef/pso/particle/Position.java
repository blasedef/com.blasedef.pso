package com.blasedef.pso.particle;

import java.util.ArrayList;

import com.blasedef.pso.space.ISpace;

public class Position implements IPosition {
	
	private ArrayList<Double> position;
	private Double cost;
	private ISpace space;
	private boolean isVelocity = false;
	
	public Position(ISpace space, boolean position){
		this.setPosition(new ArrayList<Double>());
		this.cost = Double.MAX_VALUE;
		this.space = space;
		if(space != null)
			if(position){
				this.setPosition(this.space.initialisePosition());
			} else {
				this.setPosition(this.space.initialiseVelocity());
			}
		
	}
	
	public Position(){
		this.setPosition(new ArrayList<Double>());
		this.cost = Double.MAX_VALUE;
		this.space = null;
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
		if(this.position == null){
			this.position = new ArrayList<Double>();
		}
		if(this.position.size() == i){
			this.position.add(d);
		} else {
			this.position.set(i, d);
		}
		
	}
	
	public int getSize(){
		return this.position.size();
	}
	
	public void move(IPosition velocity, IPosition jumps){
		if(!isVelocity){
			for(int index = 0; index < velocity.getSize(); index++){
				Double x = space.fit(index, this.position.get(index) + velocity.getPosition(index) * jumps.getPosition(index));
				this.position.set(index, x);
			}
		}
	}
	
	public void move(IPosition velocity){
		if(!isVelocity){
			for(int index = 0; index < velocity.getSize(); index++){
				Double x = space.fit(index,this.position.get(index) + velocity.getPosition(index));
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

	@Override
	public String toString(){
		
		String name = "";
		
		if((this.position.size() == 0) || (this.position == null)){
			name = "no position yet";
		}
		
		for(int i = 0; i < this.position.size(); i++){
			name = name + " " + i + ":" + this.position.get(i);
		}
		
		return name + " @Cost " + this.cost;
		
	}

}