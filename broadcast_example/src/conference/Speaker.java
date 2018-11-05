package conference;

import broadcasts.Broadcast;

public class Speaker extends Thread {
	
	Broadcast<String> broadcast;
	
	public Speaker(Broadcast<String> wM) {
		broadcast = wM;
		setName("speaker" + getName());
	}
	
	public void run() {
		System.out.println(getName() + " : Comincia a lanciare messaggi!");
		broadcast.send(" Benvenuti!");
		for(int i =0; i< 10; i++){
			try{
				Thread.sleep(1000);;
			}catch(InterruptedException e){}
			broadcast.send("Yo niggy bla bla bla " + i);
		}
	}

}
