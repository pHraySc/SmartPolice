package cn.smartpolice.protocol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.hibernate.Msg_alarm;
import cn.smartpolice.hibernate.Msg_chat;
import cn.smartpolice.netdao.MsgDao;
import cn.smartpolice.netdao.MsgRecv;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.MsgTask;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;
import net.sf.json.JSONArray;

/**
 * 消息传递 cmd=5
 * 
 * @author 刘超 消息推送协议中未做多个消息推送的处理，单个消息推送及通知、请求未做测试
 */
public class ProtocolMessage extends ProtocolBase {

	// type=1
	String user;
	String mType; // class 1报警，2聊天，3通知
	String mDate;
	String content; // 消息内容（报警消息为：类型+级别）
	String attach; // 消息附件（URL）
	int recv; // 消息接受者账号
	MsgRecv msgRecv = null;

	// type=3
	int num;
	String info;
	// type=5
	String askMsgType;
	int askMsgNum;
	String[] users = null;
	String[] mTypes = null;
	String[] mDates = null;
	String[] contents = null;
	String[] attachs = null;
	String[] recvs = null;
	String infoString;

	@Override
	void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		System.out.println("消息推送");
		this.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");

		// 单个消息推送请求
		if (revPacket.getType() == ConstParam.TYPE_1) {

			user = jsonAnalysis.getValue(data, "USER");

			mType = jsonAnalysis.getValue(data, "CLASS");

			mDate = jsonAnalysis.getValue(data, "DATE");

			content = jsonAnalysis.getValue(data, "CONTENT");

			attach = jsonAnalysis.getValue(data, "ATTACH");

			recv = Integer.getInteger(jsonAnalysis.getValue(data, "RECV"));
			

		}

		// 多个消息推送请求
		if (revPacket.getType() == ConstParam.TYPE_3) {
			num = Integer.parseInt(jsonAnalysis.getValue(data, "NUM"));
			// 解析info里的内容，将数据分别存入数组
			info = jsonAnalysis.getValue(data, "INFO");
			JSONArray jsonArr = JSONArray.fromObject(info);
			for (int i = 0; i < jsonArr.size(); i++) {
				users[i] = jsonArr.getJSONObject(i).getString("USER");
				mTypes[i] = jsonArr.getJSONObject(i).getString("CLASS");
				mDates[i] = jsonArr.getJSONObject(i).getString("DATE");
				contents[i] = jsonArr.getJSONObject(i).getString("CONTENT");
				attachs[i] = jsonArr.getJSONObject(i).getString("ATTACH");
				recvs[i] = jsonArr.getJSONObject(i).getString("RECV");
			}
		}

		// 未读消息请求
		if (revPacket.getType() == ConstParam.TYPE_5) {
			askMsgNum = Integer.parseInt(jsonAnalysis.getValue(data, "NUM"));
			askMsgType = jsonAnalysis.getValue(data, "CLASS");
		}

		this.ExecProto();
	}

	@Override
	void ExecProto() {
		// TODO Auto-generated method stub
		// 单个消息推送请求
		if (revPacket.getType() == ConstParam.TYPE_1) {
			// 在工作队列中添加一个任务
			MsgTask msgTask = new MsgTask();
			msgTask.setMsgNum(1);
			msgTask.setSendUserID(Integer.parseInt(user));
			msgTask.setmType(Integer.parseInt(mType));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				msgTask.setmDate(simpleDateFormat.parse(mDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msgTask.setContent(content);
			msgTask.setAttach(attach);
			msgTask.setRevUserID(recv); // 若存在接收者，则revID为接收者ID,若不存在接收者，revID为0
			SysInfo.getMsgTaskQueue().add(msgTask);
			// 任务队列不为空 启动工作线程
			if (SysInfo.getMsgTaskQueue() != null) {
				Thread msgTaskCheckThread = new Thread(new MsgTaskCheckThread());
				msgTaskCheckThread.start();
			}
		}

		// 多个消息推送
		if (revPacket.getType() == ConstParam.TYPE_3) {
			// 在工作队列中添加一个任务user[i] = jsonArr.getJSONObject(i).getString("USER");

			MsgTask msgTask = new MsgTask();
			msgTask.setMsgNum(num);
			for (int i = 0; i < num; i++) {
				user = users[i];
				mType = mTypes[i];
				mDate = mDates[i];
				content = contents[i];
				attach = attachs[i];
				msgTask.setMsgNum(num);
				msgTask.setSendUserID(Integer.parseInt(user));
				msgTask.setmType(Integer.parseInt(mType));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					msgTask.setmDate(simpleDateFormat.parse(mDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msgTask.setContent(content);
				msgTask.setAttach(attach);
				msgTask.setRevUserID(recv); // 若存在接收者，则revID为接收者ID,若不存在接收者，revID为0
				SysInfo.getMsgTaskQueue().add(msgTask);
			}
			// 任务队列不为空 启动工作线程
			if (SysInfo.getMsgTaskQueue() != null) {
				Thread msgTaskCheckThread = new Thread(new MsgTaskCheckThread());
				msgTaskCheckThread.start();
			}
		}

		// 未读消息请求
		if (revPacket.getType() == ConstParam.TYPE_5) {
			msgRecv = new MsgDao().findMsgRecvByRecvUserId(revPacket.getSid());
			// 消息未读
			if (msgRecv.getState() == 1) {
				if (msgRecv.getMsgtype() == "1") {// 报警
					byte[] unReadMsg = PackPkt(1);
					SendPkt(unReadMsg);
				}
				if (msgRecv.getMsgtype() == "2") {// 聊天
					byte[] unReadMsg = PackPkt(2);
					SendPkt(unReadMsg);
				}
				if (msgRecv.getMsgtype() == "4") {// 通知
					byte[] unReadMsg = PackPkt(4);
					SendPkt(unReadMsg);

				}
				if (msgRecv.getMsgtype() == "0") {// 通知
					byte[] unReadMsg = PackPkt(3);// 所有类型
					SendPkt(unReadMsg);

				}

				// 删除消息未读任务队列
				// MsgTask msgTask =
				// SysInfo.getInstance().getMsgTaskByRecvUserId(revPacket.getSid());
				// SysInfo.getMsgTaskQueue().remove(msgTask);
			}
		}
	}

	@Override
	byte[] PackPkt(int i) {

		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		byteChar[0] = ConstParam.CMD_5; // cmd=5
		byteChar[1] = ConstParam.TYPE_6; // 未读消息应答
		byteChar[2] = ConstParam.OPT_16; // opt=16 应答报文
		byteChar[3] = ConstParam.SORT_3; // sort=3
		int sendseq = revPacket.getSeq(); // ??
		int ackseq = revPacket.getSeq(); // ??
		MsgTask msgTask = new MsgTask();
		switch (i) {
		case 1:
			Msg_alarm alarm = new MsgDao().findMsgalarmByRecvUserId(msgRecv.getRecvuserid());//
			if (alarm != null) {
				infoString = "{'DATA':{'USER':'" + alarm.getDeviceid() + "','CLASS':'" + 1 + "','DATA':'"
						+ alarm.getTime() + "','CONTENT':'" + alarm.getType() + alarm.getLevel() + "'+'ATTACH':'"
						+ alarm.getUrl() + "'}}";
			}
			break;
		case 2:

			Msg_chat chat = new MsgDao().findMsgChatByRecvUserId(msgRecv.getRecvuserid());//
			if (chat != null) {
				infoString = "{'DATA':{'USER':'" + chat.getSendid() + "','CLASS':'" + 2 + "','DATA':'"
						+ chat.getSendtime() + "','CONTENT':'" + chat.getContent() + "'+'ATTACH':'" + null + "'}}";

			}
			break;
		}

		String packetBody = "{'DATA':{'NUM':'" + 1 + "','INFO':'" + infoString + "'}}";
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

	@Override
	void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub
		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));
	}

}
