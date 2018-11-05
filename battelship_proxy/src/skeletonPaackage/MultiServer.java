package skeletonPaackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import proxyPackage.IServer;

public class MultiServer  {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(IServer.PORT);
		System.out.println("Started: " + serverSocket);
		
		while(true){
			System.out.println("Waiting communications... ");
			Socket socket = serverSocket.accept();
			
			new Thread(new Skeleton(socket)).start();
			
		}
	}
	
	
}
