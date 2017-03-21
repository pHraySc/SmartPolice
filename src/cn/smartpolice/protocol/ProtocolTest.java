package cn.smartpolice.protocol;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import cn.smartpolice.netdao.ServerDao;
import cn.smartpolice.netdao.ServerInf;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;

/**
 * ����
 * 
 * @author ����
 *
 */
public class ProtocolTest extends ProtocolBase {

	PacketInfo revPacket;
	

	@Override
	public void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		this.revPacket = packetInfo;
	}

	@Override
	public void ExecProto() {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] PackPkt(int i) {
		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		// �����ݿ��л�ȡ
		ServerDao serverDao = new ServerDao();
		ServerInf server = serverDao.findServer(1);
		int sid = server.getServerId();
		int sendseq=0; 
		int ackseq = revPacket.getSeq(); // ACK��ֵ���յ������ĵ���ţ����������
		switch (i) {
		case 1: // ������ͨ���Ա���
			byteChar[0] = 0; // cmd =1
			byteChar[1] = 0; // type=2
			byteChar[2] = 8; // opt=16 ��ʾ�˱�����Ӧ���ģ�ȷ�Ϻ���Ч��
			byteChar[3] = 3; // sort=3 SORT��ʾ���ͷ���� 3����web��������

			packet.add(byte1);
			packet.add(byteChar);
			packet.add(int2bytes(sid));
			packet.add(int2bytes(++sendseq));
			packet.add(int2bytes(ackseq));
			break;
		}
		byte[] packets = sysCopy(packet); // �����byte[]ƴ��
		return packets;
	}

	@Override
	public void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub

		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));
	}

	// int ת��Ϊbyte
	public byte[] int2bytes(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) (0xff & i);
		b[1] = (byte) ((0xff00 & i) >> 8);
		b[2] = (byte) ((0xff0000 & i) >> 16);
		b[3] = (byte) ((0xff000000 & i) >> 24);
		return b;
	}

	// ϵͳ�ṩ�����鿽������arraycopy
	public byte[] sysCopy(List<byte[]> srcArrays) {
		int len = 0;
		for (byte[] srcArray : srcArrays) {
			len += srcArray.length;
		}
		byte[] destArray = new byte[len];
		int destLen = 0;
		for (byte[] srcArray : srcArrays) {
			System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
			destLen += srcArray.length;
		}
		return destArray;
	}
}
