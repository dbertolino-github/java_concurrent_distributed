package mainPackage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Vector;

public class Server extends UnicastRemoteObject implements ServerInterface {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector listeners = new Vector();
	private int numChiamate = 0;
	
	protected Server() throws RemoteException {
		super();
	}

	@Override
	public String toUpperCase(String x) throws RemoteException {
		numChiamate++;
		return x.toUpperCase();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addListener(ListenerInterface rl) throws RemoteException {
		listeners.addElement(rl);
	}

	@Override
	public void removeListener(ListenerInterface rl) throws RemoteException {
		listeners.removeElement(rl);
	}
	
	@SuppressWarnings("rawtypes")
	public void notifyListeners() {
		Vector temp = (Vector) listeners.clone();
		
		for(Enumeration e = temp.elements(); e.hasMoreElements();){
			ListenerInterface rl = (ListenerInterface) e.nextElement();
			try{
				rl.remoteEvent(numChiamate);
			}catch (RemoteException e1){
				listeners.remove(rl);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		Server myServer = new Server();
		Registry registryRMI = LocateRegistry.createRegistry(8099);
		registryRMI.rebind("server", myServer);
		System.err.println("Server Registered");
		
		while(true) {
			myServer.notifyListeners();
			Thread.sleep(1000);
		}
	}
	
}
