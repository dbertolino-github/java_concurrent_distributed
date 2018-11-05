package proxyPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;


import javax.swing.text.BadLocationException;

public class Stub implements IServer {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	@Override
	public HTMLDoc sendGet(URI uri) throws IOException, IllegalArgumentException, BadLocationException, URISyntaxException {
		
		HTMLDoc doc = null;
		
		try{
			String path = uri.getPath();
			String host = uri.getHost();
			this.socket = new Socket(host, SERVER_PORT);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter( socket.getOutputStream());
			
			//Sending request
			String request = "GET " + path + " HTTP/1.1\r\n" +
							 "Host: " + host + "\r\n" + 
							 "Connection: close\r\n\r\n";
			out.println(request);
			out.flush();
			
			//re-creating HTML document
			String docString = "";
			String line;
			while ((line = in.readLine()) != null) {
				docString += line + "\n";
			}
			
			doc = new HTMLDoc(docString, uri);
			socket.close();
		}finally {
			if(socket!= null)
				socket.close();
		}
		return doc;
	}

}
