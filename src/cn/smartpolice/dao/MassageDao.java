package cn.smartpolice.dao;

import java.util.List;

public interface MassageDao {
	public List findallMsg_alarms();//����ȫ���豸������Ϣ

	public List findallMsg_chat();//����ȫ��������Ϣ

	public List findallMsg_notcie();//����ȫ��֪ͨ��Ϣ

	public List findallComment();//����ȫ��������Ϣ
	
	public List findAll_msg_alarms(int alarmid);//������Ϣ������

	public List findAll_msg_chat(int chatid);//������Ϣ������

	public List findAll_msg_notice(int noticeid);//֪ͨ��Ϣ������

	public List findAllcomment(int commentid);//������Ϣ����
	
}

