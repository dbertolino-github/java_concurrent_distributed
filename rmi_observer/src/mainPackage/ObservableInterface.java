package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObservableInterface extends Remote {
	
	void addObserver(ObserverInterface o) throws RemoteException;
}
