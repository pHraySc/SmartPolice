package cn.smartpolice.dao;

import java.lang.reflect.Field;
import java.util.Iterator;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;

import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.CompanyUser;

@Repository("companyInfoDao")
@Transactional(readOnly=false)
public class CompanyInfoDaoImpl implements CompanyInfoDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List findAuditCompany() {
				
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="select inf.companyid,user.userid,inf.name,inf.type,inf.demo,user.username"
				+ " from company_inf as inf,company_user as user where "
				+ "user.state like '0' and user.companyid=inf.companyid ";
				
		List auditCompany=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return auditCompany;
	}

	@Override
	public List findAllCompany() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select inf.companyid,inf.name,inf.type,inf.address,user.username,user.state"
				+ " from company_inf as inf,company_user as user where "
				+ "user.companyid=inf.companyid ";
		List allCompany=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allCompany;
	}

	@Override
	public String Companypass(int userid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE company_user SET state='1' WHERE userid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,userid);
		query.executeUpdate();
		System.out.println(userid);
		return "success";
	}

	@Override
	public String Companyrefused(int userid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE company_user SET state='-1' WHERE userid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,userid);
		query.executeUpdate();
		return "success";
	}

	@Override
	public List findAllCompanyInfo(int companyid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT i.name,i.type,u.email,i.demo,i.logo,i.address,u.number"+ 
				   " FROM company_inf AS i,company_user AS u WHERE i.companyid=? AND "+
				   "i.companyid=u.companyid ";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,companyid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List allCompanyInfo = query.list();
		return allCompanyInfo;
	}
	
}
