package mainPackage;

public class Main {
	
	public static void main(String[] args) throws InterruptedException{
		
		int numPanettieri = (int) (Math.random() * 10 + 1);
		int numClienti = (int) (Math.random() * 10 + 1);
		int capienzaBancone = (int) (Math.random() * 20 + 1);
		
		ThreadGroup consumatori = new ThreadGroup("consumatori");
		ThreadGroup panettieri = new ThreadGroup("panettieri");
		Bancone bancone = new Bancone(capienzaBancone);
		
		System.out.println("Il bancone oggi offre " + capienzaBancone + " panolli.");
		
		for(int i = 0; i< numPanettieri; i++){
			Baker b = new Baker(i, bancone, panettieri);
			b.start();
		}
		
		for(int i =0 ; i< numClienti; i++){
			Customer c = new Customer(i, bancone , consumatori);
			c.start();
		}
		
		Thread.sleep(2000);
		System.out.println("La panetteria sta per chiudere. \n");
		
		
		consumatori.interrupt();
		panettieri.interrupt();
		Thread.sleep(2000);
		System.out.println("Sono avanzati " + bancone.getAvanzi() + " panini.");
		
		
	}

}
