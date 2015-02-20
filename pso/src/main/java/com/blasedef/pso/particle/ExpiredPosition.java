package com.blasedef.pso.particle;

import java.util.ArrayList;

public class ExpiredPosition implements IPosition {

	private ArrayList<Double> position;
	private Double cost;
	
	public int getSize() {
		return position.size();
	}

	public Double getPosition(int i) {
		return position.get(i);
	}

	public void setPosition(ArrayList<Double> position) {
		this.position = position;
	}

	public void move(IPosition velocity, IPosition jumps) {

	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double d) {
		this.cost = d;

	}

	/**
	 * For testing only
	 */
	public void setPosition(double d, double e, double f) {
		this.position.set(0, d);
		this.position.set(1, e);
		this.position.set(2, f);
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
		
		return name;
		
	}

	public void setPosition(IPosition position) {
		if(this.position == null){
			this.position = new ArrayList<Double>();
		}
		for(int index = 0; index < position.getSize(); index++){
			this.position.add(position.getPosition(index));
		}
	}

}
