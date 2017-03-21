package cn.smartpolice.workbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cn.smartpolice.protocol.ProtocolAccount;
import cn.smartpolice.protocol.ProtocolBase;
import cn.smartpolice.protocol.ProtocolControl;
import cn.smartpolice.protocol.ProtocolLogin;
import cn.smartpolice.protocol.ProtocolTest;


/**
 * ϵͳ��Ϣ
 * ʹ��synchronizedSet����ʹHashSet����ͬ����������Set s = Collections.synchronizedSet(new HashSet(...));
 * @author ����
 *
 */
public class SysInfo {

	private static SysInfo instance;
	private static UIUserInfo uIUserInfo; //�����û���¼��Ϣ
	private static Set<UserNode> userNodeQueue = Collections.synchronizedSet(new HashSet<UserNode>()); //dev����
	//private static HashSet<AppNode> appNodeQueue = new HashSet<AppNode>(); //appuser����
	private static ArrayList<FileNodeInfo> fileDataInfoQueue = new ArrayList<FileNodeInfo>(); //�ļ�����
	private static SysStatInfo sysStatInfo; //ϵͳͳ����Ϣ
	private static SysCfgInfo sysCfgInfo; //ϵͳ������Ϣ
	
	private static ArrayList<MsgTask> msgTaskQueue = new ArrayList<MsgTask>(); //��Ϣ�������
	
	
	//Э�鴦��������	
	private static ProtocolBase[] prtocolBases = {new ProtocolTest(), new ProtocolLogin(), new ProtocolAccount(),
		new ProtocolControl()};

	public static ProtocolBase[] getPrtocolBases() {
		return prtocolBases;
	}

	public static SysCfgInfo getSysCfgInfo() {
		return sysCfgInfo;
	}
	
	public static ArrayList<FileNodeInfo> getFileDataInfoQueue() {
		return fileDataInfoQueue;
	}
	public static Set<UserNode> getUserNodeQueue() {
		return userNodeQueue;
	}
	public static ArrayList<MsgTask> getMsgTaskQueue() {
		return msgTaskQueue;
	}
	public static void setSysCfgInfo(SysCfgInfo sysCfgInfo) {
		SysInfo.sysCfgInfo = sysCfgInfo;
	}
	
	//��������˽��
	private SysInfo(){}
	//�õ�SysInfo����
	public static SysInfo getInstance(){
		instance = new SysInfo();
		return instance;
	}
	
	//����id�ҵ�app��Ϣ�ڵ�
	public AppNode getAppNodeById(int id){
		for(UserNode userNode:userNodeQueue){
			//id��Ȳ��ж��Ƿ���appnode
			if(userNode.getId() == id && userNode instanceof AppNode){
				AppNode appNode =(AppNode) userNode; //��������ת��
				return appNode;
			}
		}
		return null;
	}
	
	//����id�ҵ�dev��Ϣ�ڵ�
	public DevNode getDevNodeById(int id){
		for(UserNode userNode:userNodeQueue){
			if(userNode.getId() == id && userNode instanceof DevNode){
				DevNode devNode =(DevNode) userNode;
				return devNode;
			}
		}
		return null;
	}
	
	//���ݽ�����id������Ϣ����ڵ�
	public MsgTask getMsgTaskByRecvUserId(int id){
		for(MsgTask msgTask:msgTaskQueue){
			if(msgTask.getRevUserID() == id ){
				return msgTask;
			}
		}
		return null;
	}
	
	//���һ��userNode�ڵ㵽������
	public synchronized void addUserNode(UserNode userNode){
		
		this.userNodeQueue.add(userNode);
	}
	
	//ɾ��userNode�����е�һ���ڵ�
	public synchronized void removeUserNode(UserNode userNode){
		
		this.userNodeQueue.remove(userNode);
	}

}
