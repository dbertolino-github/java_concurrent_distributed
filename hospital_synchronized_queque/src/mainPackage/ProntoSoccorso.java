package mainPackage;

public class ProntoSoccorso {
	
	private Boolean free;
	private int waiting;
	private int id;
	
	public ProntoSoccorso(int n){
		waiting = 0;
		free = true;
		this.id = n;
	}
	
	public synchronized void accettazione(Client c) throws InterruptedException{
		Thread.sleep(500);
		if(!free){
			waiting++;
			System.out.println(c.getName() + " si mette in coda.");
			wait();
		}
		free = false;
	}
	
	public synchronized  void cura(Client c) throws InterruptedException{
		System.out.println(c.getName() + " entra in cura.");
		Thread.sleep(1000);
	}
	
	public synchronized void exit(Client c) throws InterruptedException{
		System.out.println(c.getName() + " lascia il pronto soccorso.");
		if(waiting > 0){
			waiting--;
			notify();
		}
		free = true;
	}
	
	public int getID(){
		return this.id;
	}
	
	
	
}
