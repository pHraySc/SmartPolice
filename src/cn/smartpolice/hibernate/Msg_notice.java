package cn.smartpolice.hibernate;

import java.sql.Date;

public class Msg_notice {
	private int noticeid;//通知消息编号
	private String title;
	private Date sendtime;
	private String content;
	private int sendid;
	private int recvid; 
	
	

	
	public int getRecvid() {
		return recvid;
	}
	public void setRecvid(int recvid) {
		this.recvid = recvid;
	}
	public int getSendid() {
		return sendid;
	}
	public void setSendid(int sendid) {
		this.sendid = sendid;
	}
	public int getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
