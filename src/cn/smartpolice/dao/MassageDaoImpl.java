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

import cn.smartpolice.hibernate.UserInfo;

@Repository("massageDao")
@Transactional(readOnly=false)
public class MassageDaoImpl implements MassageDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	public List findallMsg_alarms(){
				
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="SELECT m.alarmid,m.time,d.username,m.type,m.level FROM msg_alarm AS m,device_inf AS d WHERE m.deviceid=d.deviceid";
				
		List allmsg_alarms=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allmsg_alarms;
	}

	@Override
	public List findallMsg_chat() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="SELECT t1.chatid,t1.name,t1.name1 FROM "+
					"(SELECT m.chatid,n.name,o.name AS name1 FROM msg_chat AS m INNER JOIN user_inf AS "+
						"n ON m.sendid=n.userid INNER JOIN user_inf AS o ON m.apprecvid=o.userid WHERE "+
						"m.recvtype='1') AS t1"+
						" UNION ALL "+
						"SELECT t2.chatid,t2.name,t2.name1 FROM "+
						"(SELECT m.chatid,p.username AS name1,n.name as name FROM msg_chat AS m,device_inf AS p,user_inf AS "+
						"n WHERE m.sendid=n.userid AND m.DEVERCVID= p.deviceid ) AS t2 ";
			
		List allmsg_chat=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	
		return allmsg_chat;
		
	}

	@Override
	public List findallMsg_notcie() {
		
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="SELECT msg_notice.noticeid,msg_notice.title,user_inf.name,msg_notice.sendtime FROM msg_notice,user_inf"+
					" WHERE msg_notice.sendid=user_inf.userid";
				
		List allmsg_notice=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allmsg_notice;
	}

	@Override
	public List findallComment() {

		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="SELECT m.commentid,n.name,m.date,m.type,m.title FROM comment AS m,user_inf AS n WHERE n.userid=m.userid";
				
		List allcomment=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allcomment;
	}

	@Override
	public List findAll_msg_alarms(int alarmid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from msg_alarm where alarmid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,alarmid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List allalarmmsgInfo = query.list();
		return allalarmmsgInfo;
	}

	@Override
	public List findAll_msg_chat(int chatid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from msg_chat where chatid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,chatid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List allchatmsgInfo = query.list();
		return allchatmsgInfo;
	}

	@Override
	public List findAll_msg_notice(int noticeid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from msg_notice where noticeid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,noticeid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List allnoticemsgInfo = query.list();
		return allnoticemsgInfo;
	}

	@Override
	public List findAllcomment(int commentid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from comment where commentid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,commentid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List allcommentInfo = query.list();
		return allcommentInfo;
	}

	
}