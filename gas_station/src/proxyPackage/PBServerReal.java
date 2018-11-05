package proxyPackage;

import java.util.Hashtable;

import proxyPackage.Distributore;
import proxyPackage.PBServerInterface;

public class PBServerReal implements PBServerInterface {
	
	Hashtable<String,Distributore> mappaDistributori = new Hashtable<String, Distributore>();

	@Override
	public void stop() {
		System.out.println("Connessione in chiusura.");
	}

	@Override
	public void updatePrices(Distributore d) {
		if(mappaDistributori.containsKey(d.getName())){
			mappaDistributori.remove(d.getName());
			mappaDistributori.put(d.getName(), d);
			System.out.println("Distributore aggiornato: " + d.getName());
		}
		else {
			mappaDistributori.put(d.getName(), d);
			System.out.println("Distributore inserito: " + d.getName());
		}
		
	}

	@Override
	public Distributore getPrices(String name) {
		Distributore d = mappaDistributori.get(name);
		return d;
	}

}
