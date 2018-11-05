package mainPackage;

public class Main {
	
	public static void main(String[] args){
		
		int NUM_THREADS = (int)(Math.random()*20 + 1);
		int NUM_POINTS = (int)(Math.random()*1000000 +1); 
		int TOTAL_THROWS = NUM_THREADS*NUM_POINTS;
		
		PiReduce reduce = new PiReduce(TOTAL_THROWS);
		Barrier barrier = new Barrier(NUM_THREADS, reduce);
		
		for(int i = 0; i< NUM_THREADS; i++){
			new PiMap(NUM_POINTS, barrier, i).start();
		}
	}
	
	
}
