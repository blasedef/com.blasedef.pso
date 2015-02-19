package com.blasedef.pso.space;

import java.util.ArrayList;

public class BoundaryActionRandom implements IBoundaryAction {
	private ArrayList<Tuple> space;
	
	public BoundaryActionRandom(ArrayList<Tuple> space){
		this.space = space;
	}
	
	public Double fit(int index, double d) {
		//TODO
		return 0.0;
	}
}
