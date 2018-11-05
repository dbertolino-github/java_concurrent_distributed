package basics;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Long>{
	
	private static final long serialVersionUID = 1L;
	
	static final int SEQUENTIAL_THREADSHOLD = 5000;
	int low, high;
	int[] array;
	
	Sum(int[] arr, int lo, int hi){
		array = arr;
		low = lo;
		high = hi;
	}

	@Override
	protected Long compute() {
		if(high - low <= SEQUENTIAL_THREADSHOLD){
			long sum = 0;
			for(int i = low; i < high; i++)
				sum += array[i];
			return sum;
		}else {
			int mid = low + (high - low) / 2;
			Sum left = new Sum(array, low, mid);
			Sum right = new Sum(array, mid, high);
			left.fork();
			long rightAns = right.compute();
			long leftAns = left.join();
			return leftAns + rightAns;
		}
	}
	
	static long sumArray(int[] array){
		return ForkJoinPool.commonPool().invoke(new Sum(array, 0, array.length));
	}
	
	public static void main(String[] args) throws Exception {
		int size = 1000000;
		int[] a = new int[size];
		for(int i = 0; i<size; i++){
			a[i] = i;
		}
		
		long ret = Sum.sumArray(a);
		System.out.println("Sum = " + ret);
		
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println(Integer.toString(processors) + " processor" + (processors!=1 ? "s are " : " is ") + "avaible");
	}
	

}
