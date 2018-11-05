package mainPackage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

public class WrapperObserver implements Observer, Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObserverInterface remoteClient = null;
	
	public WrapperObserver(ObserverInterface ro) {
		this.remoteClient = ro;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		try{
			remoteClient.remoteUpdate(o.toString(), arg);
		}catch (RemoteException e){
			System.out.println("Remote exception removing observer: " + this);
			o.deleteObserver(this);
		}
	}

}
