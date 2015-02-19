package com.blasedef.pso.space;

import java.util.ArrayList;

public class BoundaryActionBounce implements IBoundaryAction {
	private ArrayList<Tuple> space;
	
	public BoundaryActionBounce(ArrayList<Tuple> space){
		this.space = space;
	}
	
	public Double fit(int index, double d) {
		//TODO
		return 0.0;
	}
	
	public void addSpace(ArrayList<Tuple> space) {
		this.space = space;
	}
}
