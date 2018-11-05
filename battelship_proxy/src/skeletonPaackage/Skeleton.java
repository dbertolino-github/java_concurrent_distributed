package skeletonPaackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import proxyPackage.IServer;


public class Skeleton implements  Runnable, IServer {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private RealServer server;
	
	public Skeleton(Socket s){
		server = new RealServer();
		socket = s;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void run() {
		
		String protocol;
		try {
			while(true){
				
				protocol= in.readUTF();
				
				if(protocol.startsWith("NEW")){
					String result = startNewGame(protocol);
					out.writeUTF(result);
				}
				else if(protocol.startsWith("SHOW")){
					String board = getBoard();
					out.writeUTF(board);
				}
				else if(protocol.startsWith("MISSING")){
					Integer missing = getNumMissing();
					out.writeUTF(missing.toString());
				}
				else if(protocol.startsWith("SHOOT")){
					String[] n = protocol.split("\\s");
					Integer row = Integer.parseInt(n[1]);
					Integer column = Integer.parseInt(n[2]);
					String feedback = shoot(row, column);
					out.writeUTF(feedback);
				}
				else if(protocol.startsWith("SHOW")){
					String board = getBoard();
					out.writeUTF(board);
				}
				else if(protocol.startsWith("MISSING")){
					Integer missing = getNumMissing();
					out.writeUTF(missing.toString());
				}
				else if(protocol.startsWith("SCORE")){
					Double score = getScore();
					out.writeUTF(score.toString());
				}
				else if(protocol.startsWith("END")){
					close();
					break;
				}
			}	
		} 
		catch (IOException e) {e.printStackTrace();}
		
	}

	@Override
	public String startNewGame(String n) throws IllegalArgumentException, IOException {
		return server.startNewGame(n);
	}

	@Override
	public String getBoard() throws IOException {
		return server.getBoard();
	}

	@Override
	public int getNumMissing() throws IOException {
		return server.getNumMissing();
	}

	@Override
	public String shoot(int r, int c) throws IOException {
		return server.shoot(r, c);
	}

	@Override
	public double getScore() throws IOException {
		return server.getScore();
	}

	@Override
	public void close() throws IOException {
		socket.close();
		server.close();
		
	}

	
}
