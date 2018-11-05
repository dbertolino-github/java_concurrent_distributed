package produttoriConsumatori;

import buffers.BoundedBuffer;

public class Market {
	
	public static void main(String[] args){
		
		int bufferDimension = (int) (Math.random()*20+1);
		int numberOfProducers = (int) (Math.random()*300+1);
		int numberOfConsumers = (int) (Math.random()*300+1);
		BoundedBuffer<MyObject> buffer = new BoundedBuffer<MyObject>(bufferDimension);
		
		System.out.println("The Buffer has " + bufferDimension + " blocks of space.");
		
		for(int i=0; i<numberOfProducers; i++){
			new Producer(i, buffer).start();
		}
		
		for(int j=0; j<numberOfConsumers; j++){
			new Consumer(j, buffer).start();
		}
		
	}
}
