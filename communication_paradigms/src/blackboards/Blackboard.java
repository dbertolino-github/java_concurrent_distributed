package blackboards;

public class Blackboard<Data> {
	
	private Data message;
	private boolean statusValid;
	
	public Blackboard(){
		statusValid = false;
	}
	
	public Blackboard(Data m){
		statusValid = true;
		message = m;
	}
	
	public synchronized void write(Data m){
		message = m;
		statusValid = true;
		notifyAll();
	}
	
	public synchronized Data read() throws InterruptedException {
		while(!statusValid){
			wait();
		}
		return message;
	}
	
	public synchronized void clear(){
		statusValid = false;
	}
	
	public boolean dataAvaible() {
		return statusValid;
	}
	
}
