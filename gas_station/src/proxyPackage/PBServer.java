package proxyPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import proxyPackage.PBServerInterface;

public class PBServer {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(PBServerInterface.PORT);
		System.out.println("Server started: " + server );
		
		while(true){
			System.out.println("Waiting a connection.");
			Socket socket = server.accept();
			
			new Thread(new PBServerSkeleton(socket)).start();
		}
	}
}
