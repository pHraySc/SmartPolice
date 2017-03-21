package cn.smartpolice.workbean;

import java.util.Date;

import org.apache.mina.core.session.IoSession;

/**
 * appNode��devNode�Ļ���
 * @author ����
 *
 */
public class UserNode {

	private String ip;
	private int port;
	private String Account;
	private int id;
	private long loginDate; //��Ҫ������� ���Զ���Ϊlong����
	private Date revPktDate;
	private int revPktId;
	private int sntPktId;
	private IoSession ioSession;
	private int state; //�û�״̬
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(long loginDate) {
		this.loginDate = loginDate;
	}
	public Date getRevPktDate() {
		return revPktDate;
	}
	public void setRevPktDate(Date revPktDate) {
		this.revPktDate = revPktDate;
	}
	public int getRevPktId() {
		return revPktId;
	}
	public void setRevPktId(int revPktId) {
		this.revPktId = revPktId;
	}
	public int getSntPktId() {
		return sntPktId;
	}
	public void setSntPktId(int sntPktId) {
		this.sntPktId = sntPktId;
	}
	public IoSession getIoSession() {
		return ioSession;
	}
	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}
	
	
}
