package usingBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerService {
	
	public static void main(String[] args){
		BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10) ;
		Producer producer = new Producer ( queue ) ;
		Consumer consumer = new Consumer ( queue ) ;
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}
