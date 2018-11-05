package mainPackage;

public class Industria {
	
	private Macchina[] macchineAttive;
	private int numeroMacchine;
	private int turniOperatore;
	
	public Industria(){
		numeroMacchine = (int) (Math.random() * 5 + 1);
		turniOperatore = (int) (Math.random() * 10 + 1);
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		Industria ind = new Industria();
		ind.lanciaMacchine();
		Thread.sleep(5000);
		for(int i = 0; i < ind.turniOperatore; i++){
			for(int j = 0; j< ind.macchineAttive.length; j++){
				Macchina m = ind.macchineAttive[j];
				if(!m.isAlive()){
					m = new Macchina();
					m.setName("macchina"+j);
					m.start();
					System.out.println("L'operatore ha fatto ripartire la macchina" + j);
				}
			}
			Thread.sleep(5000);
		}
		System.out.println("L'operatore ha finito i propri turni di lavoro.");
		for(int j = 0; j< ind.macchineAttive.length; j++){
			ind.macchineAttive[j].join();
		}
		System.out.println("L'operatore ha lavorato per: " + ind.turniOperatore + " turni.");
	}
	
	private void lanciaMacchine(){
		this.macchineAttive = new Macchina[this.numeroMacchine];
		for(int i = 0; i<this.macchineAttive.length; i++){
			this.macchineAttive[i] = new Macchina();
			this.macchineAttive[i].setName("macchina"+i);
			this.macchineAttive[i].start();
		}
	}
}
