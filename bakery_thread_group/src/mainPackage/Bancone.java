package mainPackage;

public class Bancone {
	
	private int BUFFERSIZE;
	private int numItems;
	
	public Bancone(int n){
		this.BUFFERSIZE = n;
		this.numItems = 0;
	}
	
	public synchronized void produci() throws InterruptedException{
		while(numItems == BUFFERSIZE){
			wait();
		}
		numItems = numItems +1;
		notify();
	}
	
	public synchronized void consuma() throws InterruptedException{
		while(numItems == 0){
			wait();
		}
		numItems = numItems -1;
		notify();
	}
	
	public int getAvanzi(){
		return  numItems;
	}

}
