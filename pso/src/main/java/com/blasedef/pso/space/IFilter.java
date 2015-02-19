package com.blasedef.pso.space;

import com.blasedef.pso.particle.IPosition;

public interface IFilter {

	IPosition filter(IPosition position);

}
