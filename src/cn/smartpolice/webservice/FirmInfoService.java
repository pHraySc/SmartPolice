package cn.smartpolice.webservice;

import antlr.collections.List;
import cn.smartpolice.hibernate.CompanyUser;
import cn.smartpolice.hibernate.ManagerInfo;

public interface FirmInfoService {
	CompanyUser login(String username);


	void register(CompanyUser firm);


	String checkuser(String username);
}
