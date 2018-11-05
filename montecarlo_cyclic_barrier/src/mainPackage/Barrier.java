package mainPackage;

public class Barrier {
		
	private int need;
	private int arrived;
	private boolean releasing;
	private PiReduce reduce;
	
	public Barrier(int number, PiReduce reduce){
		need = number;
		arrived = 0;
		releasing = false;
		this.reduce = reduce;
	}
	
	public synchronized int value() {
		return arrived;
	}
	
	public int capacity() {
		return need;
	}
	
	public synchronized void waitB() throws InterruptedException {
		while (releasing) {
			wait();
		}
		try{
			arrived++;
			while(arrived != need && !releasing){
				wait();
			}
			if(arrived == need) {
				releasing = true;
				System.out.println("\nAll " + need +" Threads are arrived at barrier.\n");
				reduce.start();
				notifyAll();
			}
		}finally {
			arrived --;
			if (arrived == 0) {
				releasing = false;
				notifyAll();
			}
		}
	}
	
	public synchronized void addHits(int n) {
		this.reduce.addhits(n);
	}
	
	
}
