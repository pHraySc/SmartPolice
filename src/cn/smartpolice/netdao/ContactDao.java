package cn.smartpolice.netdao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contact表的操作
 */
public class ContactDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//插入联系人
	public void insertUserCon(ContactInf contactInf){
		this.session.save(contactInf);
		ts.commit();
	}
	//通过user cuser 查看Contact表是否是联系人
	public ContactInf findContactByuser(String user,String cuser){
		Query query = session.createQuery("from Contact where masterid = ? and  contactedid = ?");
		query.setString(0, user);
		List<ContactInf> list = query.list();
		ts.commit();
		if(list.contains(cuser)){
			return list.get(0);
		}
		return null;
	}
	
	//查询一个user的所有联系人

	public List<ContactInf> findallContactByuser(String user){
		
		Query query = session.createQuery("from Contact where masterid = ? and  contactedid = ?");
		query.setString(0, user);
		List<ContactInf> list = query.list();
		ts.commit();	
			return list;	
	}
	
	//删除表中的一条数据
	public ContactInf deleteContactInf(ContactInf contactInf){
		Session session = null;
		
		try{
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			contactInf =(ContactInf)session.load(ContactInf.class, contactInf.getContactId());//加载联系人主键的编号     
			session.delete(contactInf); //删除
			session.getTransaction().commit();
		}catch (HibernateException e) { 
			 e.printStackTrace();  
	         session.getTransaction().rollback();
		} finally{  //这里不知道为什么
            if (session != null){  
                if (session.isOpen()){  
                    session.close();  
                }  
            }  
        }  
		return null;
	}
	
	//更新表中的一条记录
	public ContactInf updateContactInf(ContactInf contactInf,int i){
		
        Session session = null;
        try{
        	session = HibernateUtil.currentSession();
        	session.beginTransaction();
			contactInf =(ContactInf)session.load(ContactInf.class, contactInf.getContactId());//加载联系人主键的编号     
			if(i == 1){
				contactInf.setAliasid("allas");
			}
			if(i == 2){
				contactInf.setGroup("group");
			}
			session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
        	HibernateUtil.closeSession();//不知道对不对
        }
		return null;
    }
}
