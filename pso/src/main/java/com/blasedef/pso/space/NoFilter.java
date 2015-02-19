package com.blasedef.pso.space;

import com.blasedef.pso.particle.IPosition;

public class NoFilter implements IFilter {

	public IPosition filter(IPosition position) {
		return position;
	}

}
