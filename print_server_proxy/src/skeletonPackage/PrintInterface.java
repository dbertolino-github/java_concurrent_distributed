package skeletonPackage;

public interface PrintInterface {
	
	public static final int PORT = 9999;
	
	public String[] getPrinterList();
	
	public boolean print(String printer, String document);
	
	public void stopPrinting();
}
