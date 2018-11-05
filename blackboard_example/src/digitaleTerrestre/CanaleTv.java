package digitaleTerrestre;

import blackboards.Blackboard;

public class CanaleTv extends Thread {
	
	String name;
	Blackboard<Program> blackboard;
	Program[] programmazione;
	
	CanaleTv(Blackboard<Program> b, String nomeCanale, Program[] progs){
		this.blackboard = b;
		this.name = nomeCanale;
		this.programmazione = progs;
	}
	
	public void run() {
		for (int i = 0; i < programmazione.length; i++){
			System.out.println(this.name + " trasmette alle " + programmazione[i]);
			blackboard.write(programmazione[i]);
			try{Thread.sleep((long)(Math.random()*1000));}
			catch(InterruptedException e){e.printStackTrace();}
		}
	}
}
