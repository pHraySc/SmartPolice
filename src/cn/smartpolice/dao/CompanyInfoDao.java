package cn.smartpolice.dao;

import java.util.List;

public interface CompanyInfoDao {
	
	public List findAuditCompany(); //����CompanyUser��CompanyInfo���ϲ�ѯ δ��˳���
	
	public List findAllCompany(); //��ѯ���г���

	public String Companypass(int userid);//���ͨ��
	
	public String Companyrefused(int userid);//��˲�ͨ��

	public List findAllCompanyInfo(int companyid);//��������



}
