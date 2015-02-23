package com.blasedef.pso.jamesii.model;

import org.jamesii.core.model.formalism.Formalism;

public class MyModelFormalism extends Formalism {

  private static final long serialVersionUID = 1952345754598770326L;

  public MyModelFormalism() {
    super("SAMPLE", "SAMPLE", "My Model Formalism",
        "...",
        TimeBase.CONTINUOUS, SystemSpecification.DISCRETE, TimeProgress.EVENT);
  }
}
