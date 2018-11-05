package mainPackage;

public class Flight {

	private int capacity;
	private int avaibleSeats;
	
	public Flight(){
		int n = (int) (Math.random() * 100);
		this.capacity = n;
		this.avaibleSeats = n;
	}
	
	public void book(int n){
		this.avaibleSeats = avaibleSeats - n;
	}
	
	public void unBook(int n){
		this.avaibleSeats = avaibleSeats + n;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public int getAvaibleSeats(){
		return avaibleSeats;
	}
	
	public int getBookedSeats(){
		int result = (capacity - avaibleSeats);
		return result;
	}
	
}
