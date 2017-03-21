package cn.smartpolice.netdao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * ��server_inf��Ĳ���
 * @author ����
 *
 */
public class ServerDao {
	
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//���ҷ�������Ϣ
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
