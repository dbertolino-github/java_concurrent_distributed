package mainPackage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Observable;

public class Server extends Observable implements ObservableInterface , Runnable  {
	
	public Server(){
		new Thread(this).start();
	}
	
	@Override
	public void addObserver(ObserverInterface o) throws RemoteException {
		WrapperObserver wo = new WrapperObserver(o);
		addObserver(wo);
		System.out.println("Added observer: " + wo);
	}
	
	
	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(5*1000);
			} catch (InterruptedException e){
				//ignore
			}
			setChanged();
			notifyObservers(new Date());
		}
	}
	
	public static void main(String[] args){
		try{
			Server server = new Server();
			ObservableInterface serverStub = (ObservableInterface) UnicastRemoteObject.exportObject(server, 0);
			
			Registry registryStub = LocateRegistry.createRegistry(9999);
			registryStub.rebind("Server", serverStub);
			
			System.err.println("Server ready");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	

	
}
