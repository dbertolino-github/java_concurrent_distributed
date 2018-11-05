package SalesTransientSignal;

import Signals.SignalWaiter;

public class Client extends Thread {
	
	private SignalWaiter doorShop;
	
	public Client(SignalWaiter door){
		doorShop = door;
		setName("Client " + getName());
	}
	
	public void run(){
		System.out.println(getName() + " is sitting in the queue.");
		try{
			doorShop.waitSignal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName() + " enter in the shop.");
	}
	
}
