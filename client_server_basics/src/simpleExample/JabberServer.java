package simpleExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JabberServer {
	
	public static final int PORT = 8080;
	
	public static void main(String[] args) throws IOException {
		
		//Preparing server
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("started: " + server);
		
		try{
			Socket connection = server.accept();
			try{
				//Open an input and output connection with the client
				System.out.println("Connection accepted: " + connection);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())),true);
				
				//Provide service till the client pass the "END" string
				while(true){
					String str = in.readLine();
					if(str.equals("END")){
						break;
					}
					System.out.println("Echoing: " + str);
					out.println(str);
				}
			}
			finally {
				System.out.println("closing..");
				connection.close(); //Closing client connection
			}
		}
		finally{
			server.close(); //Closing server connection
		}
	}

}
