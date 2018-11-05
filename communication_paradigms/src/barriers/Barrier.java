package barriers;

public class Barrier {
		
	private int need;
	private int arrived;
	private boolean releasing;
	
	public Barrier(int number){
		need = number;
		arrived = 0;
		releasing = false;
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
				System.out.println("All " + need+" parties are arrived at barrier");
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
}
