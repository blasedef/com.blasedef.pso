package com.blasedef.pso.costfunction;

import com.blasedef.pso.particle.IPosition;

public class SimpleCostFunction implements ICostFunction {

	public void assessCost(IPosition p) {
		
		Double temp = 0.0;
		Double target = 0.0;
		
		for(int i = 0; i < p.getSize(); i++){
			temp = temp + p.getPosition(i);
		}
		
		p.setCost(temp - target);

	}

}
