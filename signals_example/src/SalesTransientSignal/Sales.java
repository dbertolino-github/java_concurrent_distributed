package SalesTransientSignal;

public class Sales {
	
	public static void main(String[] args) {
		OpenedDoorSignal doorShop = new OpenedDoorSignal();
		new Shop(doorShop).start();
		int numClients = (int) (Math.random()*10);
		for (int i = 0; i < numClients; i++){
			new Client(doorShop).start();
		}
		try {Thread.sleep(1000);} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
