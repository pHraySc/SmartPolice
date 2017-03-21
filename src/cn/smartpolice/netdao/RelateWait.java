package cn.smartpolice.netdao;

import java.util.Date;

public class RelateWait {
	private int rwid; //关联确认编号
	private int applyid; //请求用户
	private int deviceid; //关联设备
	private int askid; //确认用户
	private byte applytype; //0为申请，1分享
	private char applyright; //关联关系：0代表管理权限，1为操控权限，2为查看权限，3为接收权限
	private Date applytime; //请求时间
	private Date acktime; //确认时间
	private byte state; //1已确认生效，0未确认或者否认
	private char message;//留言
	
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
