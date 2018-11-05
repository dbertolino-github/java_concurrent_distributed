package digitaleTerrestre;

public class Program {
	
	private String nome;
	private String startTime;
	
	Program(String nome, String start){
		this.nome = nome;
		this.startTime = start;
	}
	
	public String toString(){
		return startTime + ">>" + nome + " ";
	}
	
}
