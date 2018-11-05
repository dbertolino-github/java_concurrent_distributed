package simpleMultiClient;

import java.io.IOException;
import java.net.InetAddress;

public class MultiJabberClient {
	
	static final int MAX_THREADS = 40;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		InetAddress addr = InetAddress.getByName("localhost");
		
		while(true) {
			if (ClientThread.threadCount() < MAX_THREADS) {
				new ClientThread(addr);
			}
			Thread.currentThread();
			Thread.sleep(100);
		}
	}
}
