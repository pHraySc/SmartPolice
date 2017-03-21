package cn.smartpolice.dao;

import java.util.List;

public interface WebserviceInfoDao {
	public List findAllWebserviceinfo();

	public List findServiceInfo(int serviceid);
	
}
