package com.blasedef.pso.costfunction;

import com.blasedef.pso.particle.IPosition;

public interface ICostFunction {
	
	public void assessCost(IPosition p);

}