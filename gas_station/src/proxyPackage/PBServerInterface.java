package proxyPackage;

public interface PBServerInterface {
	
	int PORT = 9999;
	
	void stop();
	
	void updatePrices(Distributore d);
	
	Distributore getPrices(String name);
	
}
