package proxyPackage;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1;
	public enum Command{UPDATE , READ , STOP};
	private Command cmd;
	private Distributore distr;
	private String distrName;

	public Message (Command c) {
		setCmd(c);
	}
	
	public Message(Command c, Distributore d){
		setCmd(c);
		setDistr(d);
	}
	
	public Message( Command c, String n){
		setCmd(c);
		setDistrName(n);
	}

	public String getDistrName() {
		return distrName;
	}

	public void setDistrName(String distrName) {
		this.distrName = distrName;
	}

	public Command getCmd() {
		return cmd;
	}

	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}

	public Distributore getDistr() {
		return distr;
	}

	public void setDistr(Distributore distr) {
		this.distr = distr;
	}
	
	
	
}
