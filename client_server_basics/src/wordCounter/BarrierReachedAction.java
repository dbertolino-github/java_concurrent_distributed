package wordCounter;

import java.io.PrintWriter;
import java.util.ArrayList;

public class BarrierReachedAction extends Thread {
	
	private ArrayList<WordCounter> wordCounters;
	private PrintWriter out;
	
	public BarrierReachedAction(PrintWriter out){
		this.out = out;
	}
	
	public void setCounters(ArrayList<WordCounter> wordCounters) {
		this.wordCounters = wordCounters;
	}
	
	public void run() {
		System.out.println("Barrier Reached!");
		int totalWords = 0;
		for(WordCounter wc : wordCounters) {
			totalWords += wc.wordCount;
		}
		
		System.out.println("Word count is = " + totalWords);
		out.println(totalWords);
	}

}
