package cn.smartpolice.dao;

import java.util.List;

import cn.smartpolice.hibernate.ManagerInfo;

public interface ManagerInfoDao {


	ManagerInfo findManager(String username);

	List managerMsg();
}
