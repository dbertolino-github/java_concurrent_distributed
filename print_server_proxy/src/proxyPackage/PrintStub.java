package proxyPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class PrintStub implements PrintInterface {
	
	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	
	public PrintStub() throws IOException{
		InetAddress addr = InetAddress.getByName("localhost");
		socket = new Socket( addr, PrintInterface.PORT);
		inStream = new ObjectInputStream(socket.getInputStream());
		outStream = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public String[] getPrinterList() {
		try {
			outStream.writeObject(new Message(Message.Command.GET_PRINTER_LIST));
			return (String[]) inStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new String[] {""};
		}	
	}

	@Override
	public boolean print(String printer, String document) {
		try {
			outStream.writeObject(new Message(Message.Command.PRINT, printer, document ));
			return inStream.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void stopPrinting() {
		try{
			outStream.writeObject(new Message(Message.Command.STOP_PRINTING));
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
