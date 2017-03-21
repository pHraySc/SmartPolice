package cn.smartpolice.protocol;

import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.PacketInfo;

public class ProtocolRelate extends ProtocolBase {

	int ref; //1��ʾ����Ա����0��ʾ��������
	String user;
	String muser; //����������˺�
	String puser; //ǰ���豸�˺�
	String allas; //�豸����
	int right; //Ȩ�޴��룺0����Ȩ�ޣ�1�ٿ�Ȩ�ޣ�2�鿴Ȩ�ޣ�3����Ȩ��
	String pass; //��¼ǰ���豸���룬Ϊ0����û�����ѡ���ʾֻ�Ǽǵ�ƽ̨�Ľ���Ȩ��
	byte ret; //���ش��룬0��ʾ�ȴ�ȷ�ϣ�-1��ʾ�����ʺ�ʧ�ܣ�1��ʾ�ʺ��Ѿ���������
	String info; //����ԭ��ʱ�䣬��RET=-1ʱΪ����ԭ�򣻵�RET>=0ʱΪ�����ʺųɹ���ʱ��
	int kind; //
	
	@Override
	void ParsePktProto(PacketInfo packetInfo) {
		System.out.println("��������");
		this.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
		
		if (revPacket.getType() == ConstParam.TYPE_1) {
			System.out.println("�����˺�����");
			ref = Integer.parseInt(jsonAnalysis.getValue(data, "REF"));
			user = jsonAnalysis.getValue(data, "USER");
			muser = jsonAnalysis.getValue(data, "MUSER");
			allas = jsonAnalysis.getValue(data, "ALLAS");
			right = Integer.parseInt(jsonAnalysis.getValue(data, "RIGHT"));
			pass = jsonAnalysis.getValue(data, "PASS");
		}
		
		if (revPacket.getType() == ConstParam.TYPE_3) {
			ref = Integer.parseInt(jsonAnalysis.getValue(data, "REF"));
			user = jsonAnalysis.getValue(data, "USER");
			muser = jsonAnalysis.getValue(data, "MUSER");
			right = Integer.parseInt(jsonAnalysis.getValue(data, "RIGHT"));
			pass = jsonAnalysis.getValue(data, "PASS");

		}
		
		if (revPacket.getType() == ConstParam.TYPE_5) {
			user = jsonAnalysis.getValue(data, "USER");
			kind = Integer.parseInt(jsonAnalysis.getValue(data, "KIND"));
			puser = jsonAnalysis.getValue(data, "PUSER");
		}
		
		if(revPacket.getType() == ConstParam.TYPE_7) {
			user = jsonAnalysis.getValue(data, "USER");
			puser = jsonAnalysis.getValue(data, "PUSER");
			kind = Integer.parseInt(jsonAnalysis.getValue(data, "KIND"));
			right = Integer.parseInt(jsonAnalysis.getValue(data, "RIGHT"));
			pass = jsonAnalysis.getValue(data, "PASS");

		}
		
		if(revPacket.getType() == ConstParam.TYPE_9) {
			user = jsonAnalysis.getValue(data, "USER");
			kind = Integer.parseInt(jsonAnalysis.getValue(data, "KIND"));
			info = jsonAnalysis.getValue(data, "INFO");
		}
		
		if(revPacket.getType() == ConstParam.TYPE_11) {
			ref = Integer.parseInt(jsonAnalysis.getValue(data, "REF"));
			user = jsonAnalysis.getValue(data, "USER");
			muser = jsonAnalysis.getValue(data, "MUSER");
			puser = jsonAnalysis.getValue(data, "PUSER");
			kind = Integer.parseInt(jsonAnalysis.getValue(data, "KIND"));
			allas = jsonAnalysis.getValue(data, "ALLAS");
			pass = jsonAnalysis.getValue(data, "PASS");
			right = Integer.parseInt(jsonAnalysis.getValue(data, "RIGHT"));
		}

	}

	@Override
	void ExecProto() {
		

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
