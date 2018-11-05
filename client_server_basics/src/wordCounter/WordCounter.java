package wordCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.CyclicBarrier;

public class WordCounter extends Thread{
	
	CyclicBarrier cyclicBarrier;
	int wordCount = 0;
	int startFile, endFile;
	
	public WordCounter(CyclicBarrier c, int startFile, int endFile) {
		cyclicBarrier = c;
		this.startFile = startFile;
		this.endFile = endFile;
		new Thread(this).start();
	}
	
	@SuppressWarnings("resource")
	public void run() {
		try {
			for(int i = startFile; i<endFile; i++){
				BufferedReader br = new BufferedReader(new FileReader("file_" + i + ".txt"));
				
				String line = br.readLine();
				while (line != null) {
					String[] wordArray = line.split("\\s+");
					wordCount += wordArray.length;
					line = br.readLine();
				}
			}
			cyclicBarrier.await();
		}
		catch(Exception exc){
			System.out.println(exc);
		}
	}
}
