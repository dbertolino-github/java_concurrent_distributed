package skeletonPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class PrintSkeleton implements PrintInterface, Runnable {
	
	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	
	public PrintSkeleton(Socket s) throws IOException{
		this.socket = s;
		inStream = new ObjectInputStream(socket.getInputStream());
		outStream = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public String[] getPrinterList() {
		return null;
	}

	@Override
	public boolean print(String printer, String document) {
		return false;
	}

	@Override
	public void stopPrinting() {
		try{
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try{
			while(!socket.isClosed()){
				Message mess = (Message) inStream.readObject();
				if(mess.getCmd() == Message.Command.GET_PRINTER_LIST){
					outStream.writeObject(getPrinterList());
					outStream.flush();
				}
				else if (mess.getCmd() == Message.Command.PRINT){
					boolean ret = print(mess.getDocument(), mess.getPrinter());
					outStream.writeBoolean(ret);
					outStream.flush();
				}
				else if (mess.getCmd() == Message.Command.STOP_PRINTING){
					stopPrinting();
				}
				else {
					System.out.println("operation not recognized nigg: " + mess);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			stopPrinting();
		}
		
	}
	
	@SuppressWarnings("unused")
	private String combine(String[] s , String glue) {
		int k = s.length;
		if(k==0)
			return null;
		StringBuilder out = new StringBuilder();
		out.append(s[0]);
		for(int x = 1; x < k; ++x)
			out.append(glue).append(s[x]);
		return out.toString();
	}

}
