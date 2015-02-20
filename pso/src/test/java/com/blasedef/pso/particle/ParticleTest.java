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
		
		
		Double originalVelocityProportion = 0.01;
		Double personalBestProportion = 0.01;
		Double groupProportion = 0.01;
		Double globalBestProportion = 0.01;
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
		
		ActiveParticle particle = new ActiveParticle(originalVelocityProportion, 
				personalBestProportion, 
				groupProportion, 
				globalBestProportion, 
				group, 
				rng, 
				null,
				globalQueue, 
				groupList, 
				barrier, 
				costFunction, 
				space);
		
		
		particle.assessCost();
		particle.updateVelocity();
		particle.move(false);
		particle.assessCost();
	
		//System.out.println(particle);
		assertEquals(particle.getCost(), 1450.0);
		
	}
	
	
	public void testAssessParticle2() {
		
		
		Double originalVelocityProportion = 0.01;
		Double personalBestProportion = 0.01;
		Double groupProportion = 0.01;
		Double globalBestProportion = 0.01;
		int group = 0;
		IRandomNumberGenerator rng = new NoRandomNumberGenerator();
		PriorityQueue<IParticle> globalQueue = new PriorityQueue<IParticle>();
		HashMap<Integer,PriorityQueue<IParticle>> groupList = new HashMap<Integer,PriorityQueue<IParticle>>();
		Barrier barrier = new Barrier();
		ICostFunction costFunction = new SimpleCostFunction();
		ISpace space = new Space(null,null,null,null);
		space.addDimension(0.0, 1000.0, rng);
		space.addDimension(0.0, 1000.0, rng);
		space.addDimension(0.0, 1000.0, rng);
		
		ActiveParticle particle = new ActiveParticle(originalVelocityProportion, 
				personalBestProportion, 
				groupProportion, 
				globalBestProportion, 
				group, 
				rng, 
				null,
				globalQueue, 
				groupList, 
				barrier, 
				costFunction, 
				space);
		
		particle.assessCost();
		particle.updateVelocity();
		particle.move(false);
		particle.assessCost();
	
		//System.out.println(particle);
		assertEquals(particle.getCost(), 2500.0);
		
	}
	
	public void testAssessParticle3() {
		
		
		Double originalVelocityProportion = 0.01;
		Double personalBestProportion = 0.01;
		Double groupProportion = 0.01;
		Double globalBestProportion = 0.01;
		
		int group = 0;
		
		IRandomNumberGenerator rng = new NoRandomNumberGenerator();
		PriorityQueue<IParticle> globalQueue = new PriorityQueue<IParticle>();
		HashMap<Integer,PriorityQueue<IParticle>> groupList = new HashMap<Integer,PriorityQueue<IParticle>>();
		
		Barrier barrier = new Barrier();
		ICostFunction costFunction = new SimpleCostFunction();
		
		ISpace space = new Space(null,null,null,null);
		space.addDimension(0.0, 100.0, rng);
		space.addDimension(0.0, 100.0, rng);
		space.addDimension(0.0, 100.0, rng);
		
		ActiveParticle particle1 = new ActiveParticle(originalVelocityProportion, 
				personalBestProportion, 
				groupProportion, 
				globalBestProportion, 
				group, 
				rng, 
				null,
				globalQueue, 
				groupList, 
				barrier, 
				costFunction, 
				space);
		
		ActiveParticle particle2 = new ActiveParticle(originalVelocityProportion, 
				personalBestProportion, 
				groupProportion, 
				globalBestProportion, 
				group, 
				rng, 
				null,
				globalQueue, 
				groupList, 
				barrier, 
				costFunction, 
				space);
		
		particle1.setPosition(0.0,1.0,2.0);
		particle2.setPosition(100.0,99.0,98.0);
		
		particle1.assessCost();
		particle2.assessCost();
		particle1.updateVelocity();
		particle2.updateVelocity();
		particle1.move(false);
		particle2.move(false);
		particle1.assessCost();
		particle2.assessCost();
		
		assertEquals(globalQueue.peek().getCost(), 1003.0);
		
	}
	
}
