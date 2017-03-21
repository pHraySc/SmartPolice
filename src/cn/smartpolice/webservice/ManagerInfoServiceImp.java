package cn.smartpolice.webservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.ManagerInfo;
import cn.smartpolice.dao.ManagerInfoDao;

@Service("managerInfoService")
@Transactional(readOnly=false)

public class ManagerInfoServiceImp implements ManagerInfoService{
	
	
	@Resource(name="managerInfoDao")
	private ManagerInfoDao managerInfoDao;
	
	@Override
	public ManagerInfo login(String username) {
		ManagerInfo manager=new ManagerInfo();
		
		manager=this.managerInfoDao.findManager(username);
		return manager;
	}

	@Override
	public List managerMsg() {
		List list=this.managerInfoDao.managerMsg();
		return list;
	}

}
