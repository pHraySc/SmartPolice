package cn.smartpolice.netdao;

import java.util.Date;

/**
 * 用户联系人信息表contact_inf
 * 
 */
public class ContactInf {
	
	private int contactId;//联系关系编号
	private int masterId;//自己ID
	private int contactedId;//联系人ID
	private String group;//联系人名分组
	private String aliasid;//联系人别名
	private Date setTime;//添加日期
	
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public int getMasterId() {
		return masterId;
	}
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	public int getContactedId() {
		return contactedId;
	}
	public void setContactedId(int contactedId) {
		this.contactedId = contactedId;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getAliasid() {
		return aliasid;
	}
	public void setAliasid(String aliasid) {
		this.aliasid = aliasid;
	}
	public Date getSetTime() {
		return setTime;
	}
	public void setsetTime(Date setTime) {
		this.setTime = setTime;
	}
}
