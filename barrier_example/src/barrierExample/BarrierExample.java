package barrierExample;

import barriers.Barrier;

public class BarrierExample {
	
	public static void main(String[] args){
		
		final Barrier barrier = new Barrier(3);
		
		Thread t1 = new Thread(new Task(barrier), "Thread1");
		Thread t2 = new Thread(new Task(barrier), "Thread2");
		Thread t3 = new Thread(new Task(barrier), "Thread3");
		Thread t4 = new Thread(new Task(barrier), "Thread4");
		Thread t5 = new Thread(new Task(barrier), "Thread5");
		Thread t6 = new Thread(new Task(barrier), "Thread6");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
	
	private static class Task implements Runnable {
		private Barrier barrier;
		public Task(Barrier barrier){
			this.barrier = barrier;
		}
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
				barrier.waitBarrier();
				System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
			} catch (InterruptedException ex ){
				ex.printStackTrace();
			}
		}
	}
}
