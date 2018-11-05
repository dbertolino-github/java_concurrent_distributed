package conference;

import broadcasts.Broadcast;

public class Conference {
	
	public static void main(String[] args) throws InterruptedException {
		Broadcast<String> broadcast = new Broadcast<String>();
		
		System.out.println("Tutto è pronto...");
		for( int i = 0; i< 10; i++){
			new Listener(broadcast).start();
		}
		Thread.sleep(1000);
		System.out.println("Arriva lo speaker!");
		new Speaker(broadcast).start();
	}
}
