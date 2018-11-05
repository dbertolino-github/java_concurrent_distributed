package mainPackage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConverterClient {
	
	public static void main(String[] args){
		try{
			Registry registryStub = LocateRegistry.getRegistry(8099);
			CurrencyConverter serverStub = (CurrencyConverter) registryStub.lookup("ConverterServer");
			float  usd = (float) Math.random()*100;
			float  eur = (float) Math.random()*100;
			System.out.println("US value: " + usd);
			System.out.println("EU value: " + eur);
			
			float conv1 = serverStub.toEU(usd);
			float conv2 = serverStub.toEU(eur);
			System.out.println("US value converted in EU: " + conv1);
			System.out.println("EU value converted in US: " + conv2);
			
			
		}
		catch (Exception e){
			System.out.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
