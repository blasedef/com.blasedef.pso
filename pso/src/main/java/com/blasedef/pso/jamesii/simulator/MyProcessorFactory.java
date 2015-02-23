/**
 * This code is in the public domain.
 */
package com.blasedef.pso.jamesii.simulator;

import java.util.ArrayList;
import java.util.List;

import com.blasedef.pso.jamesii.model.IMyModel;

import org.jamesii.core.distributed.partition.Partition;
import org.jamesii.core.experiments.tasks.IComputationTask;
import org.jamesii.core.factories.Context;
import org.jamesii.core.model.IModel;
import org.jamesii.core.parameters.ParameterBlock;
import org.jamesii.core.processor.IProcessor;
import org.jamesii.core.processor.ProcessorInformation;
import org.jamesii.core.processor.plugintype.JamesProcessorFactory;
import org.jamesii.core.simulationrun.ISimulationRun;

/**
 * This is the factory for {@link MyProcessor}.
 * 
 * <p/>
 * It declares which model interfaces the processor supports (see
 * {@link #getSupportedInterfaces()}), how efficient it is with respect to other
 * implementations (see {@link #getEfficencyIndex()}), and whether it supports a
 * parallel and distributed execution (see {@link #supportsSubPartitions()}).
 * 
 * @author Julius No
 */
public class MyProcessorFactory extends JamesProcessorFactory {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1208999950753808614L;

  @Override
  public IProcessor<?> create(IModel model, IComputationTask computationTask,
      Partition partition, ParameterBlock params, Context context) {
    ISimulationRun simulationRun = (ISimulationRun) computationTask;
    MyProcessor p = new MyProcessor((IMyModel) model);
    p.setComputationTask(simulationRun);
    simulationRun.setProcessorInfo(new ProcessorInformation(p));
    return p;
  }

  @Override
  public double getEfficencyIndex() {
    return 1;
  }

  @Override
  public List<Class<?>> getSupportedInterfaces() {
    List<Class<?>> interfaces = new ArrayList<>();
    interfaces.add(IMyModel.class);
    return interfaces;
  }

  @Override
  public boolean supportsSubPartitions() {
    return false;
  }

}
