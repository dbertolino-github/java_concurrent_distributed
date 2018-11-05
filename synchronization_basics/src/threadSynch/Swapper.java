package threadSynch;

public class Swapper {
	
	private int tmp;
	
	/* This synchronized method swap two the num1 and the num2 of the SwapThread passed as parameter.
	 * As you can see is full of pauses and you may think that other thread will creates races condition on 
	 * the two variables.
	 * The key word synchronized lock the object and prevent other thread access until the method ends.
	 * More of that, no other threads can access the monitor of the object.
	 */
	synchronized public void swap(SwapThread s) throws InterruptedException{
		Thread.sleep((int) (Math.random() * 100));
		try{
			Thread.sleep((int) (Math.random() * 100));
			tmp = s.num1;
			s.num1 = s.num2;
			Thread.sleep((int) (Math.random()*100));
			s.num2 = tmp;
		} catch (InterruptedException e){ e.printStackTrace();}
	}
}
