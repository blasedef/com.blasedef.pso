package com.blasedef.pso.space;

import java.util.ArrayList;

public class BoundaryActionWrap implements IBoundaryAction {
	private ArrayList<Tuple> space;
	
	public BoundaryActionWrap(ArrayList<Tuple> space){
		this.space = space;
	}
	
	public Double fit(int index, double d) {
		//TODO
		return 0.0;
	}
}
