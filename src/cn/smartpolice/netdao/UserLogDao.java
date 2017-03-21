package cn.smartpolice.netdao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 对user_log表的操作
 * @author 刘超
 *
 */
public class UserLogDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//插入记录
	public void insertUserLogInf(UserLog userLog){
		this.session.save(userLog);
		ts.commit();
	}
	//通过deviceid和operate=-1查找记录
	public DevLog findDevLogByAppIdAndOperate(int appid){
		Query query = session.createQuery("from UserLog where userid = ? and operate = -1");
		query.setInteger(0, appid);
		List<DevLog> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
}
