package cn.smartpolice.dao;

import java.util.List;

public interface UserInfoDao {
	public List findAllUserinfo();//����ȫ���û���Ϣ

	public List findManagerUserInfo(int userid);//�û���ϸ��Ϣ
	
}
