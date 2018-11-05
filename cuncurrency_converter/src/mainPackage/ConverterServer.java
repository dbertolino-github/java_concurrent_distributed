package mainPackage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConverterServer implements CurrencyConverter {
	
	public ConverterServer(){};

	@Override
	public float toEU(Float value) throws RemoteException {
		return value*0.895415473F;
	}

	@Override
	public float toUS(Float value) throws RemoteException {
		return value*1.114365F;
	}
	
	public static void main(String[] args){
		
		try {
			ConverterServer server = new ConverterServer();
			CurrencyConverter serverStub = (CurrencyConverter) UnicastRemoteObject.exportObject(server, 0);
			
			Registry registryRMI = LocateRegistry.createRegistry(8099);
			registryRMI.rebind("ConverterServer", serverStub);
			
			System.out.println("Server ready.");
		}catch (Exception e){
			System.out.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	

}
