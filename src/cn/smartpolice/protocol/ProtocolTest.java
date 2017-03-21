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
 * 测试
 * 
 * @author 刘超
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
		// 从数据库中获取
		ServerDao serverDao = new ServerDao();
		ServerInf server = serverDao.findServer(1);
		int sid = server.getServerId();
		int sendseq=0; 
		int ackseq = revPacket.getSeq(); // ACK的值是收到请求报文的序号，具有相关性
		switch (i) {
		case 1: // 返回联通测试报文
			byteChar[0] = 0; // cmd =1
			byteChar[1] = 0; // type=2
			byteChar[2] = 8; // opt=16 表示此报文是应答报文，确认号有效；
			byteChar[3] = 3; // sort=3 SORT表示发送方类别 3代表web服务器类

			packet.add(byte1);
			packet.add(byteChar);
			packet.add(int2bytes(sid));
			packet.add(int2bytes(++sendseq));
			packet.add(int2bytes(ackseq));
			break;
		}
		byte[] packets = sysCopy(packet); // 将多个byte[]拼接
		return packets;
	}

	@Override
	public void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub

		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));
	}

	// int 转换为byte
	public byte[] int2bytes(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) (0xff & i);
		b[1] = (byte) ((0xff00 & i) >> 8);
		b[2] = (byte) ((0xff0000 & i) >> 16);
		b[3] = (byte) ((0xff000000 & i) >> 24);
		return b;
	}

	// 系统提供的数组拷贝方法arraycopy
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
