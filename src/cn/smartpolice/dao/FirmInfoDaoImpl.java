package cn.smartpolice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.net.httpserver.Authenticator.Success;

import cn.smartpolice.hibernate.CompanyUser;
import cn.smartpolice.hibernate.ManagerInfo;


@Repository("firmInfoDao")
@Transactional(readOnly=false)

public class FirmInfoDaoImpl implements FirmInfoDao{
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public CompanyUser findFirm(String username) {
		// TODO Auto-generated method stub
		List<CompanyUser> firm=(List<CompanyUser>) this.hibernateTemplate.find("from CompanyUser where username=?",new String(username));
		if(firm.size()!=0){
			return firm.get(0);
		} else{
		return null;
		}
	}
	
	@Override
	public void register(CompanyUser firm) {
		this.hibernateTemplate.save(firm);
		
	}

	@Override
	public String checkuser(String username) {
		
	Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String sql="select username from company_user ";
			//获得SQLQuery对象
		  SQLQuery query = session.createSQLQuery(sql);
		  //设定结果结果集中的每个对象为Map类型   
		  query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		  //执行查询
		  List list = query.list();
		  System.out.println("List:"+list);
		  System.out.println(username);
		  boolean b = list.contains(username);
		  
		  	
		if(b==true){
			 System.out.println("成功了");		}
		/*else{
			System.out.println(username);
			return "success";
		}
		*/
		  return "error";
	}


	



}
