package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	
	public String toUpperCase(String x) throws RemoteException;
	public void addListener(ListenerInterface rl) throws RemoteException;
	public void removeListener(ListenerInterface rl) throws RemoteException;
}
