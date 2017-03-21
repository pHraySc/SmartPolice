package cn.smartpolice.protocol;

import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.PacketInfo;

public class ProtocolDiscoverLAN extends ProtocolBase {
	String user;
	int id;
	int ret;
	

	@Override
	void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		this.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
		if(revPacket.getType()==ConstParam.TYPE_1) {
			user = jsonAnalysis.getValue(data, "USER");
		}

	}

	@Override
	void ExecProto() {
		// TODO Auto-generated method stub

	}

	@Override
	byte[] PackPkt(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub

	}

}
