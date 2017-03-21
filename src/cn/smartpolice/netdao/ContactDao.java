package cn.smartpolice.netdao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contact��Ĳ���
 */
public class ContactDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//������ϵ��
	public void insertUserCon(ContactInf contactInf){
		this.session.save(contactInf);
		ts.commit();
	}
	//ͨ��user cuser �鿴Contact���Ƿ�����ϵ��
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
	
	//��ѯһ��user��������ϵ��

	public List<ContactInf> findallContactByuser(String user){
		
		Query query = session.createQuery("from Contact where masterid = ? and  contactedid = ?");
		query.setString(0, user);
		List<ContactInf> list = query.list();
		ts.commit();	
			return list;	
	}
	
	//ɾ�����е�һ������
	public ContactInf deleteContactInf(ContactInf contactInf){
		Session session = null;
		
		try{
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			contactInf =(ContactInf)session.load(ContactInf.class, contactInf.getContactId());//������ϵ�������ı��     
			session.delete(contactInf); //ɾ��
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
	
	//���±��е�һ����¼
	public ContactInf updateContactInf(ContactInf contactInf,int i){
		
        Session session = null;
        try{
        	session = HibernateUtil.currentSession();
        	session.beginTransaction();
			contactInf =(ContactInf)session.load(ContactInf.class, contactInf.getContactId());//������ϵ�������ı��     
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
        	HibernateUtil.closeSession();//��֪���Բ���
        }
		return null;
    }
}
