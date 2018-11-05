package proxyPackage;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L; 
	public enum Command{PRINT , GET_PRINTER_LIST , STOP_PRINTING};
	private Command cmd;
	private String printer;
	private String document;
	
	public Message(Command cmd){
		setCmd(cmd);
	}
	
	public Message(Command cmd, String printer, String doc){
		setCmd(cmd);
		setPrinter(printer);
		setDocument(doc);
	}

	public Command getCmd() {
		return cmd;
	}

	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
}
