package proxyPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Proxy implements IServer {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String chessBoard;
	private Integer missing;
	
	//The constructor initialize communications channels
	public Proxy() throws Exception {
		
		InetAddress addr = InetAddress.getByName("localhost");
		System.out.println("adrr = " + addr);
		socket = new Socket( addr, IServer.PORT);
		
		
		System.out.println("socket = " + socket);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
	}

	@Override
	public String startNewGame(String n) throws IllegalArgumentException, IOException {
		out.writeUTF("NEW " + n);
		out.flush();
		String feedBack = n + "stai giocando con " +in.readUTF();
		return feedBack;		
	}

	@Override
	public String shoot(int r, int c) throws IOException {	
		out.writeUTF("SHOOT " + r + " " + c);
		out.flush();
		String message = in.readUTF();
		return message;
	}

	@Override
	public String getBoard() throws IOException {	
		out.writeUTF("SHOW");
		out.flush();
		chessBoard = in.readUTF();
		return chessBoard;
	}
	
	@Override
	public int getNumMissing() throws IOException {
		out.writeUTF("MISSING");
		out.flush();
		missing = Integer.parseInt(in.readUTF());
		return missing;
		
	}
	
	@Override
	public double getScore() throws IOException {
		out.writeUTF("SCORE");
		out.flush();
		Double score = Double.parseDouble(in.readUTF());
		return score;	
	}

	@Override
	public void close() throws IOException {
		out.writeUTF("END");
		out.flush();
	}

	
	
}
