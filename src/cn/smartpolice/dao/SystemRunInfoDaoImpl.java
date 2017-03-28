package cn.smartpolice.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.SystemRunInfo;

@Repository("systemRunInfoDao")
@Transactional(readOnly=false)
public class SystemRunInfoDaoImpl implements SystemRunInfoDao{
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	private Session session;

	@Override
	public List getSystemRunInfoFromDB() {
		session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "Select * from SYSTEMRUNINFO";
		List info = (List)session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return info;
	}
	
}
