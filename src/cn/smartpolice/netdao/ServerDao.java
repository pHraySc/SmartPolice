package cn.smartpolice.netdao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 对server_inf表的操作
 * @author 刘超
 *
 */
public class ServerDao {
	
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//查找服务器信息
	public ServerInf findServer(int id){
		
		Query query = session.createQuery("from ServerInf where serverId = ?");
		query.setInteger(0, id);
		List<ServerInf> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
}
