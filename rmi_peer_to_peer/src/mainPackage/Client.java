package mainPackage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ListenerInterface {

	@Override
	public void remoteEvent(Object param) throws RemoteException {
		System.err.println("REMOTE NOTIFICATION: num calls = " + param);
	}
	
	public void run() throws Exception {
		Registry registry = LocateRegistry.getRegistry(8099);
		ServerInterface serverStub = (ServerInterface) registry.lookup("server");
		
		/*
		 * The client become a remote object callable by the server
		 */
		ListenerInterface stub = (ListenerInterface) UnicastRemoteObject.exportObject(this, 0);
		serverStub.addListener(stub);
		
		System.out.println("Chiamata remota: " + serverStub.toUpperCase("test abc"));
		Thread.sleep(1500);
		
		System.out.println("Chiamata remota: " + serverStub.toUpperCase("test def"));
		Thread.sleep(1500);
		
		System.out.println("Chiamata remota: " + serverStub.toUpperCase("test ghi"));
		Thread.sleep(1500);
	}
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.run();
	}

}
