package preemptiveTest;

public class PreemptiveTest {
	
	/*
	 * In computing, preemption is the act of temporarily interrupting a task being carried out by a computer system, 
	 * without requiring its cooperation, and with the intention of resuming the task at a later time. 
	 * Such changes of the executed task are known as context switches. 
	 * It is normally carried out by a privileged task or part of the system known as a preemptive scheduler, 
	 * which has the power to preempt, or interrupt, and later resume, other tasks in the system.
	 */
	
	public static void main(String[] args) throws InterruptedException{
		System.out.println("THREAD MAIN BEGINS");
		Thread first = new PreemptiveThread("First Thread");first.start();
		Thread second = new PreemptiveThread("Second Thread");second.start();
		
		try{Thread.sleep(10000);}
		catch(InterruptedException e){}
		
		first.interrupt();
		second.interrupt();
		first.join(); 
		second.join();
	}
}
