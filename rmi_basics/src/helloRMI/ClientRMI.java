package helloRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
	
	private ClientRMI(){};
	
	public static void main(String[] args){
		
		try{
			/*
			 * The client obtains the registry stub on the same port were was created by the server,
			 * and then it gets the the server stub with the "lookup method.
			 */
			Registry registryStub = LocateRegistry.getRegistry(8099);
			HelloInterface serverStub = (HelloInterface) registryStub.lookup("HelloServer");
			String received = serverStub.sayHello();
			System.out.println(received);
			
			Person me = new Person("Dario MonPert");
			received = serverStub.sayHello(me);
			System.out.println(received);
			
		} catch (Exception e){
			System.err.println("Client exception: " + e.toString());
			e.getStackTrace();
		}
	}

}
