package mainPackage;

public class Agency extends Thread{
	
	private int SeatsToBook;
	private int SeatsToUnbook;
	private boolean booked;
	private Flight flight;

	public Agency(Flight f){
		SeatsToBook = (int) (Math.random()* 30 + 1);
		SeatsToUnbook = (int) (Math.random() * SeatsToBook + 1);
		flight = f;
	}
	
	@Override
	public void run(){
		System.out.println("-- " + this.getName() + " tries to book " + this.SeatsToBook + " seats.");
		try{
			Thread.sleep(200);
			bookSeats(SeatsToBook);
			if(getRandomBoolean() && booked){
				unbookSeats();
			}
		} catch (InterruptedException e) { e.printStackTrace();}
	}
	
	public Boolean getRandomBoolean(){
		return Math.random() < 0.5;
	}
	
	private void bookSeats(int n) throws InterruptedException{
		System.out.println(this.getName() + " is requesting " + n + " seats.");
		synchronized(flight){
			if( SeatsToBook > flight.getAvaibleSeats()){
				this.booked = false;
				System.err.println(this.getName() + " no seats avaibles.");
			}
			else {
				Thread.sleep(500);
				flight.book(n);
				this.booked = true;
				System.out.println(n + " seats assigned to " + this.getName() + ".");
			}		
		}
	}
	
	private void unbookSeats() throws InterruptedException{
		System.out.println(this.getName() + " is unbooking " + SeatsToUnbook+ " seats.");
		synchronized(flight){
			if( SeatsToUnbook > flight.getBookedSeats() || this.booked == false) {
				System.err.println(this.getName() + " can not unbook seats.");
			}
			else{
				Thread.sleep(500);
				flight.unBook(SeatsToUnbook);
				System.out.println(this.getName() + " unbooked " + SeatsToUnbook + " seats.");
			}
		}
	}
}
