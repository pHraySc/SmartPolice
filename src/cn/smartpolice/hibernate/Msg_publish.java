package cn.smartpolice.hibernate;

public class Msg_publish {
	private int mpid; //发布编号
	private int noticeid; //通知消息编号
	private int apprecvid; // 用户接受者编号
	private int comprecvid; //厂商用户接收者编号
	private int devrecvid; //设备管理员接收者编号
	private int manrecvid; //管理员接收者编号
	
	public int getMpid() {
		return mpid;
	}
	public void setMpid(int mpid) {
		this.mpid = mpid;
	}
	public int getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}
	public int getApprecvid() {
		return apprecvid;
	}
	public void setApprecvid(int apprecvid) {
		this.apprecvid = apprecvid;
	}
	public int getComprecvid() {
		return comprecvid;
	}
	public void setComprecvid(int comprecvid) {
		this.comprecvid = comprecvid;
	}
	public int getDevrecvid() {
		return devrecvid;
	}
	public void setDevrecvid(int devrecvid) {
		this.devrecvid = devrecvid;
	}
	public int getManrecvid() {
		return manrecvid;
	}
	public void setManrecvid(int manrecvid) {
		this.manrecvid = manrecvid;
	}

}
