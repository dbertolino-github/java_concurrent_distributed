package mainPackage;

public class Customer extends Thread {
	
	private Bancone table;
	
	public Customer(int id, Bancone bn, ThreadGroup gruppo){
		super(gruppo,"Cliente " + id);
		this.table = bn;
	}
	
	public void run(){
		while(true){
			try{
				table.consuma();
				System.out.println(getName() + " sta consumato.");
				sleep((long) (Math.random()* 500 +1));
			}catch(InterruptedException e){
				System.out.println(getName() + " sta uscendo dalla panetteria.");
				break;
			}
		}
	}

}
