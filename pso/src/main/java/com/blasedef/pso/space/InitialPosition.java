package com.blasedef.pso.space;

import java.util.ArrayList;

public class InitialPosition implements IInitialPosition {

	private ArrayList<Tuple> space;

	public InitialPosition(ArrayList<Tuple> space) {
		this.space = space;
	}

	/**
	 * Just a random vector in the space
	 */
	public ArrayList<Double> initialise() {
		
		ArrayList<Double> velocity = new ArrayList<Double>();
		
		for(Tuple t : space){
			velocity.add(t.getPosition());
		}
		
		return velocity;
	}

}
