package proxyPackage;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public interface IServer {
	
    public int SERVER_PORT = 80;

    public HTMLDoc sendGet(URI uri) throws IOException, IllegalArgumentException, BadLocationException, URISyntaxException;
    

}

