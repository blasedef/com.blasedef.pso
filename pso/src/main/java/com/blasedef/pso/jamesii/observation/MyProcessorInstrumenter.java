package com.blasedef.pso.jamesii.observation;

import java.util.ArrayList;
import java.util.List;

import com.blasedef.pso.jamesii.simulator.MyProcessorState;

import org.jamesii.core.experiments.instrumentation.computation.IComputationInstrumenter;
import org.jamesii.core.experiments.tasks.IComputationTask;
import org.jamesii.core.observe.IObservable;
import org.jamesii.core.observe.IObserver;
import org.jamesii.core.observe.Mediator;

public class MyProcessorInstrumenter implements IComputationInstrumenter {

  private static final long serialVersionUID = 3448918204005157998L;

  List<IObserver<?>> observers = new ArrayList<>();

  @Override
  public List<? extends IObserver<? extends IObservable>> getInstantiatedObservers() {
    return observers;
  }

  @Override
  public void instrumentComputation(IComputationTask computation) {
    MyProcessorState procState =
        (MyProcessorState) computation.getProcessorInfo().getLocal().getState();
    Mediator.create(procState);
    IObserver<?> observer = new MyProcessorObserver();
    procState.registerObserver(observer);
    observers.add(observer);
  }

}
