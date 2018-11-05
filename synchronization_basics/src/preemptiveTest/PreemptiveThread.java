package preemptiveTest;

public class PreemptiveThread extends Thread {
	
	public PreemptiveThread(String string) {
		super();
		this.setName(string);
	}

	@Override
	public String toString(){
		String n = Thread.currentThread().getName();
		return n;
	}
	
	public void run(){
		while(true){
			System.out.println(this + " is still running.");
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				System.err.println(this + " was Interrupted");
				break;
			}
		}
	}
}
