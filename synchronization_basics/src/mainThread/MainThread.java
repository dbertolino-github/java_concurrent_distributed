package mainThread;

public class MainThread {
	
	/*
	 * When you print a Thread the result is composed in this way:
	 * Thread[ nameOfThread, priority, threadGroup ]
	 * 
	 * This method simply print out the current main Thread twice.
	 */
	public static void main(String[] args){
		
		Thread t = Thread.currentThread();							//Obtaining Thread instance.
		System.out.println("Il main Thread è: " + t);
		
		
		t.setName("MyMainThread");									//modify name of the thread.
		System.out.println("Ora il MainThread si chiama: " + t);
	}
}

