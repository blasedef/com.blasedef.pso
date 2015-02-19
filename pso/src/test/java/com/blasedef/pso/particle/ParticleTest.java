package com.blasedef.pso.particle;

import java.util.HashMap;
import java.util.PriorityQueue;

import com.blasedef.pso.costfunction.ICostFunction;
import com.blasedef.pso.costfunction.SimpleCostFunction;
import com.blasedef.pso.rng.IRandomNumberGenerator;
import com.blasedef.pso.rng.NoRandomNumberGenerator;
import com.blasedef.pso.space.ISpace;
import com.blasedef.pso.space.Space;

import junit.framework.TestCase;

public class ParticleTest extends TestCase {

	public void testAssessParticle1() {
		
		
		Double originalVelocityProportion = 0.5;
		Double personalBestProportion = .5;
		Double groupProportion = .5;
		Double globalBestProportion = .5;
		int group = 0;
		IRandomNumberGenerator rng = new NoRandomNumberGenerator();
		PriorityQueue<IParticle> globalQueue = new PriorityQueue<IParticle>();
		HashMap<Integer,PriorityQueue<IParticle>> groupList = new HashMap<Integer,PriorityQueue<IParticle>>();
		Barrier barrier = new Barrier();
		ICostFunction costFunction = new SimpleCostFunction();
		ISpace space = new Space(null,null,null,null);
		space.addDimension(0.0, 100.0, rng);
		space.addDimension(100.0, 200.0, rng);
		space.addDimension(200.0, 300.0, rng);
		
		Particle particle = new Particle(originalVelocityProportion, 
				personalBestProportion, 
				groupProportion, 
				globalBestProportion, 
				group, 
				rng, 
				globalQueue, 
				groupList, 
				barrier, 
				costFunction, 
				space);
		
		
		particle.assessCost();
		particle.updateVelocity();
		particle.move(false);
		particle.assessCost();
		assertEquals(particle.getCost(), 1600.0);
		
	}
	
}
