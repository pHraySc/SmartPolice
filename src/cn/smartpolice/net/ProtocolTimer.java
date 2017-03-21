package cn.smartpolice.net;

import java.util.Date;
import java.util.HashSet;

import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

/**
 * 定时器线程ProtocolTimer
 * @author 刘超
 *
 */
public class ProtocolTimer implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("定时器工作");
		SysInfo sysInfo = SysInfo.getInstance();
		Date CurrentTime = new Date();
		//检查dev队列中是否有节点超时
		for(UserNode userNode : sysInfo.getUserNodeQueue()){
			//最近接收报文时间和当前时间差3分钟
			long interval = (CurrentTime.getTime()-userNode.getRevPktDate().getTime())/1000;
			if(interval>60){ 
				sysInfo.getInstance().removeUserNode(userNode);; //删除节点
			}
			System.out.println("*********定时器线程UserNode*********");
			System.out.println("UserNode="+userNode);
			System.out.println("*********定时器线程devNode*********");
		}
		
	}

}
