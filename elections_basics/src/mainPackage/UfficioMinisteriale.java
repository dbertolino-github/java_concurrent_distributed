package mainPackage;

public class UfficioMinisteriale {
	
	Integer numeroPartiti;
	Integer numeroSeggi;
	int[] votiTotaliPartito;
	int votiPartitoMigliore = 0;
	int partitoMigliore = 0;
	
	public UfficioMinisteriale(){
		
		numeroPartiti = (int) (Math.random() * 10+1);
		numeroSeggi = (int) (Math.random() * 10+1);
		votiTotaliPartito = new int[this.numeroPartiti];
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		UfficioMinisteriale u = new UfficioMinisteriale();
		u.lanciaSeggi();
		
		for(int i = 0; i < u.numeroPartiti; i++){
			System.out.println("Il partito " + i + " ha ottenuto " + u.votiTotaliPartito[i] + " voti.");
		}
		
		u.decretaVincitore();
		System.out.println("Il Partito migliore: " + u.partitoMigliore + " ha ottenuto voti: " + u.votiTotaliPartito[u.partitoMigliore]);
	}
	
	private void lanciaSeggi() {
		
		Integer n = this.numeroSeggi;
		
		SeggioElettorale[] list = new SeggioElettorale[n];
		for(int i = 0; i < n; i++ ){
			list[i] = new SeggioElettorale(this, i);
			list[i].start();
		}
		try{
			for(int i = 0; i < n; i++ ){
				list[i].join();
			}
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	private void decretaVincitore(){
		for(int i = 0; i < votiTotaliPartito.length ; i++){
			if(votiPartitoMigliore < votiTotaliPartito[i]){
				votiPartitoMigliore = votiTotaliPartito[i];
				partitoMigliore = i;
			}	
		}
	}

}
