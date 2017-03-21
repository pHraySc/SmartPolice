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

@Repository("userInfoDao")
@Transactional(readOnly=false)
public class UserInfoDaoImpl implements UserInfoDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List findAllUserinfo(){
				
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="select user_inf.userid,user_inf.username,user_inf.`name`,user_inf.type,user_inf.authority FROM user_inf";
				
		List allUserinfo=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allUserinfo;
	}

	@Override
	public List findManagerUserInfo(int userid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from user_inf where userid=? ";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,userid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List allUserInfo = query.list();
		return allUserInfo;	}
}