/**
 * This code is in the public domain.
 */
package com.blasedef.pso.jamesii.simulator;

import org.jamesii.core.processor.ProcessorState;

public class MyProcessorState extends ProcessorState {

  private static final long serialVersionUID = 7528228482468603285L;

  Integer steps = 0;
  Double cost = Double.MAX_VALUE;
  
  public Double getTime() {
	    return steps.doubleValue();
  }
  
  public void stepFinished(Double cost) {
	  this.cost = cost;
	  steps++;
  }
  
  public Double getCost() {
	    return cost;
	  }
  
}
