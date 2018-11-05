package threadSynch;

public class SwapThread implements Runnable {
	
	/*	This class contains two numbers and a SwapInt object.
	 * 	We want the SwapInt to exchange the number values without race condition.
	 */
	private int threadNumber;
	int num1;
	int num2;
	private static Swapper swInt;
	
	public SwapThread( int n1, int n2, int n){
		this.num1 = n1;
		this.num2 = n2;
		this.threadNumber = n;
		SwapThread.swInt = new Swapper();
	}
	
	@Override
	public String toString(){
		return "Thread" + threadNumber + ": " + num1 + ", " + num2;
	}
	
	@Override
	public void run() {
		System.out.println(this.toString());
		try {swInt.swap(this);} 
		catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("SWAPPED: " + this.toString());
	}
	
	
	/*	The main simply launch two SwapThread that will work together
	 * 	on the same variables num1 & num2.
	 * 	We have implemented the swapper in order not to create race condition.
	 */
	public static void main(String[] args) {
		
		for(int i = 0;  i < 10; i++){
			int val1 = (int) (Math.random()*100);
			int val2 = (int) (Math.random()*100);
			(new Thread(new SwapThread(val1 ,val2, i))).start();
		}
		
	}

	
}
