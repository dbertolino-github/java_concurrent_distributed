package proxyPackage;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class PBServerStub implements PBServerInterface {
	
	private Socket socket;
	private ObjectInput inStream;
	private ObjectOutput outStream;
	
	public PBServerStub()  {
		try {
			
			InetAddress addr = InetAddress.getByName(null);
			socket = new Socket( addr, PBServerInterface.PORT);
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void stop() {
		try {
			outStream.writeObject(new Message(Message.Command.STOP));
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatePrices(Distributore d) {
		try {
			outStream.writeObject(new Message(Message.Command.UPDATE, d));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Distributore getPrices(String name) {
		
		try {
			outStream.writeObject(new Message(Message.Command.READ, name ));
			Distributore d = (Distributore) inStream.readObject();
			return d;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
