package cn.smartpolice.dao;

import java.util.List;

public interface UserInfoDao {
	public List findAllUserinfo();//查找全部用户信息

	public List findManagerUserInfo(int userid);//用户详细信息
	
}
