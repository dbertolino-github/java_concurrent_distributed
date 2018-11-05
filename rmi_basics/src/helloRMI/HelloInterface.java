package helloRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
	
	String sayHello() throws RemoteException;
	String sayHello(Person me) throws RemoteException;
}
