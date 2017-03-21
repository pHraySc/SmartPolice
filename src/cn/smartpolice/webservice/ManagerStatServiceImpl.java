package cn.smartpolice.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.CompanyInfoDao;
import cn.smartpolice.dao.StatDao;
import cn.smartpolice.hibernate.CompanyUser;
import cn.smartpolice.tools.Stattools;


@Service("managerStatService")
@Transactional(readOnly=false)
public class ManagerStatServiceImpl implements ManagerStatService {

	@Resource(name = "statDao")
	private StatDao statDao;

	@Resource(name = "statTool")
	private Stattools statTool;
	

	@Override
	public List getCompanyData() {
		List CompanyData = statDao.statCompanyData();
		return CompanyData;
	}


	@Override
	public List getDeviceData() {
		List DeviceData = statDao.statDeviceData();
		return DeviceData;
	}


	@Override
	public List getMoveDeviceData() {
		List MoveDeviceData = statDao.statMoveDeviceData();
		return MoveDeviceData;
	}


	@Override
	public List getUserinfo() {
		List UserinfoData = statDao.statUserinfoData();
		return UserinfoData;
	}

}
