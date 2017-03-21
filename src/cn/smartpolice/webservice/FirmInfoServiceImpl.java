package cn.smartpolice.webservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;
import cn.smartpolice.dao.FirmInfoDao;
import cn.smartpolice.hibernate.CompanyUser;
import cn.smartpolice.hibernate.ManagerInfo;


@Service("firmInfoService")
@Transactional(readOnly=false)

public class FirmInfoServiceImpl  implements FirmInfoService {
	
	
	@Resource(name="firmInfoDao")
	private FirmInfoDao firmInfoDao;

	@Override
	public CompanyUser login(String username) {
		
		CompanyUser firm=new CompanyUser();
		
		firm=this.firmInfoDao.findFirm(username);
		return firm;
		
	}




	@Override
	public void register(CompanyUser firm) {
		this.firmInfoDao.register(firm);
		
	}




	@Override
	public String checkuser(String username) {
		String str=this.firmInfoDao.checkuser(username);
		return str;
		
	}


}
