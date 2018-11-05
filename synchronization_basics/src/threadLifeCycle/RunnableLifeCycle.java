package threadLifeCycle;

public class RunnableLifeCycle implements Runnable {
	
	
	public static void main(String[] args) throws InterruptedException{
		System.out.println("MyThread WILL START QUICKLY.");
		RunnableLifeCycle re = new RunnableLifeCycle();
		Thread t = new Thread(re); 
		t.setName("MyThread");
		t.start();
		System.out.println("MyThread LAUNCHED.");
		t.interrupt();
		t.join();
		System.out.println("MainThread close");
		System.exit(0);
	}
	@Override
	public String toString(){
		String n = Thread.currentThread().getName();
		return n;
	}
	
	public void run(){
		for(int i=0; i <10; i++){
			System.out.println( this + " is running.");
			try{
				Thread.sleep(1);
			}
			catch(InterruptedException e){
				System.err.println(this + " was interrupted.");
				break ;
			}
		}
		System.out.println(this + " ends.");
	}
	
	

}
