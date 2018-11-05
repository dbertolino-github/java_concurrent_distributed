package Signals;

public class TransientSignal extends Signal {
	
	protected int waiting = 0;
	public synchronized void send() {
		if(waiting > 0){
			super.send();
		}
	}
	
	public synchronized void waits() throws InterruptedException {
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

