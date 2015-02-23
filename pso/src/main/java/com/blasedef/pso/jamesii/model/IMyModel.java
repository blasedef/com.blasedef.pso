package com.blasedef.pso.jamesii.model;


import org.jamesii.core.model.IModel;
import org.jamesii.core.model.symbolic.ISymbolicModel;

/**
 * 
 */
public interface IMyModel extends IModel, ISymbolicModel<IMyModel> {

	//get best cost
	public Double getBestCost();
	
	//get best position
	public String getBestPosition();
	
	//get all costs
	
	//get all positions
 
	public void step();

}
