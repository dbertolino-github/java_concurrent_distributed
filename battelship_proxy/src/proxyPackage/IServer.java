package proxyPackage;

import java.io.IOException;

public interface IServer {
	
	public static final int PORT = 9999;
	
	public String startNewGame(String n) throws IllegalArgumentException, IOException;
	public String getBoard() throws IOException;
	public int getNumMissing() throws IOException;
	public String shoot(int r, int c) throws IOException;
	public double getScore() throws IOException;
	public void close() throws IOException;

}
