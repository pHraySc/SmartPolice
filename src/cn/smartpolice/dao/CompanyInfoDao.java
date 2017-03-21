package cn.smartpolice.dao;

import java.util.List;

public interface CompanyInfoDao {
	
	public List findAuditCompany(); //返回CompanyUser和CompanyInfo联合查询 未审核厂商
	
	public List findAllCompany(); //查询所有厂商

	public String Companypass(int userid);//审核通过
	
	public String Companyrefused(int userid);//审核不通过

	public List findAllCompanyInfo(int companyid);//厂商详情



}
