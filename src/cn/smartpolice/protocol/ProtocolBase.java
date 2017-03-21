package cn.smartpolice.protocol;


import cn.smartpolice.workbean.PacketInfo;



/**
 * 具体协议处理基类
 * @author 刘超
 *
 */
public abstract class ProtocolBase {

	PacketInfo revPacket = new PacketInfo();//子协议特有数据对象
	abstract void ParsePktProto(PacketInfo packetInfo); //解析子协议
	abstract void ExecProto(); //子协议处理
	abstract byte[] PackPkt(int i); //子协议封装协议报文
	abstract void SendPkt(byte[] sendPacket); //发送一个协议报文处理
}
