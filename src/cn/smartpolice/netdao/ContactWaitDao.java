package cn.smartpolice.netdao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * contact_wait�Ĵ���
 * @author caicai
 *
 */
public class ContactWaitDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//����ȴ�ȷ����ϵ��
	public void insertContactWait(ContactWait contactWait){
		this.session.save(contactWait);
		ts.commit();
	}
	
	//ɾ��ȷ�Ϲ��ĵȴ���ϵ�˵�����
	public ContactInf deleteContactWait(ContactWait contactWait){
		Session session = null;
		
		try{
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			contactWait =(ContactWait)session.load(ContactWait.class, contactWait.getCwid());//������ϵ�������ı��     
			session.delete(contactWait); //ɾ��
			session.getTransaction().commit();
		}catch (HibernateException e) { 
			 e.printStackTrace();  
	         session.getTransaction().rollback();
		} finally{  //���ﲻ֪��Ϊʲô
            if (session != null){  
                if (session.isOpen()){  
                    session.close();  
                }  
            }  
        }  
		return null;
	}
}
//  sql��� ����  
