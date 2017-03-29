package cn.smartpolice.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.PlatMsgAnnounce;

@Repository("platMsgAnnounceDao")
@Transactional(readOnly=false)
public class PlatMsgAnnounceDaoImpl implements PlatMsgAnnounceDao{
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	private Session session;

	@Override
	public void AddPlatMsgToDB(PlatMsgAnnounce announce) {
		session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		session.save(announce);
	}

}
