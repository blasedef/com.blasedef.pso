package com.blasedef.pso.space;

import java.util.ArrayList;

public interface IBoundaryAction {

	public Double fit(int index, double d);
	
	public void addSpace(ArrayList<Tuple> space);

}
