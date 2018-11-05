package mainPackage;

public class Client extends Thread {
	
	private ProntoSoccorso ps;
	
	public Client(ProntoSoccorso prontoSoccorso, String name){
		super(name);
		this.ps = prontoSoccorso;
	}
	
	public void run(){
		try{
			System.out.println(this.getName() + " arriva alla struttura: " + ps.getID() + ".");
			ps.accettazione(this);
			ps.cura(this);
			ps.exit(this);
			
		}catch(InterruptedException e){}
	}
}
