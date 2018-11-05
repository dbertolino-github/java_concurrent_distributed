package dayTimeServer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {
	
	public final static int DEFAULT_PORT = 13;
	
	public static void main(String[] args) {
		System.out.println("Server is getting ready.");
		try {
			//Initialize server
			ServerSocket server = new ServerSocket(DEFAULT_PORT);
			Socket connection = null;
			try{
				//Preparing server to accepts client
				System.out.println("Server ready!");
				connection = server.accept();
				
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				
				out.write(now.toString() + "\r\n");
				out.flush();
				
				connection.close();
			}catch(IOException ex) {}
			finally {
			try {
				if(connection != null) {connection.close();}
				System.out.println("Server is closing.");						
					server.close();
				}catch(IOException ex) {}	
			}	
	}catch(IOException ex) {}
	}
}
