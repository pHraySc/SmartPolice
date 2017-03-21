package cn.smartpolice.webservice;

import java.util.List;

public interface ManagerAdminService {
	public List findAllCompany();
	public List findAllDevice();
	public List findAllMoveDevice();
	public List findAllUserinfo();
	public List findAllWebservice();
	public List findAllMsg_alarms();
	public List findAllMsg_chat();
	public List findAllMsg_notice();
	public List findAllComment();
	public List CompanyDetail1(int companyid);
	public List PredeviceDetailOfManager(int auditid);
	public List UserDetail1(int userid);
	public List ServiceOfChecked(int serviceid);
	public List All_msg_alarms(int alarmid);
	public List All_msg_chat(int chatid);
	public List All_msg_notice(int noticeid);
	public List Allcomment(int commentid);
}
