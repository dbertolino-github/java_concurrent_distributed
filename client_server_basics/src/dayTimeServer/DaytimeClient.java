package dayTimeServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class DaytimeClient {
	
	public static void main(String[] args) throws IOException{
		InetAddress addr = InetAddress.getByName("localhost");
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, DaytimeServer.DEFAULT_PORT);
		
		try{
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String data = in.readLine();
			System.out.println("Oggi è il: " +data);
		}finally {
			System.out.println("closing... ");
			socket.close();
		}
	}
}
