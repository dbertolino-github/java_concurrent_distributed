package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverInterface extends Remote{
	
	void remoteUpdate(Object observable, Object updateMsg) throws RemoteException;
}
