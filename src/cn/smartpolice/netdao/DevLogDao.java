package cn.smartpolice.netdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * ��device_log��Ĳ���
 * @author ����
 *
 */
public class DevLogDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//���devLog��¼
	public void insertDevLogInf(DevLog devLog){
		this.session.save(devLog);
		ts.commit();
	}
	//ͨ��deviceid��operate=-1���Ҽ�¼
	public DevLog findDevLogByDevIdAndOperate(int deviceid){
		Query query = session.createQuery("from DevLog where deviceid = ? and operate = -1");
		query.setInteger(0, deviceid);
		List<DevLog> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
}
