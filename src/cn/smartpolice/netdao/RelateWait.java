package cn.smartpolice.netdao;

import java.util.Date;

public class RelateWait {
	private int rwid; //����ȷ�ϱ��
	private int applyid; //�����û�
	private int deviceid; //�����豸
	private int askid; //ȷ���û�
	private byte applytype; //0Ϊ���룬1����
	private char applyright; //������ϵ��0�������Ȩ�ޣ�1Ϊ�ٿ�Ȩ�ޣ�2Ϊ�鿴Ȩ�ޣ�3Ϊ����Ȩ��
	private Date applytime; //����ʱ��
	private Date acktime; //ȷ��ʱ��
	private byte state; //1��ȷ����Ч��0δȷ�ϻ��߷���
	private char message;//����
	
	public int getRwid() {
		return rwid;
	}
	public void setRwid(int rwid) {
		this.rwid = rwid;
	}
	public int getApplyid() {
		return applyid;
	}
	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}
	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public int getAskid() {
		return askid;
	}
	public void setAskid(int askid) {
		this.askid = askid;
	}
	public byte getApplytype() {
		return applytype;
	}
	public void setApplytype(byte applytype) {
		this.applytype = applytype;
	}
	public char getApplyright() {
		return applyright;
	}
	public void setApplyright(char applyright) {
		this.applyright = applyright;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public Date getAcktime() {
		return acktime;
	}
	public void setAcktime(Date acktime) {
		this.acktime = acktime;
	}
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	public char getMessage() {
		return message;
	}
	public void setMessage(char message) {
		this.message = message;
	}


}
