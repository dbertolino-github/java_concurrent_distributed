package primitive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Int {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		String file = "C:\\Users\\Dario\\workspacePCD\\Serializzazione\\prova.txt";
		ObjectOutput out =  new ObjectOutputStream( new FileOutputStream (file));
		ObjectInput in = new ObjectInputStream( new FileInputStream(file));
		
		int i = 5;
		out.writeInt(i);;
		out.flush();
		out.close();
		
		int j = in.readInt();
		System.out.print(j);
		in.close();
	}

}
