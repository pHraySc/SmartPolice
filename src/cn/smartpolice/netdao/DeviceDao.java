package cn.smartpolice.netdao;

/**
 * Device_inf表的操作
 * @author 刘超
 *
 */
import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeviceDao {
	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	public DeviceInf findDevByID(int id){
		Query query = session.createQuery("from DeviceInf where deviceid = ?");
		query.setInteger(0, id);
		List<DeviceInf> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	//通过username查找
	public DeviceInf findDevByName(String name){
		Query query = session.createQuery("from DeviceInf where username=?");
		query.setString(0, name);
		List<DeviceInf> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	//添加记录
	public void insertDevInf(DeviceInf deviceInf){
		this.session.save(deviceInf);
		ts.commit();
	}
	//置state=0（删除标记 注销协议）
	public void changeStateToZero(int id){
		Query query = session.createQuery("update DeviceInf dev set dev.state = 0 where dev.deviceid =?");
		query.setInteger(0, id);
		ts.commit();
	}
	public void updateDevInf(DeviceInf deviceInf) {
		// TODO Auto-generated method stub
		this.session.update(deviceInf);
		ts.commit();
	}
}
