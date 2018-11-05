package proxyPackage;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import proxyPackage.Message;
import proxyPackage.PBServerInterface;
import proxyPackage.Distributore;

public class PBServerSkeleton implements PBServerInterface, Runnable {
	
	private Socket socket;
	private ObjectOutput outStream;
	private ObjectInput inStream;
	private PBServerReal serverReale;
	
	public PBServerSkeleton(Socket s){
		socket = s;
		serverReale = new PBServerReal();
		try {
			
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Server thread partito.");
		
		while(!socket.isClosed()){
			try {
				
				Message mess = (Message) inStream.readObject();
				if(mess.getCmd().equals(Message.Command.STOP)){
					stop();
				}
				else if(mess.getCmd().equals(Message.Command.UPDATE)){
					Distributore d  = mess.getDistr();
					updatePrices(d);
				}
				else if(mess.getCmd().equals(Message.Command.READ)){
					String name = mess.getDistrName();
					Distributore d = getPrices(name);
					outStream.writeObject(d);
				}
				
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void stop() {
		serverReale.stop();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePrices(Distributore d) {
		serverReale.updatePrices(d);
	}

	@Override
	public Distributore getPrices(String name) {
		Distributore d = serverReale.getPrices(name);
		return d;
	}

	

}
