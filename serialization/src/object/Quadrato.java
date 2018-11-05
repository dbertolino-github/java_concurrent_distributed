package object;

import java.io.Serializable;

public class Quadrato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double lato;
	private double area;
	
	public Quadrato(double l)  {
		this.lato = l;
		this.area = l*l;
	}

	public double getLato() {
		return lato;
	}

	public void setLato(double lato) {
		this.lato = lato;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

}
