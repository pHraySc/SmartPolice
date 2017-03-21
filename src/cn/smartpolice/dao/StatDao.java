package cn.smartpolice.dao;

import java.util.List;

import cn.smartpolice.hibernate.CompanyUser;

public interface StatDao {


	public List statCompanyData();

	public List statDeviceData();

	public List statMoveDeviceData();

	public List statUserinfoData();

}
