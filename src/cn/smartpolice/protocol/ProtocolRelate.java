package cn.smartpolice.protocol;

import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.PacketInfo;

public class ProtocolRelate extends ProtocolBase {

	int ref; //1表示管理员分享，0表示自我申请
	String user;
	String muser; //管理关联人账号
	String puser; //前端设备账号
	String allas; //设备别名
	int right; //权限代码：0管理权限，1操控权限，2查看权限，3接收权限
	String pass; //登录前端设备密码，为0或者没有这个选项表示只登记到平台的接收权限
	byte ret; //返回代码，0表示等待确认；-1表示关联帐号失败；1表示帐号已经关联过。
	String info; //错误原因、时间，当RET=-1时为错误原因；当RET>=0时为关联帐号成功的时间
	int kind; //
	
	@Override
	void ParsePktProto(PacketInfo packetInfo) {
		System.out.println("关联管理");
		this.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
		
		if (revPacket.getType() == ConstParam.TYPE_1) {
			System.out.println("关联账号请求");
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
