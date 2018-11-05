package mainPackage;

public class Macchina extends Thread {
	
	private Integer tempoDiVita;
	
	public Macchina(){
		tempoDiVita = (int) (Math.random() * 50 +1);
	}
	
	public void run(){
		
		for(int i = 0; i < tempoDiVita; i++){
			int pezzo = produci();
			if(pezzo == 4){this.interrupt();}
			try{Thread.sleep(200);}
			catch(InterruptedException e){break;}
		}
		System.out.println(this + " si è bloccata.");
	}
	
	@Override
	public String toString() {
		String n = this.getName();
		return n;
	}
	
	public int produci(){
		int pezzo = (int) (Math.random() * 4);
		return pezzo;
	}

}
