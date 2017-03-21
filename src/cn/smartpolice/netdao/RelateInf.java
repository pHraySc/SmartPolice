package cn.smartpolice.netdao;

import java.util.Date;

/**
 * 关联人信息表映射
 * @author 刘超
 *
 */
public class RelateInf {

	private int relateid;
	private int userid;
	private int deviceid;
	private Date settime;
	private String authority;
	private int aliasid;
	public int getRelateid() {
		return relateid;
	}
	public void setRelateid(int relateid) {
		this.relateid = relateid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getAliasid() {
		return aliasid;
	}
	public void setAliasid(int aliasid) {
		this.aliasid = aliasid;
	}
}
