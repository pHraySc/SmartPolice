package cn.smartpolice.netdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 关联人表操作
 * @author 刘超
 *
 */
public class RelateDao {

	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	public String[] findUserIdByDeviceId(int deviceid){
		String sql = "select userid from relate_inf where deviceid = ?";
		Query query = session.createSQLQuery(sql).setInteger(0, deviceid);
		List userIdList = (List) query.list();
		
		String[] userIdArray =(String[]) userIdList.toArray();
		return userIdArray;
	}
}
