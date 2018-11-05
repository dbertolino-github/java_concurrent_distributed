package proxyPackage;

import java.io.IOException;

public class PBClient {
	
	public static void main(String[] args) throws IOException {
		
		PBServerInterface server = null;
		try{
			System.out.println("Client lanciato.");
			server = new PBServerStub();
			int numDistributori = (int) (Math.random() * 10);
			Distributore d = null; 
			
			System.out.println("Inserendo nuovi distributori.");
			for(int i = 0; i < numDistributori; i++){
				d = new Distributore(Integer.toString(i));
				server.updatePrices(d);
			}
			
			System.out.println("Leggendo i dati dei distributori inseriti.");
			for(int i = 0; i < numDistributori; i++){
				d = server.getPrices(Integer.toString(i));
				System.out.println("Il distributore: " + d.getName() +
								   "\nVende ora ai seguenti prezzi: " + "Benzina = " + d.getPrezzoBenzina() + " , Diesel = " + d.getPrezzoDiesel() + "\n" );
			}
			
			System.out.println("Aggiornando i prezzi dei distributori.");
			for(int i = 0; i< numDistributori; i++){
				d = new Distributore(Integer.toString(i));
				server.updatePrices(d);
			}
			
			System.out.println("Leggendo i dati dei distributori aggiornati.");
			for(int i = 0; i < numDistributori; i++){
				d = server.getPrices(Integer.toString(i));
				System.out.println("Il distributore: " + d.getName() +
								   "\nVende ora ai seguenti prezzi: " + "Benzina = " + d.getPrezzoBenzina() + " , Diesel = " + d.getPrezzoDiesel() + "\n" );
			}
			
		}
		finally  {
			server.stop();
		}
	}
}
