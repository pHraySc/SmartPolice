package cn.smartpolice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.CompanyUser;

@Repository("statDao")
@Transactional(readOnly=false)
public class StatDaoImpl implements StatDao {


	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public List statCompanyData() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql1="SELECT COUNT(*) FROM company_user";
		String sql2="SELECT COUNT(*) FROM (SELECT * FROM company_user WHERE state='1') AS yishenhe";
		String sql3="SELECT COUNT(*) FROM (SELECT * FROM company_user WHERE state='0') AS weishenhe";
		String sql4="SELECT COUNT(*) FROM (SELECT * FROM company_user WHERE state='-1') AS weitongguo";
		SQLQuery query1=session.createSQLQuery(sql1);
		SQLQuery query2=session.createSQLQuery(sql2);
		SQLQuery query3=session.createSQLQuery(sql3);
		SQLQuery query4=session.createSQLQuery(sql4);
		List data1=query1.list();
		List data2=query2.list();
		List data3=query3.list();
		List data4=query4.list();
		List list = new ArrayList();
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		
		System.out.println(list);
	
		return list;
		
	}
	@Override
	public List statDeviceData() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql1="SELECT COUNT(*) FROM device_audit WHERE type='0'";
		String sql2="SELECT COUNT(*) FROM (SELECT * FROM device_audit WHERE state='1' and type='0') AS yishenhe";
		String sql3="SELECT COUNT(*) FROM (SELECT * FROM device_audit WHERE state='0' and type='0') AS weishenhe";
		String sql4="SELECT COUNT(*) FROM (SELECT * FROM device_audit WHERE state='-1' and type='0') AS weitongguo";
		SQLQuery query1=session.createSQLQuery(sql1);
		SQLQuery query2=session.createSQLQuery(sql2);
		SQLQuery query3=session.createSQLQuery(sql3);
		SQLQuery query4=session.createSQLQuery(sql4);
		List data1=query1.list();
		List data2=query2.list();
		List data3=query3.list();
		List data4=query4.list();
		List list = new ArrayList();
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		
		System.out.println(list);
	
		return list;
		
	}
	@Override
	public List statMoveDeviceData() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql1="SELECT COUNT(*) FROM device_audit WHERE type='1'";
		String sql2="SELECT COUNT(*) FROM (SELECT * FROM device_audit WHERE state='1' and type='1') AS yishenhe";
		String sql3="SELECT COUNT(*) FROM (SELECT * FROM device_audit WHERE state='0' and type='1') AS weishenhe";
		String sql4="SELECT COUNT(*) FROM (SELECT * FROM device_audit WHERE state='-1' and type='1') AS weitongguo";
		SQLQuery query1=session.createSQLQuery(sql1);
		SQLQuery query2=session.createSQLQuery(sql2);
		SQLQuery query3=session.createSQLQuery(sql3);
		SQLQuery query4=session.createSQLQuery(sql4);
		List data1=query1.list();
		List data2=query2.list();
		List data3=query3.list();
		List data4=query4.list();
		List list = new ArrayList();
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		
		System.out.println(list);
	
		return list;
	}
	@Override
	public List statUserinfoData() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql1="SELECT COUNT(*) FROM user_inf ";
		String sql2="SELECT COUNT(*) FROM (SELECT * FROM user_inf WHERE state='1' ) AS yishenhe";
		String sql3="SELECT COUNT(*) FROM (SELECT * FROM user_inf WHERE state='0') AS weishenhe";
		String sql4="SELECT COUNT(*) FROM (SELECT * FROM user_inf WHERE state='-1' ) AS weitongguo";
		SQLQuery query1=session.createSQLQuery(sql1);
		SQLQuery query2=session.createSQLQuery(sql2);
		SQLQuery query3=session.createSQLQuery(sql3);
		SQLQuery query4=session.createSQLQuery(sql4);
		List data1=query1.list();
		List data2=query2.list();
		List data3=query3.list();
		List data4=query4.list();
		List list = new ArrayList();
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		
		System.out.println(list);
	
		return list;
	}

}
