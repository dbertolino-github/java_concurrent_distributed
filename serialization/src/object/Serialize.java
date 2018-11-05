package object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Serialize {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		String file = "C:\\Users\\Dario\\workspacePCD\\Serializzazione\\prova.txt";
		ObjectOutput out = new ObjectOutputStream( new FileOutputStream(file) );
		ObjectInput in = new ObjectInputStream( new FileInputStream(file));
		
		Quadrato q = new Quadrato(4.5);
		String s = "ma che bei quadrati cazzo!!!";
		Quadrato[] quadrati = new Quadrato[3];
		quadrati[0] = new Quadrato(3.2);
		quadrati[1] = new Quadrato(7.8);
		quadrati[2] = new Quadrato(22.3);
		out.writeObject(s);
		out.writeObject(q);
		out.writeObject(quadrati);
		out.flush();
		out.close();
		
		String ao = (String)in.readObject();
		Quadrato r = (Quadrato)in.readObject();
		Quadrato[] ri = (Quadrato[])in.readObject();
		System.out.println(ao + "\nIl lato: " + r.getLato() + "\nL'area: " + r.getArea());
		for(int i = 0; i < ri.length; i++){
			System.out.println("\nIl lato: " + ri[i].getLato() + "\nL'area: " + ri[i].getArea());
		}
		in.close();
	
	}
	}
	
