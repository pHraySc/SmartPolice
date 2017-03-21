package cn.smartpolice.protocol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.netdao.MsgDao;
import cn.smartpolice.netdao.MsgRecv;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.MsgTask;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;
import net.sf.json.JSONArray;

/**
 * ��Ϣ���� cmd=5
 * 
 * @author ���� ��Ϣ����Э����δ�������Ϣ���͵Ĵ���������Ϣ���ͼ�֪ͨ������δ������
 */
public class ProtocolMessage extends ProtocolBase {

	// type=1
	String user;
	String mType; // class 1������2���죬3֪ͨ
	String mDate;
	String content; // ��Ϣ���ݣ�������ϢΪ������+����
	String attach; // ��Ϣ������URL��
	int recv; // ��Ϣ�������˺�

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

	@Override
	void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		System.out.println("��Ϣ����");
		this.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");

		// ������Ϣ��������
		if (revPacket.getType() == ConstParam.TYPE_1) {
			user = jsonAnalysis.getValue(data, "USER");
			mType = jsonAnalysis.getValue(data, "CLASS");
			mDate = jsonAnalysis.getValue(data, "DATE");
			content = jsonAnalysis.getValue(data, "CONTENT");
			attach = jsonAnalysis.getValue(data, "ATTACH");
			recv = Integer.parseInt(jsonAnalysis.getValue(data, "RECV"));
		}
		// �����Ϣ��������
		if (revPacket.getType() == ConstParam.TYPE_3) {
			num = Integer.parseInt(jsonAnalysis.getValue(data, "NUM"));
			// ����info������ݣ������ݷֱ��������
			JSONArray jsonArr = JSONArray.fromObject(data);
			for (int i = 0; i < jsonArr.size(); i++) {
				users[i] = jsonArr.getJSONObject(i).getString("USER");
				mTypes[i] = jsonArr.getJSONObject(i).getString("CLASS");
				mDates[i] = jsonArr.getJSONObject(i).getString("DATE");
				contents[i] = jsonArr.getJSONObject(i).getString("CONTENT");
				attachs[i] = jsonArr.getJSONObject(i).getString("ATTACH");
				recvs[i] = jsonArr.getJSONObject(i).getString("RECV");
			}
		}

		// δ����Ϣ����
		if (revPacket.getType() == ConstParam.TYPE_5) {
			askMsgNum = Integer.parseInt(jsonAnalysis.getValue(data, "NUM"));
			askMsgType = jsonAnalysis.getValue(data, "CLASS");
		}

		this.ExecProto();
	}

	@Override
	void ExecProto() {
		// TODO Auto-generated method stub
		// ������Ϣ��������
		if (revPacket.getType() == ConstParam.TYPE_1) {
			// �ڹ������������һ������
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
			msgTask.setRevUserID(recv); // �����ڽ����ߣ���revIDΪ������ID,�������ڽ����ߣ�revIDΪ0
			SysInfo.getMsgTaskQueue().add(msgTask);
			// ������в�Ϊ�� ���������߳�
			if (SysInfo.getMsgTaskQueue() != null) {
				Thread msgTaskCheckThread = new Thread(new MsgTaskCheckThread());
				msgTaskCheckThread.start();
			}
		}
		// �����Ϣ����
		if (revPacket.getType() == ConstParam.TYPE_3) {
			// �ڹ������������һ������user[i] = jsonArr.getJSONObject(i).getString("USER");

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
				msgTask.setRevUserID(recv); // �����ڽ����ߣ���revIDΪ������ID,�������ڽ����ߣ�revIDΪ0
				SysInfo.getMsgTaskQueue().add(msgTask);
			}
			// ������в�Ϊ�� ���������߳�
			if (SysInfo.getMsgTaskQueue() != null) {
				Thread msgTaskCheckThread = new Thread(new MsgTaskCheckThread());
				msgTaskCheckThread.start();
			}
		}

		// δ����Ϣ����
		if (revPacket.getType() == ConstParam.TYPE_5) {
			MsgRecv msgRecv = new MsgDao().findMsgRecvByRecvUserId(recv);
			// ��Ϣδ��
			if (msgRecv.getState() == 1) {
				byte[] unReadMsg = PackPkt(ConstParam.SENT_PKT_TYPE_1);
				SendPkt(unReadMsg);
				// ɾ����Ϣδ���������
				MsgTask msgTask = SysInfo.getInstance().getMsgTaskByRecvUserId(recv);
				SysInfo.getMsgTaskQueue().remove(msgTask);
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
		byteChar[1] = ConstParam.TYPE_6; // δ����ϢӦ��
		byteChar[2] = ConstParam.OPT_16; // opt=16 Ӧ����
		byteChar[3] = ConstParam.SORT_3; // sort=3
		int sendseq = revPacket.getSeq(); // ??
		int ackseq = revPacket.getSeq(); // ??

		MsgTask msgTask = SysInfo.getInstance().getMsgTaskByRecvUserId(recv);
		String infoString = "{'DATA':{'USER':'" + msgTask.getSendUserID() + "','CLASS':'" + msgTask.getmType()
				+ "','DATA':'" + msgTask.getmDate() + "','CONTENT':'" + msgTask.getContent() + "'+'ATTACH':'"
				+ msgTask.getAttach() + "'}}";
		String packetBody = "{'DATA':{'NUM':'" + msgTask.getMsgNum() + "','INFO':'" + infoString + "'}}";

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

	@Override
	void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub
		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));
	}

}
