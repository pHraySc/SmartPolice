package cn.smartpolice.webservice;


import java.util.List;

import cn.smartpolice.hibernate.ManagerInfo;

/**
 * ����Ա���ҵ����
 * 
 * 
 * */
public interface ManagerInfoService {
	ManagerInfo login(String username);

	List managerMsg();
}
