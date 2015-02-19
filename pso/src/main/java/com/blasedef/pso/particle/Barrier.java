package com.blasedef.pso.particle;

import java.util.ArrayList;

public class Barrier {
	
	private ArrayList<IParticle> players;
	private int counter1 = 0;
	private int counter2 = 0;
	
	public Barrier(){
		players = new ArrayList<IParticle>();
	}
	
	public synchronized void add(IParticle p){
		players.add(p);
	}
	
	public synchronized void increment1(){
		counter1++;
	}
	
	public synchronized void increment2(){
		counter2++;
	}
	
	public synchronized boolean barrier1(){
		return (counter1 == players.size());
	}
	
	private synchronized void decrement(){
		
		for(IParticle p : players){
			if(p.isFinished()){
				counter1--;
				counter2--;
				p.setFinished(false);
			}
		}
	}
	
	public synchronized boolean barrier2(){
		decrement();
		return (counter2 == 0);
	}
	

	


}
