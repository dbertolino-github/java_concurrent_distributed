package mainPackage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ObserverInterface {

	private static final long serialVersionUID = 1L;

	protected Client() throws RemoteException {
		super();
	}

	@Override
	public void remoteUpdate(Object observable, Object updateMsg) throws RemoteException {
		System.out.println("got message: " + updateMsg);
	}
	
	public static void main(String[] args ){
		try{
			Registry registry = LocateRegistry.getRegistry(9999);
			ObservableInterface server = (ObservableInterface) registry.lookup("Server");
			Client client = new Client();
			server.addObserver(client);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
