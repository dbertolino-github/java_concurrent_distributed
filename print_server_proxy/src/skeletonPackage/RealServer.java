package skeletonPackage;

import java.io.IOException;
import java.net.Socket;

public class RealServer extends PrintSkeleton {
	
	public RealServer(Socket socket) throws IOException{
		super(socket); 
	}
}
