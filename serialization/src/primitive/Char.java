package primitive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Char {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String file = "C:\\Users\\Dario\\workspacePCD\\Serializzazione\\prova.txt";
		ObjectOutput out = new ObjectOutputStream( new FileOutputStream(file) );
		ObjectInput in = new ObjectInputStream( new FileInputStream(file));
		
		char letter = 'a';
		out.writeChar(letter);
		out.flush();
		out.close();
		
		char toPrint = in.readChar();
		System.out.println(toPrint);
		in.close();
	}
}
