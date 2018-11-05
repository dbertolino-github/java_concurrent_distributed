package callCenterPersistentSignal;

import java.util.ArrayList;

import Signals.PersistentSignal;

public class CallCenter {
	
	public static void main(String[] args) throws InterruptedException {
		int callsNumber = (int) (Math.random()*10);
		int operatorsNumber = (int) (Math.random()*10+2);
		PersistentSignal callCenterSystem = new PersistentSignal();
		ArrayList<Operator> operators = new ArrayList<Operator>();
		
		for(int i = 0; i < operatorsNumber; i++){
			operators.add((new Operator(i , callCenterSystem)));
			operators.get(i).start();
		}
		
		for(int j=0; j < callsNumber; j++){
			try{
				callCenterSystem.sendSignal();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < operatorsNumber; i++){
			operators.get(i).join();
		}
		
		System.out.println("Today the call center gave work to " + operatorsNumber + " operators,\n"
						   + "receiving " + callsNumber + " phone calls.");
	}
}
