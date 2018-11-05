package simpleMultiClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread {
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static int counter = 0;
	private int id = counter++;
	private static int threadCount = 0;
	
	public static int threadCount() {
		return threadCount;
	}
	
	public ClientThread(InetAddress addr) {
		System.out.println("Making client " + id);
		threadCount++;
		
		try{
			socket = new Socket(addr, MiltiJabberServer.PORT);
		}catch(IOException e){System.err.println("Socket failed");}
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			start();
		}catch(IOException e) {
			try {
				socket.close();
			}catch(IOException e2) {
				System.err.println("Socket not closed");
			}
		}
	}
	
	public void run(){
		try {
			for (int i=0; i<25; i++) {
				out.println("Client " + id + ": " + i);
				String str = in.readLine();
				System.out.println(str);
			}
			out.println("END");
		} catch(IOException e) {
			System.err.println("IO Exception");
		} finally {
			try {
				socket.close();
			}catch(IOException e) {
				System.err.println("Socket not closed");
			}
			threadCount--;
		}
	}
}
