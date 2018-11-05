package daemonThreads;

public class DaemonThread extends Thread {
	
	/*
	 * The constructor of this normal Thread
	 * set the Daemon modality to true.
	 */
	public DaemonThread(){
		setDaemon(true);
	}
	
	/*
	 *	This run never ends.
	 */
	public void run(){
		int count = 0;
		while(true) {
			System.out.println("Hello from DaemonThread: " + count++);
			try{
				sleep(500);
			}
			catch(InterruptedException e){}
		}
	}
}
