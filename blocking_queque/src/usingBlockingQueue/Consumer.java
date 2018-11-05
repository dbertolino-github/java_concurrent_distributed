package usingBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	
	private BlockingQueue<Message> queue;
	
	public Consumer(BlockingQueue<Message> q){
		this.queue = q;
	}
	
	@Override
	public void run() {
		try{
			Message msg;
			
			while((msg = queue.take()).getMessage() != "exit"){
				Thread.sleep(10);
				System.out.println("Consumed "+ msg.getMessage());
				
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
