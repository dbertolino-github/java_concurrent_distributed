package skeletonPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainProgram {

	public static void main(String[] args) throws IOException{
		
		System.out.println("Server started");
		ServerSocket s = new ServerSocket(PrintInterface.PORT);
		
		while(true){
			System.out.println("Server is waiting connection");
			Socket socket = s.accept();
			
			new Thread(new RealServer(socket)).start();
		}
		
	}
	
}
