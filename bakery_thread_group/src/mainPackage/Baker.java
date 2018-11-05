package mainPackage;

public class Baker extends Thread {
	
	private Bancone table;
	
	public Baker(int id, Bancone bn, ThreadGroup gruppo){
		super(gruppo,"Panettiere " + id);
		this.table = bn;
	}
	
	public void run(){
		while(true){
			try{
				System.out.println(getName() + " sta producendo.");
				table.produci();
				sleep((long) (Math.random()* 500 +1));
			}catch(InterruptedException e){
				System.out.println(getName() + " comincia a pulire la panetteria");
				try {Thread.sleep(3000);}
				catch (InterruptedException e1) {e1.printStackTrace();}
				System.out.println(getName() + " ha lasciato la panetteria.");
				break;
			}
		}
	}

}
