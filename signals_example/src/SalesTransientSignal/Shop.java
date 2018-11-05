package SalesTransientSignal;

import Signals.SignalSender;

public class Shop extends Thread {
	
	private SignalSender doorShop;
	
	public Shop(SignalSender door){
		doorShop = door;
		setName("Shop " + getName());
	}
	
	public void run(){
		while(true) {
			try {
				Thread.sleep(10000);
				System.out.println("IL NEGOZIO APRE LE PORTE!");
				doorShop.sendSignal();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
