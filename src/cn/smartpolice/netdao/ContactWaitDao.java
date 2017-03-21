package cn.smartpolice.netdao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * contact_wait的处理
 * @author caicai
 *
 */
public class ContactWaitDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//插入等待确认联系人
	public void insertContactWait(ContactWait contactWait){
		this.session.save(contactWait);
		ts.commit();
	}
	
	//删除确认过的等待联系人的请求
	public ContactInf deleteContactWait(ContactWait contactWait){
		Session session = null;
		
		try{
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			contactWait =(ContactWait)session.load(ContactWait.class, contactWait.getCwid());//加载联系人主键的编号     
			session.delete(contactWait); //删除
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
}
//  sql语句 不会  
