package cn.smartpolice.netdao;

import java.util.Date;

/**
 * �û���ϵ����Ϣ��contact_inf
 * 
 */
public class ContactInf {
	
	private int contactId;//��ϵ��ϵ���
	private int masterId;//�Լ�ID
	private int contactedId;//��ϵ��ID
	private String group;//��ϵ��������
	private String aliasid;//��ϵ�˱���
	private Date setTime;//�������
	
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
