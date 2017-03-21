package cn.smartpolice.webservice;


import java.util.List;

import cn.smartpolice.hibernate.ManagerInfo;

/**
 * 管理员相关业务类
 * 
 * 
 * */
public interface ManagerInfoService {
	ManagerInfo login(String username);

	List managerMsg();
}
