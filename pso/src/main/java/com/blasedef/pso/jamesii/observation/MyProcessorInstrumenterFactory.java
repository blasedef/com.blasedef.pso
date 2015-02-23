/**
 * This code is in the public domain.
 */
package com.blasedef.pso.jamesii.observation;

import com.blasedef.pso.jamesii.simulator.MyProcessorFactory;

import org.jamesii.core.experiments.instrumentation.computation.IComputationInstrumenter;
import org.jamesii.core.experiments.instrumentation.computation.plugintype.ComputationInstrumenterFactory;
import org.jamesii.core.factories.Context;
import org.jamesii.core.parameters.ParameterBlock;

/**
 * This factory creates the {@link MyProcessorInstrumenter}.
 * 
 * @author Julius No
 */
public class MyProcessorInstrumenterFactory extends
    ComputationInstrumenterFactory {

  private static final long serialVersionUID = 1897888693597530347L;

  /**
   * Check whether the model to be instrumented is supported by
   * {@link eu.quanticol.carma.jamesii.simulator.MyProcessor}.
   */
  @Override
  public int supportsParameters(ParameterBlock parameters) {
    return checkForImplementedModel(parameters,
        new MyProcessorFactory().getSupportedInterfaces());
  }

  @Override
  public IComputationInstrumenter create(ParameterBlock parameter, Context context) {
    return new MyProcessorInstrumenter();
  }

}
