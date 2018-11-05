package wordCounter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class WordCounterServer {
	
	 static final int PORT = 8080;
	 
	 public static void main(String[] args) throws IOException {
		 
		 ServerSocket s = new ServerSocket(PORT);
		 System.out.println("Server started");
		 
		 try{
			 
			 while(true){
				 Socket socket = s.accept();
				 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
				 System.out.println("socket accept: " + socket);
				 
				 int parties = 5;
				 int incr = 400 / parties;
				 ArrayList<WordCounter> counters = new ArrayList<WordCounter>();
				 BarrierReachedAction reduce = new BarrierReachedAction(out);
				 CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, reduce);
				 
				 for(int i = 0; i<parties; i++) {counters.add(new WordCounter(cyclicBarrier, i*incr, (i+1)*incr));}
				 reduce.setCounters(counters);
			 }
		 }
		 finally{
			 s.close();
		 }
	 }
}
