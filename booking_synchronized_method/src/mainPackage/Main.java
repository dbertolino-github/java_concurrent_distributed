package mainPackage;

import java.util.ArrayList;

public class Main{
	
	public static void main(String[] args) throws InterruptedException{
		
		Integer agencyNumber = (int) (Math.random() * 10 + 1);
		ArrayList<Agency> agenzie = new ArrayList<Agency>();
		Flight flight = new Flight();
		
		for(int i=0; i< agencyNumber; i++){
			Agency agency = new Agency(flight);
			agency.setName("Agency " + i);
			agenzie.add(agency);
			agency.start();
		}
		for(int i=0; i<agencyNumber; i++){
			agenzie.get(i).join();
		}
		
		System.out.println("\nSeats booked: " + flight.getBookedSeats());
		System.out.println("\nSeats remaining: " + flight.getAvaibleSeats());
	}
	

}
