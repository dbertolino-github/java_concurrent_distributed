package daemonThreads;

public class DaemonTest {
	
	
	/*
	 * This main method launch a Daemon Thread which is excecuted with a very low priority 
	 * from the scheduler.
	 * More of that a daemon thread is'nt requested to stop in order to end the main Thread.
	 * Indeed in DaemonThread class the run() never ends.
	 */
	public static void main(String[] args) throws InterruptedException{
		
		new DaemonThread().start();
		
		try{Thread.sleep(1000);}
		catch(InterruptedException e){}
		System.out.println("Main Thread ending");
	}
}
