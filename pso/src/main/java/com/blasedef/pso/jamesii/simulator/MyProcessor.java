/**
 * This code is in the public domain.
 */
package com.blasedef.pso.jamesii.simulator;

import java.util.logging.Level;

import com.blasedef.pso.jamesii.model.IMyModel;

import org.jamesii.SimSystem;
import org.jamesii.core.math.random.distributions.NormalDistribution;
import org.jamesii.core.processor.IProcessor;
import org.jamesii.core.processor.RunnableProcessor;


public class MyProcessor extends RunnableProcessor<Double> {

	private static final long serialVersionUID = 5195514592324018114L;

	final NormalDistribution normDist = new NormalDistribution(SimSystem
			.getRNGGenerator().getNextRNG(), 0, .05);

  	public MyProcessor(IMyModel model) {
	  super(model);
    	setState(new MyProcessorState());
  	}


  	@Override
  	protected void nextStep() {

  		// If necessary, system-wide logging is supported via methods like this one:
	  	SimSystem.report(Level.INFO, "My current time is:" + getTime());

	  	// Compute the next step
		veryComplicatedCalculation();

		// Notify observers about state changes
    	getModel().changed();
    	getState().changed();
  	}

  	protected void veryComplicatedCalculation() {
	  getMyModel().step();
	  getMyState().stepFinished(getMyModel().getBestCost());
  	}

  	public IMyModel getMyModel() {
	  return (IMyModel) getModel();
  	}

  	public MyProcessorState getMyState() {
	  return (MyProcessorState) getState();
  	}

	@Override
	public Double getTime() {
		return getMyState().getTime();
	}
}
