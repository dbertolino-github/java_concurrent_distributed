package SalesTransientSignal;

import Signals.SignalSender;
import Signals.SignalWaiter;

public class OpenedDoorSignal implements SignalSender, SignalWaiter {
	
	private boolean arrived;
	private int waiting;
	
	public OpenedDoorSignal(){
		arrived = false;
		waiting = 0;
	}
	
	@Override
	public synchronized void waitSignal() throws InterruptedException {
		try{
			while(!arrived){
				waiting++;
				wait();
				waiting--;
			}
			if(waiting == 0){
				arrived = false;
			}
		} catch (InterruptedException ie) {
			waiting--;
			throw ie;
		}
	}

	@Override
	public synchronized void sendSignal() {
		if(waiting > 0) {
			arrived = true;
			notifyAll();
		}
	}

}
