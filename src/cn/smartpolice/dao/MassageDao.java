package cn.smartpolice.dao;

import java.util.List;

public interface MassageDao {
	public List findallMsg_alarms();//查找全部设备报警消息

	public List findallMsg_chat();//查找全部聊天消息

	public List findallMsg_notcie();//查找全部通知消息

	public List findallComment();//查找全部反馈消息
	
	public List findAll_msg_alarms(int alarmid);//报警消息的详情

	public List findAll_msg_chat(int chatid);//聊天消息的详情

	public List findAll_msg_notice(int noticeid);//通知消息的详情

	public List findAllcomment(int commentid);//反馈消息详情
	
}

