package broadcasts;


public class Broadcast<Data> {
	
	private Data message;
	private boolean arrived;
	private int waiting;
	
	public Broadcast() {
		arrived = false;
		waiting = 0;
	}
	
	public synchronized void send(Data m){
		System.out.println("Persone in ascolto =" + waiting);
		if(waiting != 0 && !arrived){
			message = m;
			arrived = true;
			notifyAll();
		}
	}
	
	public synchronized Data receive() throws InterruptedException {
		try{
			while (!arrived){
				waiting++;
				wait();
				waiting--;
			}
			if(waiting == 0) {
				arrived = false;
			}
		}catch (InterruptedException ie){
			if (--waiting == 0){
				arrived = false;
			}
		}
		return message;
	}
	
}
