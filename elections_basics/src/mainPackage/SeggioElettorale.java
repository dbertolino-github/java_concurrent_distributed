package mainPackage;

import java.util.ArrayList;

public class SeggioElettorale extends Thread {
	
	private ArrayList<Integer> voti = new ArrayList<Integer>();
	private ArrayList<Integer> votiPerPartito;
	private Integer number;
	private UfficioMinisteriale uffMin;
	
	public SeggioElettorale(UfficioMinisteriale u, Integer n){
		votiPerPartito = new ArrayList<Integer>(u.numeroPartiti);
		this.voti = new ArrayList<Integer>();
		this.number = n;
		this.uffMin = u;
	}
	
	@Override
	public String toString(){
		return "Thread " + this.number;
	}
	
	public void run(){
		try {
			this.initVoti();
			this.contaVoti();
			this.assegnaVoti(this.uffMin);
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void initVoti() throws InterruptedException{
		int votanti = (int) (Math.random() * 100 +1);
		
		for(int i = 0; i< votanti; i++){
			
			Integer voto = (int) (Math.random() * uffMin.numeroPartiti);
			voti.add(voto);
			
			Integer attesa = (int) (Math.random() * 500 +1);
			try{Thread.sleep(attesa);}
			catch(InterruptedException e){}
		}
	}

	public void contaVoti(){
		for(int k =0; k < uffMin.numeroPartiti; k++){
			votiPerPartito.add(0);
		}
		for(int i = 0; i < uffMin.numeroPartiti; i++ ){
			for(int j = 0; j < voti.size(); j++){
				if(voti.get(j).equals(i)){
					votiPerPartito.set(i, (votiPerPartito.get(i) +1));
				}
			}
		}
	}

	public void assegnaVoti(UfficioMinisteriale u){
		for(int i = 0; i < u.numeroPartiti; i++){
			u.votiTotaliPartito[i] += votiPerPartito.get(i);
		}

	}
}
