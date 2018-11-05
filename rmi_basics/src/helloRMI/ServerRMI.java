package helloRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI implements HelloInterface {

	public ServerRMI(){}

	@Override
	public String sayHello() throws RemoteException {
		return "Hello, world of the nigggaaaas!";
	}

	@Override
	public String sayHello(Person me) throws RemoteException {
		return "Hello, " + me.getName() + ".\n You're too cute to be a niggg!";
	}
	
	public static void main(String[] args){
		try{
			
			/*
			 * We export the object through RMI in order to make it 
			 * visible to the client.
			 */
			ServerRMI myServer = new ServerRMI();
			HelloInterface stub = (HelloInterface) UnicastRemoteObject.exportObject(myServer, 0);
			
			/*
			 * We insert the remote object's stub in the RMI registry
			 * giving it a name to recognize it.
			 * It will remain listening on the 1099 port.
			 */
			Registry registryRMI = LocateRegistry.createRegistry(8099);
			registryRMI.rebind("HelloServer", stub);
			
			System.err.println("Server ready");
			
		} catch(Exception e){
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
