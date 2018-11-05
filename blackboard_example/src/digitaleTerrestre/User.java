package digitaleTerrestre;

import blackboards.Blackboard;

public class User extends Thread {
	
	String name;
	Blackboard<Program> blackboard;
	
	User(Blackboard<Program> b){
		this.blackboard = b;
		this.name = "User " + getName();
	}
	
	public void run(){
		try{
			System.out.println(this.name + ": attendo un programma.");
			Program msg = blackboard.read();
			System.out.println(this.name + ": sto guardando " + msg.toString());
			
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
