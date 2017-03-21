package cn.smartpolice.netdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.smartpolice.hibernate.Msg_alarm;
import cn.smartpolice.hibernate.Msg_chat;
import cn.smartpolice.hibernate.Msg_notice;

/**
 * 消息数据库操作
 * @author 刘超
 *
 */
public class MsgDao {

	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//在msg_alarm中插入一条记录
	public void insertMsgAlarm(Msg_alarm alarm){
		this.session.save(alarm);
		ts.commit();
	}
	//在msg_chat中插入一条记录
	public void insertMsgChat(Msg_chat chat){
		this.session.save(chat);
		ts.commit();
	}
	//在msg_notice中插入一条记录
	public void insertMsgNotice(Msg_notice notice){
		this.session.save(notice);
		ts.commit();
	}
	// 在消息记录表中插入
	public void insertMsgRecv(MsgRecv recv){
		this.session.save(recv);
		ts.commit();
	}
	//通过接收消息设备id找到消息记录
	public MsgRecv findMsgRecvByRecvUserId(int recvuserid){
		Query query = session.createQuery("from MsgRecv where recvuserid = ?");
		query.setInteger(0, recvuserid);
		List<MsgRecv> list = query.list();
		ts.commit();
		if(list != null && list.size()==1){
			return list.get(0);
		}
		return null;
		
	}
}
