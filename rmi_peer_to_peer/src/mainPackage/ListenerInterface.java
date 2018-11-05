package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ListenerInterface extends Remote{
	
	public void remoteEvent(Object param) throws RemoteException;
}
