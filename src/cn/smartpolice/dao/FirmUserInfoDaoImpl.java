package cn.smartpolice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.CompanyUser;

@Repository("firmUserInfoDao")
@Transactional(readOnly=false)

public class FirmUserInfoDaoImpl implements FirmUserInfoDao{
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public List firmAdminSelf(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from company_user where userid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List firmuserInfo = query.list();
		return firmuserInfo;
	}

	
	@Override
	public String updateNumber(int id,String number) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE company_user SET number=? WHERE userid=?";
		System.out.println(number);
		System.out.println(id);
		SQLQuery query=session.createSQLQuery(sql);
		query.setString(0,number);
		query.setInteger(1,id);
		query.executeUpdate();
		return "success";
		
	}

	@Override
	public String updateEmail(int id,String email) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		System.out.println(id);
		String sql="UPDATE company_user SET email=? WHERE userid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setString(0,email);
		query.setInteger(1,id);
		query.executeUpdate();
		query.executeUpdate();
		return "success";
	}


	@Override
	public String updatPassword(int id, String pwd2) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		System.out.println(id);
		String sql="UPDATE company_user SET password=? WHERE userid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setString(0,pwd2);
		query.setInteger(1,id);
		query.executeUpdate();
		query.executeUpdate();
		return "success";
	}


	@Override
	public List firmAdminDevive(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT d.devicename,d.type,d.date,d.code,c.username from"+  
				 " device_audit as d,company_user AS c WHERE c.userid=? and c.companyid=d.companyid";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List deviceInfo = query.list();
		return deviceInfo;
	}


	@Override
	public List firmAdmindeviceInfo(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT d.devicename,d.type,d.date,d.code,d.checkdate,cf.name from"+  
				 " device_audit as d,company_inf AS cf,company_user as c WHERE c.userid=? and c.companyid=d.companyid=cf.companyid";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List deviceInfo = query.list();
		return deviceInfo;
	}


	@Override
	public List firmAdminSoft(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT d.softid,d.name,d.type,d.date,d.version,c.username FROM soft_inf AS d,company_user AS c "+
						"WHERE d.uploadid=c.companyid and c.userid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List softInfo = query.list();
		 System.out.println("softInfo"+softInfo);
		return softInfo;
	}


	@Override
	public List firmAdminSoftInfo(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from soft_inf where softid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List softInfo = query.list();
		 System.out.println("softInfo"+softInfo);
		return softInfo;
	}


	@Override
	public List firmAdminmsg(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT a.noticeid,m.name,a.sendtime,a.title,a.content FROM manager_inf AS m,msg_notice AS a,msg_publish,company_user "+
					"WHERE a.noticeid=msg_publish.noticeid AND msg_publish.comprecvid=company_user.companyid AND company_user.userid=? ";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List firmmsg = query.list();
		 System.out.println("firmmsg"+firmmsg);
		return firmmsg;
	}


	@Override
	public List firmAdminmsgInfo(int id) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select a.name,b.sendtime,b.content from msg_notice as b,manager_inf as a where noticeid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List firmmsg = query.list();
		 System.out.println("firmmsg"+firmmsg);
		return firmmsg;
	}







	
}
