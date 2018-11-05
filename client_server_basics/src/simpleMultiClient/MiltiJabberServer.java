package simpleMultiClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiltiJabberServer {
	
	static final int PORT = 8080;
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Server started");
		try{
			while(true) {
				Socket socket = server.accept();
				try{
					new ServerThread(socket);
				}catch(IOException e) {
					socket.close();
				}
			}
		} finally {
			server.close();
		}
	}
}
