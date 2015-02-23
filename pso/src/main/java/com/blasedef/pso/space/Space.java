package com.blasedef.pso.space;

import java.util.ArrayList;
import java.util.List;

import com.blasedef.pso.particle.IPosition;
import com.blasedef.pso.rng.IRandomNumberGenerator;

public class Space implements ISpace {

	private ArrayList<Tuple> space;
	private IBoundaryAction boundaryAction;
	private IFilter filter;
	private IInitialVelocity initialVelocity;
	private IInitialPosition initialPosition;
	
	public Space(){
		space = new ArrayList<Tuple>();
		this.boundaryAction = new BoundaryActionStop(space);
		this.filter = new NoFilter();
		this.initialPosition = new InitialPosition(space);
		this.initialVelocity = new InitialVelocity(space);
	}
	
	public Space(List<Tuple> tuples){
		space = new ArrayList<Tuple>(tuples);
		this.boundaryAction = new BoundaryActionStop(space);
		this.filter = new NoFilter();
		this.initialPosition = new InitialPosition(space);
		this.initialVelocity = new InitialVelocity(space);
	}
	
	public Space(IBoundaryAction boundaryAction, 
			IFilter filter,
			IInitialPosition initialPosition,
			IInitialVelocity initialVelocity,
			List<Tuple> tuples) {
		if(tuples != null)
			space = new ArrayList<Tuple>(tuples);
		else
			space = new ArrayList<Tuple>();
		
		if(boundaryAction == null)
			this.boundaryAction = new BoundaryActionStop(space);
		if(filter == null)
			this.filter = new NoFilter();
		if(initialPosition == null)
			this.initialPosition = new InitialPosition(space);
		if(initialVelocity == null)
			this.initialVelocity = new InitialVelocity(space);
	}

	public void addDimension(Double min, Double max, IRandomNumberGenerator rng){
		space.add(new Tuple(min, max, rng));
	}

	public IPosition filter(IPosition position) {
		return this.filter.filter(position);
	}

	public IBoundaryAction getBoundaryRule() {
		return this.boundaryAction;
	}

	public ArrayList<Double> initialisePosition() {
		return this.initialPosition.initialise();
	}

	public ArrayList<Double> initialiseVelocity() {
		return this.initialVelocity.initialise();
	}

	public Double fit(int index, Double d) {
		return boundaryAction.fit(index, d);
	}

	@Override
	public void addDimension(Tuple tuple) {
		space.add(tuple);
	}
	
}
