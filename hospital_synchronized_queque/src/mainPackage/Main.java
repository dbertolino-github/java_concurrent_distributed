package mainPackage;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		int numClienti = (int)(Math.random()*10+1);
		int numStrutture = (int)(Math.random()*10+2);
		
		ArrayList<ProntoSoccorso> strutture = new ArrayList<ProntoSoccorso>();
		ArrayList<Client> clienti = new ArrayList<Client>();
		
		for(int j=0; j<numStrutture; j++){
			strutture.add(new ProntoSoccorso(j));
		}
		
		for(int i = 0; i < numClienti; i++){
			int rand = (int)(Math.random()* numStrutture);
			clienti.add(new Client(strutture.get(rand) ,"Cliente " + i));
			clienti.get(i).start();
		}	
		for (int i = 0; i < numClienti; i++){
			clienti.get(i).join();
		}
		
		System.out.println("Tutte le strutture sono state chiuse.");
	}
}
