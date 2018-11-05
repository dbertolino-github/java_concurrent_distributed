package mainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurrencyConverter extends Remote{
	
	float toEU(Float value) throws RemoteException;
	float toUS(Float value) throws RemoteException;
}
