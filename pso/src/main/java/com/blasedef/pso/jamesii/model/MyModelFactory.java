package com.blasedef.pso.jamesii.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jamesii.core.model.IModel;
import org.jamesii.core.model.formalism.Formalism;
import org.jamesii.core.model.plugintype.ModelFactory;
import org.jamesii.core.model.symbolic.ISymbolicModel;


public class MyModelFactory extends ModelFactory {

  private static final long serialVersionUID = -717155332955714181L;

  @Override
  public ISymbolicModel<?> create() {
    return new MyModel(new HashMap<String, Object>());
  }

  @Override
  public Formalism getFormalism() {
    return new MyModelFormalism();
  }

  @Override
  public List<Class<? extends IModel>> getSupportedInterfaces() {
    List<Class<? extends IModel>> supported = new ArrayList<>();
    supported.add(IMyModel.class);
    return supported;
  }

}
