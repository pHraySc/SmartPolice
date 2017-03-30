package cn.smartpolice.webservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.SystemRunInfoDao;
import cn.smartpolice.dao.SystemRunInfoDaoImpl;

@Service("systemRunInfoService")
@Transactional(readOnly=false)
public class SystemRunInfoServiceImpl implements SystemRunInfoService{

	@Resource(name = "systemRunInfoDao")
	private SystemRunInfoDao systemRunInfoDao;

	@Override
	public List getSystemRunInfoFromDB() {
		return systemRunInfoDao.getSystemRunInfoFromDB();
	}
	
	public static void main(String[] args) {
		SystemRunInfoDaoImpl daoImpl = new  SystemRunInfoDaoImpl();
		System.out.println(daoImpl.getSystemRunInfoFromDB());
	}
}
