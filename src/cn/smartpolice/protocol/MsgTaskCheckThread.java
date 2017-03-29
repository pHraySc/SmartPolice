package cn.smartpolice.protocol;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.hibernate.Msg_alarm;
import cn.smartpolice.hibernate.Msg_chat;
import cn.smartpolice.hibernate.Msg_notice;
import cn.smartpolice.netdao.MsgDao;
import cn.smartpolice.netdao.MsgRecv;
import cn.smartpolice.netdao.RelateDao;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.MsgTask;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

/**
 * 消息推送检查线程 检查消息推送任务队列 若队列不为null则根据任务node信息，在消息发送记录表中添加记录，并在此线程中给在线的节点发送未读消息通知
 * 
 * @author 刘超
 *
 */
public class MsgTaskCheckThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (MsgTask msgTask : SysInfo.getMsgTaskQueue()) {
			// 该任务节点消息类型是报警消息
			if (msgTask.getmType() == 1) {
				// 添加消息到报警消息表
				Msg_alarm alarm = new Msg_alarm();
				alarm.setDeviceid(msgTask.getSendUserID());
				alarm.setUrl(msgTask.getAttach());
				alarm.setState(true);
				alarm.setTime((Date) msgTask.getmDate());
				String[] typeAndLevel = msgTask.getContent().split("+");
				alarm.setType(Integer.parseInt(typeAndLevel[0]));// 类型
				alarm.setLevel(Integer.parseInt(typeAndLevel[1]));// 级别
				// 插入msg_alarm表
				new MsgDao().insertMsgAlarm(alarm);

				// 不存在接收着，将消息通知推送给所有关联人
				if (msgTask.getRevUserID() == 0) {
					// 查询关联关系表得到所有关联人id
					String[] userIdArray = new RelateDao().findUserIdByDeviceId(msgTask.getSendUserID());
					for (String recvuserid : userIdArray) {
						// 查找该关联人是否在线，如果在线，封装一个通知报文，发送给该关联人
						UserNode userNode = SysInfo.getInstance().getAppNodeById(Integer.parseInt(recvuserid));
						// userNode 在线
						if (userNode != null) {
							// 封装通知消息发送报文，将通知消息发送给在线的人
							byte[] msgNotice = PackPkt(msgTask);
							userNode.getIoSession().write(IoBuffer.wrap(msgNotice));
							// 在消息记录表中针对每一个关联人添加一条记录
							MsgRecv msgRecv = new MsgRecv();
							// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
							msgRecv.setMessageid(alarm.getAlarmid()); // ??
							msgRecv.setSenduserid(msgTask.getSendUserID());
							msgRecv.setRecvuserid(Integer.parseInt(recvuserid));
							msgRecv.setMsgtype("0");
							msgRecv.setState(1); // 标记为未读
							msgRecv.setRecvtime(msgTask.getmDate());
						}
						// 如果userNode不在线
						// 存入消息记录表
						if (userNode == null) {
							MsgRecv msgRecv = new MsgRecv();
							// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
							msgRecv.setMessageid(alarm.getAlarmid()); // ??
							msgRecv.setSenduserid(msgTask.getSendUserID());
							msgRecv.setRecvuserid(Integer.parseInt(recvuserid));
							msgRecv.setMsgtype("0");
							msgRecv.setState(1); // 标记为未读
							msgRecv.setRecvtime(msgTask.getmDate());
						}

					}
				}
				// 存在接收者id,只有一个接收者
				if (msgTask.getRevUserID() != 0) {

					UserNode userNode = SysInfo.getInstance().getAppNodeById(msgTask.getRevUserID());
					if (userNode != null) {
						// 在线就发送通知报文，未在线存入消息记录表，标记为未读
						byte[] msgNotice = PackPkt(msgTask);
						userNode.getIoSession().write(IoBuffer.wrap(msgNotice));
						
							// 在消息记录表中针对每一个关联人添加一条记录
							MsgRecv msgRecv = new MsgRecv();
							// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
							msgRecv.setMessageid(alarm.getAlarmid()); // ??
							msgRecv.setSenduserid(msgTask.getSendUserID());
							msgRecv.setRecvuserid(msgTask.getRevUserID());
							msgRecv.setMsgtype("0");
							msgRecv.setState(1); // 标记为未读
							msgRecv.setRecvtime(msgTask.getmDate());
						}
						
					
					if (userNode == null) {
						MsgRecv msgRecv = new MsgRecv();
						// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
						msgRecv.setMessageid(alarm.getAlarmid()); // ??
						msgRecv.setSenduserid(msgTask.getSendUserID());
						msgRecv.setRecvuserid(msgTask.getRevUserID());
						msgRecv.setMsgtype("0");
						msgRecv.setState(1); // 标记为未读
						msgRecv.setRecvtime(msgTask.getmDate());
					}

				}

			}
			// type==2为聊天消息
			if (msgTask.getmType() == 2) {
				Msg_chat chat = new Msg_chat();
				chat.setContent(msgTask.getContent());
				chat.setSendid(msgTask.getSendUserID());
				chat.setSendtime((Date) msgTask.getmDate());
				chat.setRecvid(msgTask.getRevUserID());
				// 插入聊天消息表
				new MsgDao().insertMsgChat(chat);
				//
				UserNode userNode = SysInfo.getInstance().getAppNodeById(msgTask.getRevUserID());
				// userNode 在线
				if(userNode != null) {
					userNode.getIoSession().write(PackPkts(msgTask));//在线即发送给对方
					MsgRecv msgRecv = new MsgRecv();
					// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
					msgRecv.setMessageid(chat.getChatid());
					msgRecv.setSenduserid(msgTask.getSendUserID());
					msgRecv.setRecvuserid(msgTask.getRevUserID());
					msgRecv.setMsgtype("2");// 2为聊天消息
					msgRecv.setState(0); // 已读
					msgRecv.setRecvtime(msgTask.getmDate());
					new MsgDao().insertMsgRecv(msgRecv);// 将未读消息插入消息记录表中
				}
				
				if (userNode == null) {
					MsgRecv msgRecv = new MsgRecv();
					// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
					msgRecv.setMessageid(chat.getChatid());
					msgRecv.setSenduserid(msgTask.getSendUserID());
					msgRecv.setRecvuserid(msgTask.getRevUserID());
					msgRecv.setMsgtype("2");// 2为聊天消息
					msgRecv.setState(1); // 未读
					msgRecv.setRecvtime(msgTask.getmDate());
					new MsgDao().insertMsgRecv(msgRecv);// 将未读消息插入消息记录表中
				}

			}
			// 通知消息
			if (msgTask.getmType() == 4) {
				Msg_notice notice = new Msg_notice();
				notice.setSendid(msgTask.getSendUserID());
				notice.setSendtime((Date) msgTask.getmDate());
				String[] contents = msgTask.getContent().split("+");
				notice.setTitle(contents[0]);// 标题
				notice.setContent(contents[1]);
				new MsgDao().insertMsgNotice(notice);
				// 为0全部人员
				if (msgTask.getRevUserID() == 0) {
					String[] userIdArray = new RelateDao().findUserIdByDeviceId(msgTask.getSendUserID());
					for (String recvuserid : userIdArray) {
						MsgRecv msgRecv = new MsgRecv();
						// 将报警消息表中的消息id放入记录表中 需要测试执行插入操作后会不会返回id??
						msgRecv.setMessageid(notice.getNoticeid()); // ??
						msgRecv.setSenduserid(msgTask.getSendUserID());
						msgRecv.setRecvuserid(Integer.parseInt(recvuserid));
						msgRecv.setMsgtype("4");
						msgRecv.setState(1); // 未读
						msgRecv.setRecvtime(msgTask.getmDate());

						// 查找该关联人是否在线，如果在线，封装一个通知报文，发送给该关联人
						UserNode userNode = SysInfo.getInstance().getAppNodeById(Integer.parseInt(recvuserid));
						// userNode 在线
						if (userNode != null) {
							// 构造未读消息通知报文
							byte[] unreadMsgNotice = PackPkt(msgTask);
							userNode.getIoSession().write(IoBuffer.wrap(unreadMsgNotice));
						}
					}
				}
				// 发布记录（如何读取所有人信息）
				if (msgTask.getRevUserID() == 1) {
					byte[] unreadMsgNotice = PackPkt(msgTask);
					UserNode userNode = SysInfo.getInstance().getAppNodeById(msgTask.getRevUserID());
					userNode.getIoSession().write(IoBuffer.wrap(unreadMsgNotice));// 存在接受者则将报警消息发送给接收者
				}
				// 单个人员
				if (msgTask.getRevUserID() == 2) {
					byte[] unreadMsgNotice = PackPkt(msgTask);
					UserNode userNode = SysInfo.getInstance().getAppNodeById(msgTask.getRevUserID());
					userNode.getIoSession().write(IoBuffer.wrap(unreadMsgNotice));// 存在接受者则将通知消息发送给接收者
				}

			}

		}

	}

	// 构造未读消息通知报文
	public byte[] PackPkt(MsgTask msgTask) {

		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		byteChar[0] = ConstParam.CMD_5; // cmd=5

		byteChar[1] = ConstParam.TYPE_4;

		byteChar[2] = ConstParam.OPT_8; // opt=16 应答报文
		byteChar[3] = ConstParam.SORT_3; // sort=3
		int sendseq = msgTask.getPacketInfo().getSeq(); // ??
		int ackseq = msgTask.getPacketInfo().getSeq(); // ??
		String packetBody = "{'DATA':{'CLASS':'" + msgTask.getmType() + "','NUM':'" + msgTask.getMsgNum() + "'}}";
		ByteArrayProc byteArrayProc = new ByteArrayProc();
		packet.add(byte1);
		packet.add(byteChar);
		packet.add(byteArrayProc.int2bytes(ConstParam.SERVER_ID));
		packet.add(byteArrayProc.int2bytes(++sendseq));
		packet.add(byteArrayProc.int2bytes(ackseq));
		String packetBodyJson = new JsonAnalysis().getJsonByObject(packetBody);
		byte[] packetBodyJsonByte = packetBodyJson.getBytes();
		packet.add(packetBodyJsonByte);
		byte[] packets = byteArrayProc.sysCopy(packet); // 将多个byte[]拼接
		return packets;
	}

	// 构造消息推送请求报文
	public byte[] PackPkts(MsgTask msgTask) {
		String packetBody = null;
		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		byteChar[0] = ConstParam.CMD_5; // cmd=5

		byteChar[1] = ConstParam.TYPE_1;

		byteChar[2] = ConstParam.OPT_8; // opt=16 应答报文
		byteChar[3] = ConstParam.SORT_3; // sort=3
		int sendseq = msgTask.getPacketInfo().getSeq(); // ??
		int ackseq = msgTask.getPacketInfo().getSeq(); // ??
		if (msgTask.getMsgNum() == 1) {
			packetBody = "{'DATA':{'USER':'" + msgTask.getSendUserID() + "','CLASS':'" + msgTask.getmType()
					+ "','DATA':'" + msgTask.getmDate() + "','CONTENT':'" + msgTask.getContent() + "'+'ATTACH':'"
					+ msgTask.getAttach() + "'}}";
		}
		ByteArrayProc byteArrayProc = new ByteArrayProc();
		packet.add(byte1);
		packet.add(byteChar);
		packet.add(byteArrayProc.int2bytes(ConstParam.SERVER_ID));
		packet.add(byteArrayProc.int2bytes(++sendseq));
		packet.add(byteArrayProc.int2bytes(ackseq));
		String packetBodyJson = new JsonAnalysis().getJsonByObject(packetBody);
		byte[] packetBodyJsonByte = packetBodyJson.getBytes();
		packet.add(packetBodyJsonByte);
		byte[] packets = byteArrayProc.sysCopy(packet); // 将多个byte[]拼接
		return packets;
	}

}
