package com.blasedef.pso.space;

import java.util.ArrayList;

public class BoundaryActionStop implements IBoundaryAction {

	private ArrayList<Tuple> space;
	
	public BoundaryActionStop(ArrayList<Tuple> space){
		this.space = space;
	}
	
	public Double fit(int index, double d) {
		
		if(space.get(index).isInsideBounds(d))
			return d;
		else 
			if(space.get(index).lessThanMin(d))
				return space.get(index).getMin();
			else
				return space.get(index).getMax();
	}
}
