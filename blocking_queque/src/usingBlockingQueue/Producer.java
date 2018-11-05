package usingBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
	
	private BlockingQueue<Message> queue;
	
	public Producer(BlockingQueue<Message>  q){
		this.queue = q;
	}
	
	public void run(){
		for (int i = 0; i<100; i++){
			Message msg = new Message ( "" + i ) ;
			try {
				Thread.sleep(i);
				queue.put(msg);
				System.out.println("Producer" + msg.getMessage());
			} catch (InterruptedException e ){
				e.printStackTrace();
			}
		}
		Message msg = new Message("exit");
		try{
			queue.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
