package Signals;

public class PersistentSignal extends Signal implements SignalWaiterOrWatcher {
	
	
	public synchronized void waitSignal() throws InterruptedException {
		while(!arrived){
			wait();
		}
		arrived = false;
	}
	
	public synchronized boolean watchSignal() {
		if (!arrived){
			return false;
		}
		arrived = false;
		return true;
	}

}
