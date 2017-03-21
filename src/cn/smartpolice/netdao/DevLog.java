package cn.smartpolice.netdao;

import java.util.Date;

/**
 * devµÇÂ¼ÐÅÏ¢±í
 * @author Áõ³¬
 *
 */
public class DevLog {
	private int logid;
	private int deviceid;
	private Date date;
	private String ip;
	private int port;
	private int operate;
	public int getOperate() {
		return operate;
	}
	public void setOperate(int operate) {
		this.operate = operate;
	}
	public int getLogid() {
		return logid;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
}
