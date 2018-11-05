package mainPackage;

import java.util.Random;

public class PiMap extends Thread {
	
	private Random randomGen = new Random();
	private int hits = 0;
	
	private int numThrows;
	private Barrier barrier;
	
	public PiMap(int nT, Barrier barrier, int name){
		this.setName("PiMap " + name);
		this.numThrows = nT;
		this.barrier =barrier;
	}
	
	public void run(){
		
		try {
			double computedPI = computePI();
			double difference = (Math.PI) - computedPI;
			this.barrier.addHits(hits);
			System.out.println(getName()+ " made " + numThrows + " throws,"  + " calculating PI: " + computedPI + ", difference: " + difference);
			this.barrier.waitB();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInside (double xPos, double yPos){
		double distance = Math.sqrt((xPos * xPos) + (yPos * yPos));
		return (distance < 1);
	}
	
	public double computePI(){
		
		for (int i =0; i <= numThrows; i++){
			double xPos = (randomGen.nextDouble()) * 2 - 1.0;
			double yPos = (randomGen.nextDouble()) * 2 - 1.0;
			if (isInside(xPos, yPos)){
				hits++;
			}
		}
		double PI = (4 * ((double)hits/(double)numThrows));
		return PI;
	}
}
