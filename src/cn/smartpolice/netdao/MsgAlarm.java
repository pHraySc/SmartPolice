package cn.smartpolice.netdao;

import java.util.Date;

/**
 * 设备报警消息表
 * @author 刘超
 *
 */
public class MsgAlarm {

	private int alarmid;
	private Date time;
	private int deviceid;
	private int type;
	private String url;
	private int size;
	private String md5;
	private int level;
	private Boolean state;
	public int getAlarmid() {
		return alarmid;
	}
	public void setAlarmid(int alarmid) {
		this.alarmid = alarmid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
}
