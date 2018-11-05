package digitaleTerrestre;

import blackboards.Blackboard;

public class DigitaleTerrestre {
	
	public static void main(String[] args) throws InterruptedException{
		
		Blackboard<Program> blackboardRai3 = new Blackboard<Program>();
		for(int i = 0; i < 6; i++){
			new User (blackboardRai3).start();
		}
		Program[] progsRai3 = new Program[3];
		progsRai3[0] = new Program("Meteo 3", "20:05");
		progsRai3[1] = new Program("Blob", "20:10");
		progsRai3[2] = new Program("TG 3", "20:25");
		new CanaleTv(blackboardRai3, "Rai 3", progsRai3).start();
		
		Blackboard<Program> blackboardLa7 = new Blackboard<Program>();
		for(int i = 0; i < 6; i++){
			new User (blackboardLa7).start();
		}
		Program[] progsLa7 = new Program[3];
		progsLa7[0] = new Program("Meteo La7", "20:05");
		progsLa7[1] = new Program("TG La7", "20:10");
		progsLa7[2] = new Program("L'arena", "21:10");
		new CanaleTv(blackboardLa7, "La7", progsLa7).start();
		
		
	}
}
