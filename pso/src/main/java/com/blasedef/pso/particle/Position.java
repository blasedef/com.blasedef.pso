package com.blasedef.pso.particle;

import java.util.ArrayList;

public class Position implements IPosition {
	
	private ArrayList<Double> position;
	private Double cost;
	
	public Position(){
		this.setPosition(new ArrayList<Double>());
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

	public void setPosition(Double d, int i) {
		this.position.set(i, d);
	}
	
	public int getSize(){
		return this.position.size();
	}
	
	public void move(ArrayList<Double> velocity, ArrayList<Double> jumps){
		for(int index = 0; index < velocity.size(); index++){
			Double x = this.position.get(index) + velocity.get(index) * jumps.get(index);
			this.position.set(index, x);
		}
	}
	
	public void move(ArrayList<Double> velocity){
		for(int index = 0; index < velocity.size(); index++){
			Double x = this.position.get(index) + velocity.get(index);
			this.position.set(index, x);
		}
	}
	
	public void setCost(Double cost){
		this.cost = cost;
	}
	
	public Double getCost(){
		return this.cost;
	}


}