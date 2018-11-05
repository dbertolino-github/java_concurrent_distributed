package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConferenceInterface extends Remote {
	
	public void postMessage(String x, MemberInterface mi) throws RemoteException;
	public void addListener(MemberInterface mi) throws RemoteException;
	public void removeListener(MemberInterface mi, String name) throws RemoteException;
	
}
