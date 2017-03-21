package cn.smartpolice.dao;

import antlr.collections.List;
import cn.smartpolice.hibernate.CompanyUser;

public interface FirmInfoDao {

	
		
	CompanyUser findFirm(String username);


	void register(CompanyUser firm);


	String checkuser(String username);
}
