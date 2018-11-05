package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClient {
	
	public static void main( String[] args ) throws IOException, URISyntaxException {
		                     //host                      //path
		String url = "http://artelab.dista.uninsubria.it/people.html";
		URI uri = new URI(url);
		String host = uri.getHost();
		String path = uri.getRawPath();
		
		System.out.println(uri + "\n" +  host + "\n" + path + "\n\n");
		
		Socket socket = new Socket(host, 80);
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter( socket.getOutputStream());
			
			/*Sending our request created by a REQUEST LINE (method/URL/version) and 
			 * some headers (Host/connection)
			 */
			System.out.println("**** Request to artelab.dista.uninsubria.it:");
			System.out.println("GET " + path + " HTTP/1.1\r\n" + 
							   "Host: " + host + "\r\n" + 
							   "Connection: close\r\n\r\n");
			out.println("GET " + path + " HTTP/1.1\r\n" + " Host: " + host + "\r\n" + "Connection: close\r\n\r\n");
			out.flush();
			
			//read and rebuild answer line after line and then print it on console
			String messageString = "";
			String line;
			while ((line = in.readLine()) != null) {
				messageString += line + "\n";
			}
			System.out.println("**** MESSAGE From " + host + ":\n" + messageString);
			
		} finally {
			System.out.println("**** closing ....");
			socket.close();
		}
		
	}
}
