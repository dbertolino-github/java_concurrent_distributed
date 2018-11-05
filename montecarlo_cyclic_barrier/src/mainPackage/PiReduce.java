package mainPackage;

import java.util.ArrayList;

public class PiReduce extends Thread {
	
	private ArrayList<Integer> hits;
	private int totalThrows;
	private int totalHits;
	private double finalPI;
	
	public PiReduce (int tT){
		this.setName("PiReduce");
		this.totalThrows = tT;
		hits = new ArrayList<Integer>();
	}
	
	public void run(){ 
		computeTotalHits();
		computeFinalPI();
		double difference = (Math.PI) - finalPI;
		System.out.println(getName() + " [Total throws: " + totalThrows + "]" + " [final PI: " + finalPI + "]" + " [difference: " + difference + "]");
	}
	
	public void computeFinalPI(){
		finalPI = (4 * ((double)totalHits/(double)totalThrows));
	}
	
	public void addhits(Integer tH){
		this.hits.add(tH);
	}
	
	public void computeTotalHits() {
		int result = 0;
		for(int i = 0; i < hits.size(); i++){
			result += hits.get(i);
		}
		totalHits = result;
	}

}
