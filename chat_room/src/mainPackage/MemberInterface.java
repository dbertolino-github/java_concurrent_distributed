package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MemberInterface extends Remote{
	
	public void remoteMessage(String param) throws RemoteException;
}
