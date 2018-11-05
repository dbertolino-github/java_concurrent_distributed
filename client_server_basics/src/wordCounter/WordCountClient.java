package wordCounter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class WordCountClient {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
		InetAddress addr = InetAddress.getByName("localhost");
		System.out.println("addr =" + addr);
		
		Socket socket = new Socket(addr, WordCounterServer.PORT);
		
		try{
			System.out.println("sockte = " + socket);
			BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream()));
			PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
			
			String str = in.readLine();
			System.out.println("words count = " + str);

		}
		finally {
			System.out.println("closing...");
			socket.close();
		}
	}

}
