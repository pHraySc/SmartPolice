package cn.smartpolice.webservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.CompanyInfoDao;
import cn.smartpolice.dao.DeviceInfoDao;
import cn.smartpolice.dao.MassageDao;
import cn.smartpolice.dao.UserInfoDao;
import cn.smartpolice.dao.WebserviceInfoDao;
import cn.smartpolice.hibernate.UserInfo;

@Service("managerAdminService")
@Transactional(readOnly=false)
public class ManagerAdminServiceImpl implements ManagerAdminService{

	@Resource(name="companyInfoDao")
	private CompanyInfoDao companyInfoDao;
	
	@Resource(name="deviceInfoDao")
	private DeviceInfoDao deviceInfoDao;
	
	@Resource(name="userInfoDao")
	private UserInfoDao userInfoDao;
	
	@Resource(name="webserviceInfoDao")
	private WebserviceInfoDao webserviceInfoDao;
	
	@Resource(name="massageDao")
	private MassageDao massageDao;
	
	@Override
	public List findAllCompany() {
		List allCompany = companyInfoDao.findAllCompany();
		return allCompany;
	}

	@Override
	public List findAllDevice() {
		List allDevice = deviceInfoDao.findAllDevice();
		return allDevice;
	}
	@Override
	public List findAllMoveDevice() {
		List allMoveDevice = deviceInfoDao.findAllMoveDevice();
		return allMoveDevice;
	}

	@Override
	public List findAllUserinfo() {
		List allUserInfo = userInfoDao.findAllUserinfo();
		return allUserInfo;
	}

	@Override
	public List findAllWebservice() {
		List allWebservice = webserviceInfoDao.findAllWebserviceinfo();
		return allWebservice;
	}

	@Override
	public List findAllMsg_alarms() {
		List allmsg_alarms = massageDao.findallMsg_alarms();
		return allmsg_alarms;
	}

	@Override
	public List findAllMsg_chat() {
		List allmsg_chat = massageDao.findallMsg_chat();
		return allmsg_chat;
	}

	@Override
	public List findAllMsg_notice() {
		List allmsg_notice = massageDao.findallMsg_notcie();
		return allmsg_notice;
	}

	@Override
	public List findAllComment() {
		List allcomment = massageDao.findallComment();
		return allcomment;
	}

	@Override
	public List CompanyDetail1(int companyid) {
		List allCompanyInfo = companyInfoDao.findAllCompanyInfo(companyid);
		return allCompanyInfo;
	}

	@Override
	public List PredeviceDetailOfManager(int auditid) {
		List PredeviceInfo = deviceInfoDao.findManagerPredeviceInfo(auditid);
		return PredeviceInfo;
	}

	@Override
	public List UserDetail1(int userid) {
		List allUserInfo = userInfoDao.findManagerUserInfo(userid);
		return allUserInfo;
	}

	@Override
	public List ServiceOfChecked(int serviceid) {
		List allServiceInfo = webserviceInfoDao.findServiceInfo(serviceid);
		return allServiceInfo;
	}

	@Override
	public List All_msg_alarms(int alarmid) {
		List allalarmmsgInfo = massageDao.findAll_msg_alarms(alarmid);
		return allalarmmsgInfo;
	}

	@Override
	public List All_msg_chat(int chatid) {
		List allchatmsgInfo = massageDao.findAll_msg_chat(chatid);
		return allchatmsgInfo;
	}

	@Override
	public List All_msg_notice(int noticeid) {
		List allnoticemsgInfo = massageDao.findAll_msg_notice(noticeid);
		return allnoticemsgInfo;
	}

	@Override
	public List Allcomment(int commentid) {
		List allcommentInfo = massageDao.findAllcomment(commentid);
		return allcommentInfo;
	}
	

}
