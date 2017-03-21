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
import cn.smartpolice.hibernate.ManagerInfo;


@Repository("managerInfoDao")
@Transactional(readOnly=false)
public class ManagerInfoDaoImpl implements ManagerInfoDao {
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public ManagerInfo findManager(String username) {
		// TODO Auto-generated method stub
		List<ManagerInfo> manager=(List<ManagerInfo>) this.hibernateTemplate.find("from ManagerInfo where username=?",new String(username));
		if(manager.size()!=0){
			return manager.get(0);
		} else{
		return null;
		}
	}

	@Override
	public List managerMsg(){
		
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT COUNT(*) as a FROM `comment` WHERE handle='0'";
		SQLQuery query=session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List List = query.list();
		return List;
	}


}
