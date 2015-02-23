package com.blasedef.pso.jamesii.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jamesii.core.data.model.read.plugintype.IMIMEType;
import org.jamesii.core.model.Model;
import org.jamesii.core.model.symbolic.convert.IDocument;

import com.blasedef.pso.costfunction.ICostFunction;
import com.blasedef.pso.costfunction.SimpleCostFunction;
import com.blasedef.pso.particle.ActiveParticle;
import com.blasedef.pso.particle.Barrier;
import com.blasedef.pso.particle.IParticle;
import com.blasedef.pso.rng.IRandomNumberGenerator;
import com.blasedef.pso.rng.NoRandomNumberGenerator;
import com.blasedef.pso.space.ISpace;
import com.blasedef.pso.space.Space;
import com.blasedef.pso.space.Tuple;

import static com.blasedef.pso.particle.Particle.OVPROP;
import static com.blasedef.pso.particle.Particle.LOPROP;
import static com.blasedef.pso.particle.Particle.GPPROP;
import static com.blasedef.pso.particle.Particle.GBPROP;

public class MyModel extends Model implements IMyModel {

	private static final long serialVersionUID = -3812094489604427359L;

	public static final String BEST_COST = "Best Cost";
	public static final String BEST_POSITION = "Best position";
	
	//Particle stuff
	@SuppressWarnings("serial")
	HashMap<String,Double> proportions = new HashMap<String,Double>() {{
		put(OVPROP,1.0);
		put(LOPROP,1.0);
		put(GPPROP,0.0);
		put(GBPROP,1.0);
	}};
	
	int group = 0;
	
	IRandomNumberGenerator rng = new NoRandomNumberGenerator();
	PriorityQueue<IParticle> globalQueue = new PriorityQueue<IParticle>();
	HashMap<Integer,PriorityQueue<IParticle>> groupList = new HashMap<Integer,PriorityQueue<IParticle>>();
	Barrier barrier = new Barrier();
	ICostFunction costFunction = new SimpleCostFunction();
	
	Tuple x = new Tuple(0.0,100.0,rng);
	Tuple y = new Tuple(0.0,100.0,rng);
	Tuple z = new Tuple(0.0,100.0,rng);
	
	List<Tuple> tuples = Arrays.asList(x,y,z);
	
	ISpace space = new Space(tuples);	
	
	ArrayList<IParticle> particles = new ArrayList<IParticle>();
	
	public Double bestCost;
	public String bestPosition;

	public MyModel(Map<String, Object> parameters) {
		
		for(int i = 0; i < 10; i++)
			particles.add(new ActiveParticle(proportions, 
					group, 
					rng, 
					null,
					globalQueue, 
					groupList, 
					barrier, 
					costFunction, 
					space));
		
		if (parameters.get(BEST_COST) instanceof Double) {
			bestCost = (Double) parameters.get(BEST_COST);
		}
		if (parameters.get(BEST_POSITION) instanceof String) {
			bestPosition = (String) parameters.get(BEST_POSITION);
		}
	}

	public IDocument<?> getAsDocument(Class<? extends IDocument<?>> targetFormat) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean setFromDocument(IDocument<?> model) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public IMyModel getAsDataStructure() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean setFromDataStructure(IMyModel model) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void removeSource() {
		// TODO Auto-generated method stub
		
	}
	
	public void setSource(String src, IMIMEType mime) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isSourceAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getSource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IMIMEType getSourceMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getBestCost() {
		return globalQueue.peek().getCost();
	}

	@Override
	public String getBestPosition() {
		return globalQueue.peek().getPosition().toString();
	}
	
	public void step(){
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		
		for(IParticle p : this.particles){
			es.execute(p);
		}
		
		es.shutdown();
	}
}
