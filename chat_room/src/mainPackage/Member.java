package mainPackage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Member extends Thread implements MemberInterface {
	
	private String name;
	
	public Member(){
		String random = "123xyz";
		String n = "[";
		for(int i=0; i<3; i++){
			n = n + random.charAt((int)(Math.random()*5));
		}
		n = n + "]";
		name = n;
	}
	
	@Override
	public void remoteMessage(String param) throws RemoteException {
		System.out.println(this.getMemberName() + " ho ricevuto un nuovo messaggio da:" + param);
	}
	
	public String getMemberName(){
		return this.name;
	}
	
	public void run(){
		Registry registry;
		try {
			
			registry = LocateRegistry.getRegistry(9999);
			ConferenceInterface conferenceStub = (ConferenceInterface) registry.lookup("conference");
			
			MemberInterface stub = (MemberInterface) UnicastRemoteObject.exportObject(this, 0);
			conferenceStub.addListener(stub);
			
			conferenceStub.postMessage(this.getMemberName() + " Buongiorno a tutti!", this);
			
			int numMessaggi = (int) (Math.random()*99);
			for(int i = 0; i < numMessaggi; i++){
				conferenceStub.postMessage(this.getMemberName() + " Messaggio"+i, this);
				Thread.sleep(1000);
			}
			
			conferenceStub.postMessage(this.getMemberName() + " Arrivederci a tutti!", this);
			conferenceStub.removeListener(stub, this.getMemberName());
			
		} catch (RemoteException | NotBoundException | InterruptedException e) {e.printStackTrace();}
		
	}
	
	public static void main(String[] args){
		new Thread(new Member()).start();
	}

}
