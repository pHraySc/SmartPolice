package cn.smartpolice.netdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.smartpolice.hibernate.Msg_alarm;
import cn.smartpolice.hibernate.Msg_chat;
import cn.smartpolice.hibernate.Msg_notice;

/**
 * ��Ϣ���ݿ����
 * @author ����
 *
 */
public class MsgDao {

	private Session session = HibernateUtil.currentSession();
	private Transaction ts = session.beginTransaction();
	//��msg_alarm�в���һ����¼
	public void insertMsgAlarm(Msg_alarm alarm){
		this.session.save(alarm);
		ts.commit();
	}
	//��msg_chat�в���һ����¼
	public void insertMsgChat(Msg_chat chat){
		this.session.save(chat);
		ts.commit();
	}
	//��msg_notice�в���һ����¼
	public void insertMsgNotice(Msg_notice notice){
		this.session.save(notice);
		ts.commit();
	}
	// ����Ϣ��¼���в���
	public void insertMsgRecv(MsgRecv recv){
		this.session.save(recv);
		ts.commit();
	}
	//ͨ��������Ϣ�豸id�ҵ���Ϣ��¼
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
