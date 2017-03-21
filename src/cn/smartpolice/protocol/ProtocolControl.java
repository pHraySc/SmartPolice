package cn.smartpolice.protocol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.netdao.ServerDao;
import cn.smartpolice.netdao.ServerInf;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.AppNode;
import cn.smartpolice.workbean.DevNode;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

/**
 * cmd = 3(�������˾�ֻ��ת��Э��)
 * @author ����
 *
 */
public class ProtocolControl extends ProtocolBase{

	@Override
	void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		super.revPacket = packetInfo;
		this.ExecProto();
	}

	@Override
	void ExecProto() {
		// TODO Auto-generated method stub
		UserNode userNode = null;
		//sort=0���Ĵ�app�� ֻ����ת����dev
		if(revPacket.getSort() == ConstParam.SORT_0){
			userNode = SysInfo.getInstance().getDevNodeById(revPacket.getDid());
			System.out.println("userNOde should be dev:"+userNode);
		}
		//sort=2���Ĵ�dev�� ת����app
		if(revPacket.getSort() == ConstParam.SORT_2){
			userNode = SysInfo.getInstance().getAppNodeById(revPacket.getDid());
		}
		
		//�ڵ�����Ҵ��ڵ�¼״̬
		if(userNode != null && userNode.getState() == ConstParam.LOGIN_STATE_2){
			//���½ڵ��յ����ĵ�ʱ��
			userNode.setRevPktDate(new Date());
			System.out.println("userNode :"+userNode);
			//������ת�����ýڵ�
			userNode.getIoSession().write(IoBuffer.wrap(revPacket.getMessage()));
			//������Ӧ�Ľڵ�
			if(revPacket.getSort() == ConstParam.SORT_0){
				DevNode devNode = (DevNode) userNode;
				SysInfo.getInstance().addUserNode(devNode);
			}
			if(revPacket.getSort() == ConstParam.SORT_2){
				AppNode appNode = (AppNode) userNode;
				SysInfo.getInstance().addUserNode(appNode);
			}
		}
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
