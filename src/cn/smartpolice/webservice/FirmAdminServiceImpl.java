package cn.smartpolice.webservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.FirmInfoDao;
import cn.smartpolice.dao.FirmUserInfoDao;
import cn.smartpolice.hibernate.CompanyUser;


@Service("firmAdminService")
@Transactional(readOnly=false)
public class FirmAdminServiceImpl  implements FirmAdminService{
	
	
	@Resource(name="firmUserInfoDao")
	private FirmUserInfoDao firmUserInfoDao;
	
	@Override
	public List firmAdminSelf(int id) {
		List firmsuserInfo = firmUserInfoDao.firmAdminSelf(id);
		return firmsuserInfo;
	}


	@Override
	public String updateNumber(int id,String number) {
		String str = firmUserInfoDao.updateNumber(id,number);
		return str;
	}

	@Override
	public String updateEmail(int id,String email) {
		String str = firmUserInfoDao.updateEmail(id,email);
		return str;
		
	}


	@Override
	public String updatPassword(int id, String pwd2) {
		String str = firmUserInfoDao.updatPassword(id,pwd2);
		return str;
	}


	@Override
	public List firmAdminDevive(int id) {
		List deviceInfo = firmUserInfoDao.firmAdminDevive(id);
		return deviceInfo;
	}


	@Override
	public List firmAdmindeviceInfo(int id) {
		List deviceInfo = firmUserInfoDao.firmAdmindeviceInfo(id);
		return deviceInfo;
	}


	@Override
	public List firmAdminSoft(int id) {
		List softInfo = firmUserInfoDao.firmAdminSoft(id);
		return softInfo;
	}


	@Override
	public List firmAdminSoftInfo(int id) {
		List softInfo = firmUserInfoDao.firmAdminSoftInfo(id);
		return softInfo;
	}


	@Override
	public List firmAdminmsg(int id) {
		List firmmsg = firmUserInfoDao.firmAdminmsg(id);
		return firmmsg;
	}


	@Override
	public List firmAdminMsgInfo(int id) {
		List firmmsg = firmUserInfoDao.firmAdminmsgInfo(id);
		return firmmsg;
	}

}
