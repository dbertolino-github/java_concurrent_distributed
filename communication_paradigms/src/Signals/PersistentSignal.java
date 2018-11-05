package Signals;

public class PersistentSignal extends Signal implements SignalWaiterOrWatcher {
	
	public synchronized void waits() throws InterruptedException {
		while(!arrived){
			wait();
		}
		arrived = false;
	}
	public synchronized boolean watch() {
		if (!arrived){
			return false;
		}
		arrived = false;
		return true;
	}
}
