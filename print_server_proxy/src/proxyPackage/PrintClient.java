package proxyPackage;

import java.io.IOException;
import java.util.Random;

public class PrintClient {
	
	public static void main(String[] args) throws IOException{
		PrintInterface server = null;
		try{
			server = new PrintStub();
			
			String[] printers = server.getPrinterList();
			for( int i = 0; i < 5; i++) {
				for(String name : printers){
					System.out.println("Printer: " + name);
					if(server.print(getRandomDocument(), name))
						System.out.println("document printed!");
					else
						System.out.println("printing priblems!");
				}	
			}
		}
		finally { server.stopPrinting();}
	}
	
	private static String getRandomDocument(){
		int numWords = (int) (Math.random()* 100);
		String doc = "";
		for(int i = 0; i< numWords; i++){
			doc += generateRandomWord() + " ";
		}
		return doc;
	}
	
	private static String generateRandomWord() {
		Random random = new Random();
		char[] word = new char[random.nextInt(8) + 3];
		for(int j = 0; j < word.length ; j++){
			word[j] = (char) ('a' + random.nextInt(26));
		}
		return new String(word);
	}

}
