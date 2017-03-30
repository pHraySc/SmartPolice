package cn.smartpolice.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cn.smartpolice.hibernate.AddServers;

@Repository("addServersDao")
@Transactional(readOnly=false)
public class AddServersDaoImpl implements AddServersDao{
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	private Session session;

	@Override
	public void AddServers(AddServers s) {
		System.out.println(hibernateTemplate);
		session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		session.save(s);
	}


}
