package produttoriConsumatori;

import buffers.BoundedBuffer;

public class Consumer extends Thread{
	
	private BoundedBuffer<MyObject> buffer;
	private MyObject product;
	
	public Consumer(int n, BoundedBuffer<MyObject> b){
		setName("Consuemr" + n);
		buffer = b;
	}
	
	public void run(){
		try {
			this.product = buffer.get();
			System.out.println(getName() + " has just got the product " + product.getPropertyOne() + " from the buffer.");
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
}
