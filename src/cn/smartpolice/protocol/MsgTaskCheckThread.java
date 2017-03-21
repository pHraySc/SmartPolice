package cn.smartpolice.protocol;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.hibernate.Msg_alarm;
import cn.smartpolice.hibernate.Msg_chat;
import cn.smartpolice.hibernate.Msg_notice;
import cn.smartpolice.hibernate.Msg_publish;
import cn.smartpolice.netdao.MsgDao;
import cn.smartpolice.netdao.MsgRecv;
import cn.smartpolice.netdao.RelateDao;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.MsgTask;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

/**
 * ��Ϣ���ͼ���߳� �����Ϣ����������� �����в�Ϊnull���������node��Ϣ������Ϣ���ͼ�¼������Ӽ�¼�����ڴ��߳��и����ߵĽڵ㷢��δ����Ϣ֪ͨ
 * 
 * @author ����
 *
 */
public class MsgTaskCheckThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (MsgTask msgTask : SysInfo.getMsgTaskQueue()) {
			// ������ڵ���Ϣ�����Ǳ�����Ϣ
			if (msgTask.getmType() == 1) {
				// �����Ϣ��������Ϣ��
				Msg_alarm alarm = new Msg_alarm();
				alarm.setDeviceid(msgTask.getSendUserID());
				alarm.setUrl(msgTask.getAttach());
				alarm.setState(true);
				alarm.setTime((Date) msgTask.getmDate());
				String[] typeAndLevel = msgTask.getContent().split("+");
				alarm.setType(Integer.parseInt(typeAndLevel[0]));// ����
				alarm.setLevel(Integer.parseInt(typeAndLevel[1]));// ����
				// ����msg_alarm��
				new MsgDao().insertMsgAlarm(alarm);

				// �����ڽ����ţ�����Ϣ֪ͨ���͸����й�����
				if (msgTask.getRevUserID() == 0) {
					// ��ѯ������ϵ��õ����й�����id
					String[] userIdArray = new RelateDao().findUserIdByDeviceId(msgTask.getSendUserID());
					for (String recvuserid : userIdArray) {
						// ����Ϣ��¼�������ÿһ�����������һ����¼
						MsgRecv msgRecv = new MsgRecv();
						// ��������Ϣ���е���Ϣid�����¼���� ��Ҫ����ִ�в��������᲻�᷵��id??
						msgRecv.setMessageid(alarm.getAlarmid()); // ??
						msgRecv.setSenduserid(msgTask.getSendUserID());
						msgRecv.setRecvuserid(Integer.parseInt(recvuserid));
						msgRecv.setMsgtype("0");
						msgRecv.setState(1); // δ��
						msgRecv.setRecvtime(msgTask.getmDate());

						// ���Ҹù������Ƿ����ߣ�������ߣ���װһ��֪ͨ���ģ����͸��ù�����
						UserNode userNode = SysInfo.getInstance().getAppNodeById(Integer.parseInt(recvuserid));
						// userNode ����
						if (userNode != null) {
							// ����δ����Ϣ֪ͨ����
							byte[] unreadMsgNotice = PackPkt(msgTask);
							userNode.getIoSession().write(IoBuffer.wrap(unreadMsgNotice));
						}
					}
				}
				// ���ڽ�����id,ֻ��һ��������
				if (msgTask.getRevUserID() != 0) {
					byte[] unreadMsgNotice = PackPkt(msgTask);
					UserNode userNode = SysInfo.getInstance().getAppNodeById(msgTask.getRevUserID());
					userNode.getIoSession().write(IoBuffer.wrap(unreadMsgNotice));// ���ڽ������򽫱�����Ϣ���͸�������
				}

			}
			// type==2Ϊ������Ϣ
			if (msgTask.getmType() == 2) {
				Msg_chat chat = new Msg_chat();
				chat.setContent(msgTask.getContent());
				chat.setSendid(msgTask.getSendUserID());
				chat.setSendtime((Date) msgTask.getmDate());
				chat.setRecvid(msgTask.getRevUserID());
				// ����������Ϣ��
				new MsgDao().insertMsgChat(chat);
				//
				UserNode userNode = SysInfo.getInstance().getAppNodeById(msgTask.getRevUserID());
				// userNode ������
				if (userNode == null) {
					MsgRecv msgRecv = new MsgRecv();
					// ��������Ϣ���е���Ϣid�����¼���� ��Ҫ����ִ�в��������᲻�᷵��id??
					msgRecv.setMessageid(chat.getChatid());
					msgRecv.setSenduserid(msgTask.getSendUserID());
					msgRecv.setRecvuserid(msgTask.getRevUserID());
					msgRecv.setMsgtype("2");// 2Ϊ������Ϣ
					msgRecv.setState(1); // δ��
					msgRecv.setRecvtime(msgTask.getmDate());
					new MsgDao().insertMsgRecv(msgRecv);// ��δ����Ϣ������Ϣ��¼����
				} else { // ����Է����� ������Ϣ���ķ��͸��Է�
					userNode.getIoSession().write(PackPkts(msgTask));

				}

			}
			//֪ͨ��Ϣ
			if (msgTask.getmType() == 4) {
				Msg_notice notice = new Msg_notice();
				notice.setSendid(msgTask.getSendUserID());
				notice.setSendtime((Date) msgTask.getmDate());				
				String[] contents = msgTask.getContent().split("+");
				notice.setTitle(contents[0]);//����
				notice.setContent(contents[1]);
				new MsgDao().insertMsgNotice(notice);
				
			}

		}

	}

	// ����δ����Ϣ֪ͨ����
	public byte[] PackPkt(MsgTask msgTask) {

		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		byteChar[0] = ConstParam.CMD_5; // cmd=5

		byteChar[1] = ConstParam.TYPE_4;

		byteChar[2] = ConstParam.OPT_8; // opt=16 Ӧ����
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
		byte[] packets = byteArrayProc.sysCopy(packet); // �����byte[]ƴ��
		return packets;
	}

	// ������Ϣ����������
	public byte[] PackPkts(MsgTask msgTask) {
		String packetBody = null;
		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		byteChar[0] = ConstParam.CMD_5; // cmd=5

		byteChar[1] = ConstParam.TYPE_1;

		byteChar[2] = ConstParam.OPT_8; // opt=16 Ӧ����
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
		byte[] packets = byteArrayProc.sysCopy(packet); // �����byte[]ƴ��
		return packets;
	}

}
