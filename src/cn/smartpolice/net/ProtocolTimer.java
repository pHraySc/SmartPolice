package cn.smartpolice.net;

import java.util.Date;
import java.util.HashSet;

import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

/**
 * ��ʱ���߳�ProtocolTimer
 * @author ����
 *
 */
public class ProtocolTimer implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("��ʱ������");
		SysInfo sysInfo = SysInfo.getInstance();
		Date CurrentTime = new Date();
		//���dev�������Ƿ��нڵ㳬ʱ
		for(UserNode userNode : sysInfo.getUserNodeQueue()){
			//������ձ���ʱ��͵�ǰʱ���3����
			long interval = (CurrentTime.getTime()-userNode.getRevPktDate().getTime())/1000;
			if(interval>60){ 
				sysInfo.getInstance().removeUserNode(userNode);; //ɾ���ڵ�
			}
			System.out.println("*********��ʱ���߳�UserNode*********");
			System.out.println("UserNode="+userNode);
			System.out.println("*********��ʱ���߳�devNode*********");
		}
		
	}

}
