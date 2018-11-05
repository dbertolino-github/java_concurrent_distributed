package produttoriConsumatori;

import buffers.BoundedBuffer;

public class Producer extends Thread{
	
	private BoundedBuffer<MyObject> buffer;
	private MyObject product;
	
	public Producer(int n, BoundedBuffer<MyObject> b){
		setName("Producer" + n);
		product = new MyObject(n,n+10);
		buffer = b;
	}
	
	public void run(){
		try {
			buffer.put(this.product);
			System.out.println(getName() + " has producted a new object for the buffer.");
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
}
