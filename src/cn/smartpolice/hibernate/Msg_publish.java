package cn.smartpolice.hibernate;

public class Msg_publish {
	private int mpid; //�������
	private int noticeid; //֪ͨ��Ϣ���
	private int apprecvid; // �û������߱��
	private int comprecvid; //�����û������߱��
	private int devrecvid; //�豸����Ա�����߱��
	private int manrecvid; //����Ա�����߱��
	
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
