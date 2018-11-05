package callCenterPersistentSignal;

import Signals.PersistentSignal;

public class Operator extends Thread {
	
	private PersistentSignal controller;
	
	public Operator( int n , PersistentSignal c){
		super("Operator " + n);
		this.controller = c;
	}
	
	public void run(){
		for(int a=0; a<3; a++){
			try{
				System.out.println(getName() + " is ready to answer a call.");
				if(controller.watchSignal()){
					System.out.println(getName() + " answer now!");
					Thread.sleep(1000);
				}
				System.out.println(getName() + " has nothig to do.");
				Thread.sleep(1000);
			} catch( InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println(getName() + " has just finished his work.");	
	}
	
}
