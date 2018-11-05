package Signals;

public class TransientSignal extends Signal {
	
	protected int waiting = 0;
	public synchronized void sendSignal() {
		if(waiting > 0){
			super.sendSignal();
		}
	}
	
	public synchronized void waitSignal() throws InterruptedException {
		try {
			while(!arrived){
				waiting++;
				wait();
				waiting--;
			}
			arrived = false;
		} catch (InterruptedException ie) {
			waiting--;
			throw ie;
		}
	}
}

