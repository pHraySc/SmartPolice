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
 * cmd = 3(服务器端就只是转发协议)
 * @author 刘超
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
		//sort=0报文从app来 只能是转发给dev
		if(revPacket.getSort() == ConstParam.SORT_0){
			userNode = SysInfo.getInstance().getDevNodeById(revPacket.getDid());
			System.out.println("userNOde should be dev:"+userNode);
		}
		//sort=2报文从dev来 转发给app
		if(revPacket.getSort() == ConstParam.SORT_2){
			userNode = SysInfo.getInstance().getAppNodeById(revPacket.getDid());
		}
		
		//节点存在且处于登录状态
		if(userNode != null && userNode.getState() == ConstParam.LOGIN_STATE_2){
			//更新节点收到报文的时间
			userNode.setRevPktDate(new Date());
			System.out.println("userNode :"+userNode);
			//将报文转发给该节点
			userNode.getIoSession().write(IoBuffer.wrap(revPacket.getMessage()));
			//更新相应的节点
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
