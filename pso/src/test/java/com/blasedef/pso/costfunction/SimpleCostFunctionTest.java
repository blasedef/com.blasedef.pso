package com.blasedef.pso.costfunction;

import java.util.ArrayList;

import com.blasedef.pso.particle.Position;

import junit.framework.TestCase;

public class SimpleCostFunctionTest extends TestCase {

	public void testAssessCost1() {
		
		Position position = new Position();
		ArrayList<Double> test = new ArrayList<Double>();
		test.add(100.0);
		position.setPosition(test);
		SimpleCostFunction costFunction = new SimpleCostFunction();
		costFunction.assessCost(position);
		Double actual = position.getCost();
		Double expected = 1100.0;
		assertEquals(expected, actual);
	}
	
	public void testAssessCost2() {
		
		Position position = new Position();
		ArrayList<Double> test = new ArrayList<Double>();
		test.add(-100.0);
		position.setPosition(test);
		SimpleCostFunction costFunction = new SimpleCostFunction();
		costFunction.assessCost(position);
		Double actual = position.getCost();
		Double expected = 900.0;
		assertEquals(expected, actual);
	}

}
