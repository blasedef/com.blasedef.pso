/**
 * This code is in the public domain.
 */
package com.blasedef.pso.jamesii.observation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.blasedef.pso.jamesii.simulator.MyProcessor;
import com.blasedef.pso.jamesii.simulator.MyProcessorState;

import org.jamesii.core.observe.NotifyingObserver;
import org.jamesii.core.util.misc.Pair;
import org.jamesii.gui.visualization.chart.plot.IPlot;
import org.jamesii.gui.visualization.chart.plotter.IIncrementalPlotableObserver;

/**
 * This observer retrieves all desired data from {@link MyProcessorState}.
 * 
 * If {@link org.jamesii.core.observe.IStoringObserver} would also be
 * implemented, the data could be stored with one of the available
 * {@link org.jamesii.core.data.storage.IDataStorage} implementations. The
 * current implementation (of {@link IIncrementalPlotableObserver}) allows an
 * automatic display of the relevant variables.
 * 
 * @author Julius No
 * 
 */
public class MyProcessorObserver extends NotifyingObserver<MyProcessorState>
    implements IIncrementalPlotableObserver<MyProcessorState> {

  private static final long serialVersionUID = -58861874513983692L;

  Pair<Double, Double> lastObservation;

  /**
   * This method is called when {@link MyProcessor#nextStep()} calls
   * {@link MyProcessorState#changed()}.
   */
  @Override
  public void handleUpdate(MyProcessorState entity) {
    lastObservation =
        new Pair<>(entity.getTime(), entity.getCost());
  }

  /**
   * This method can be used by components that can process multi-variate
   * time-stamped data to read it from the observer. One such component is the
   * line plot that comes with the JAMES II GUI.
   */
  @Override
  public Pair<? extends Number, ? extends Number> getCurrentData(String varName) {
    Pair<Double, Double> newObservation = null;
    newObservation = lastObservation;
    lastObservation = null;
    return newObservation;
  }

  // The following is not displayed by the simple line plot, but can be used by
  // custom visualizations:

  @Override
  public List<String> getVariableNames() {
    List<String> variableNames = new ArrayList<>();
    variableNames.add("Beam Power");
    return variableNames;
  }

  @Override
  public String getPlotTitle() {
    return "Path to World Domination";
  }

  @Override
  public List<String> getAxisNames() {
    List<String> axisNames = new ArrayList<>();
    axisNames.add("Time");
    axisNames.add("Beam Power");
    return axisNames;
  }

  @Override
  public List<String> getAxisUnits() {
    List<String> axisUnits = new ArrayList<>();
    axisUnits.add("Seconds");
    axisUnits.add("TeV");
    return axisUnits;
  }

  @Override
  public Pair<? extends Number, ? extends Number> getMin() {
    return null;
  }

  @Override
  public Pair<? extends Number, ? extends Number> getMax() {
    return null;
  }

  @Override
  public IPlot getPlotFor(String name, Color color) {
    return null;
  }

  @Override
  public boolean isMonotonIncreasingX(String varName) {
    return true;
  }

}
