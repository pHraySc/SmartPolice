package cn.smartpolice.protocol;


import cn.smartpolice.workbean.PacketInfo;



/**
 * ����Э�鴦�����
 * @author ����
 *
 */
public abstract class ProtocolBase {

	PacketInfo revPacket = new PacketInfo();//��Э���������ݶ���
	abstract void ParsePktProto(PacketInfo packetInfo); //������Э��
	abstract void ExecProto(); //��Э�鴦��
	abstract byte[] PackPkt(int i); //��Э���װЭ�鱨��
	abstract void SendPkt(byte[] sendPacket); //����һ��Э�鱨�Ĵ���
}
