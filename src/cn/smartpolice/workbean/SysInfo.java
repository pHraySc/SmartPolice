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
 * 系统信息
 * 使用synchronizedSet方法使HashSet具有同步的能力：Set s = Collections.synchronizedSet(new HashSet(...));
 * @author 刘超
 *
 */
public class SysInfo {

	private static SysInfo instance;
	private static UIUserInfo uIUserInfo; //界面用户登录信息
	private static Set<UserNode> userNodeQueue = Collections.synchronizedSet(new HashSet<UserNode>()); //dev队列
	//private static HashSet<AppNode> appNodeQueue = new HashSet<AppNode>(); //appuser队列
	private static ArrayList<FileNodeInfo> fileDataInfoQueue = new ArrayList<FileNodeInfo>(); //文件队列
	private static SysStatInfo sysStatInfo; //系统统计信息
	private static SysCfgInfo sysCfgInfo; //系统配置信息
	
	private static ArrayList<MsgTask> msgTaskQueue = new ArrayList<MsgTask>(); //消息任务队列
	
	
	//协议处理类数组	
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
	
	//将构造器私有
	private SysInfo(){}
	//得到SysInfo单列
	public static SysInfo getInstance(){
		instance = new SysInfo();
		return instance;
	}
	
	//根据id找到app信息节点
	public AppNode getAppNodeById(int id){
		for(UserNode userNode:userNodeQueue){
			//id相等并判断是否是appnode
			if(userNode.getId() == id && userNode instanceof AppNode){
				AppNode appNode =(AppNode) userNode; //向下类型转换
				return appNode;
			}
		}
		return null;
	}
	
	//根据id找到dev信息节点
	public DevNode getDevNodeById(int id){
		for(UserNode userNode:userNodeQueue){
			if(userNode.getId() == id && userNode instanceof DevNode){
				DevNode devNode =(DevNode) userNode;
				return devNode;
			}
		}
		return null;
	}
	
	//根据接受者id查找消息任务节点
	public MsgTask getMsgTaskByRecvUserId(int id){
		for(MsgTask msgTask:msgTaskQueue){
			if(msgTask.getRevUserID() == id ){
				return msgTask;
			}
		}
		return null;
	}
	
	//添加一个userNode节点到队列中
	public synchronized void addUserNode(UserNode userNode){
		
		this.userNodeQueue.add(userNode);
	}
	
	//删除userNode队列中的一个节点
	public synchronized void removeUserNode(UserNode userNode){
		
		this.userNodeQueue.remove(userNode);
	}

}
