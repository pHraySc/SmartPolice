package cn.smartpolice.netdao;

import java.util.Date;
/**
	�ȴ���ϵȷ�ϱ�contact_wait
 *
 */

public class ContactWait {
	private int cwid;//��ϵ���
	private int masterId;//���û�
	private int contactedId;//��ϵ�û�
	private String group;//��������
	private Date applyTime;//����ʱ��
	private boolean state; 
	private String message;//����
	
	public int getCwid(){
		return cwid;
	}
	public void setCwid(int cwid){
		this.cwid = cwid;
	}
	public int getMasterId(){
		return masterId;
	}
	public void setMasterId(int masterId){
		this.masterId = masterId;
	}
	public int getContactedId(){
		return contactedId;
	}
	public void setContactedId(int contactedId){
		this.contactedId = contactedId;
	}
	public String getGroup() {
		return group;
	}
	public void setgroup(String group) {
		this.group = group;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		 this.state = state;
	}public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
