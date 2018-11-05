package mainPackage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Conference extends UnicastRemoteObject implements ConferenceInterface{

	private static final long serialVersionUID = 1L;

	protected Conference() throws RemoteException {
		super();
	}

	private Vector<MemberInterface> listeners = new Vector<MemberInterface>();
	
	@Override
	public void postMessage(String x, MemberInterface mi) throws RemoteException {
		for(MemberInterface member : listeners){
			if(member.equals(mi)){}
			else{member.remoteMessage(x);}
		}
	}

	@Override
	public void addListener(MemberInterface mi) throws RemoteException {
		listeners.addElement(mi);
		
	}

	@Override
	public void removeListener(MemberInterface mi, String name) throws RemoteException {
		for(MemberInterface member : listeners){
			member.remoteMessage("[Conf-Charmain] Ringraziamo " + name + " per il suo intervento");
		}
		listeners.remove(mi);
	}
	
	public static void main(String[] args) throws Exception{
		Conference conference = new Conference();
		Registry registryRMI = LocateRegistry.createRegistry(9999);
		registryRMI.bind("conference", conference);
		System.err.println("Conference is Open!!");
	}
	

}
