package cn.smartpolice.netdao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * UserInf表的操作
 * @author 刘超
 *
 */
public class UserDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//通过id查找
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
	//通过user查找
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
	//插入记录 该方法有问题：SQLGrammarException
	public void insertAppuser(UserInf userInf){
		this.session.save(userInf);
		ts.commit();
	}
	////置state=0（删除标记 注销协议）
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
