package proxyPackage;

import java.io.Serializable;

public class Distributore implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private double prezzoBenzina;
	private double prezzoDiesel;
	
	public Distributore(String n){
		this.name = n;
		this.prezzoBenzina = (Double) (Math.random()*20);
		this.prezzoDiesel = (Double) (Math.random()*20);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrezzoBenzina() {
		return prezzoBenzina;
	}
	public void setPrezzoBenzina(double prezzoBenzina) {
		this.prezzoBenzina = prezzoBenzina;
	}
	public double getPrezzoDiesel() {
		return prezzoDiesel;
	}
	public void setPrezzoDiesel(double prezzoDiesel) {
		this.prezzoDiesel = prezzoDiesel;
	}
	

}
