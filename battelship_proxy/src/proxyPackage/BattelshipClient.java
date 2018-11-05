package proxyPackage;

import java.io.IOException;
import java.util.Scanner;

public class BattelshipClient {
	
	private static Scanner input;
	private static IServer server = null;
	
	public static void main(String[] args) throws IOException{
		
		
		input = new Scanner(System.in);
		
		try{
			server = new Proxy();
			
			System.out.println("Insert your name: ");
			String name = input.nextLine();
			System.out.println("Insert your surname: ");
			String surname = input.nextLine(); 
			System.out.println(server.startNewGame(name + "\\s" + surname));
			actualSituation();
			
			Integer ships = 3;
			while (ships >0){
				Integer[] rc = toHit();
				System.out.println(server.shoot(rc[0], rc[1]));
				System.out.println(server.getBoard());
				ships = server.getNumMissing();
				System.out.println("Ships left: " + ships);
			}
			System.out.println("You totalized a score equals to: " + server.getScore());
		}
		catch(Exception e){e.printStackTrace();}
		finally{server.close();}
	}

	
	public static Integer[] toHit(){
		Integer[] rc = new Integer[2];
		System.out.println("ROW to hit: ");
		rc[0] = (input.nextInt()) - 1;
		System.out.println("COLUMN to hit: ");
		rc[1] = (input.nextInt()) -1;
		
		return rc;	
	}
	
	public static void actualSituation() throws IOException{
		System.out.println(server.getBoard());
		System.out.println("Ships left: " + server.getNumMissing());
	}

}
