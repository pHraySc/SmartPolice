package cn.smartpolice.netdao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * UserInf��Ĳ���
 * @author ����
 *
 */
public class UserDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//ͨ��id����
	public UserInf findAppuserByID(int id){
		Query query = session.createQuery("from UserInf where userid = ?");
		query.setInteger(0, id);
		List<UserInf> list = query.list();
		ts.commit();
		
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	//ͨ��user����
	public UserInf findAppuserByName(String name){
		Query query = session.createQuery("from UserInf where username=?");
		query.setString(0, name);
		List<UserInf> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	//�����¼ �÷��������⣺SQLGrammarException
	public void insertAppuser(UserInf userInf){
		this.session.save(userInf);
		ts.commit();
	}
	////��state=0��ɾ����� ע��Э�飩
	public void changeStateToZero(int id){
		Query query = session.createQuery("update UserInf app set app.state = 0 where app.deviceid =?");
		query.setInteger(0, id);
		ts.commit();
	}
	public void updateAppuser(UserInf userInf) {
		// TODO Auto-generated method stub
		this.session.update(userInf);
		ts.commit();
	}
}
