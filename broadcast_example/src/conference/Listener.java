package conference;

import broadcasts.Broadcast;

public class Listener extends Thread {
	
	Broadcast<String> broadcast;
	
	public Listener(Broadcast<String> wM){
		broadcast = wM;
		setName("Ascoltatore " + getName());
	}
	
	public void run() {
		try{
			System.out.println(getName() + " in attesa di ascoltare");
			int numAscolti = ((int)(Math.random()*10+1));
			for(int i =0; i< numAscolti; i++){
				String msg = broadcast.receive();
				System.out.println(getName() + " ha sentito: " + msg);
			}
		}catch(InterruptedException e ){
			e.printStackTrace();
		}
	}

}
