package cn.smartpolice.tools;


public class JsonMessage {
	
	private String ID;
	private String CMD;
	private String OPT;
	private String SEQ;
	private String ACK;
	
	public String getCMD() {
		return CMD;
	}
	public void setCMD(String cMD) {
		CMD = cMD;
	}
	public String getOPT() {
		return OPT;
	}
	public void setOPT(String oPT) {
		OPT = oPT;
	}
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public String getACK() {
		return ACK;
	}
	public void setACK(String aCK) {
		ACK = aCK;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
}
