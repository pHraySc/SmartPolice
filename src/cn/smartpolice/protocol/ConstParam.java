package cn.smartpolice.protocol;



/**
 * 宏定义文件
 * @author 刘超
 *
 */
public class ConstParam {
	//定义全局常量
	public static final int MASSAGE_LEN = 20; //报文最短长度
	
	public static final int MAX_CMD = 12; //最大cmd
	public static final int CMD_0 = 0; //连通测试报文
	public static final int CMD_1 = 1; //报文协议类型（连接管理协议）
	public static final int CMD_2 = 2; 
	public static final int CMD_3 = 3;
	public static final int CMD_4 = 4;
	public static final int CMD_5 = 5;//平台帐号管理协议
	public static final int CMD_7 = 7;//文件传输协议
	public static final int CMD_10 = 10;//联系人管理协议
	
	public static final int TYPE_0 = 0; //报文类型
	public static final int TYPE_1 = 1;
	public static final int TYPE_2 = 2;
	public static final int TYPE_3 = 3;
	public static final int TYPE_4 = 4;
	public static final int TYPE_5 = 5;
	public static final int TYPE_6 = 6;
	public static final int TYPE_7 = 7;
	public static final int TYPE_8 = 8;
	public static final int TYPE_9 = 9;
	public static final int TYPE_10 = 10;
	public static final int TYPE_11 = 11;
	
	public static final int SORT_0 = 0; //app user
	public static final int SORT_2 = 2; //dev user
	public static final int SORT_3 = 3; //服务器
	
	public static final int OPT_1 = 1;
	public static final int OPT_8 = 8;
	public static final int OPT_16 = 16;
	
	public static final int LOGIN_STATE_0 = 0; //登录状态  未验证只是在队列中存在此节点
	public static final int LOGIN_STATE_1 = 1; //已返回要求验证报文
	public static final int LOGIN_STATE_2 = 2; //登录成功
	
	public static final int CHECK_STATE_0 = 0; //系统配置是否需要密码验证     不需要
	public static final int CHECK_STATE_1 = 1; //需要密码验证
	
	public static final int SENT_PKT_TYPE_1 = 1; //构造不同的返回报文   返回错误报文
	public static final int SENT_PKT_TYPE_2 = 2; //构造让对方发送验证请求报文de报文
	public static final int SENT_PKT_TYPE_3 = 3; //构造登录成功的报文
	public static final int SENT_PKT_TYPE_4 = 4; //构造保活应答报文
	
	public static final int ERROR_PKT_STATE_0 = 0; // 报文信息不匹配
	public static final int ERROR_PKT_STATE_1 = 1; // 报文验证失败
	public static final int ERROR_PKT_STATE_2 = 2;
	
	public static final int LOGIN_OPERATE_0 = 0;  //log表中标记登出
	public static final int LOGIN_OPERATE_1 = 1;  //log表中标记登录
	public static final int LOGIN_OPERATE_2 = -1;  //log表中标记注销
	
	public static final int SERVER_ID = 1; //从数据库中获取的   先定义为常量
	
	public static final int CONTACT_0 = 0;//等待确认    脱联
	public static final int CONTACT_1 = 1;//帐号已经关联过    已脱联
	public static final int CONTACT_2 = -1;//添加联系人失败  脱联失败
	
	public static final int REQUEST_INFORM_0 = 0;//请求添加
	public static final int REQUEST_INFORM_1 = 1;//确认添加
	public static final int REQUEST_INFORM_2 = 2;//否认添加
	public static final int REQUEST_INFORM_3 = 3;//添加通知
	public static final int REQUEST_INFORM_4 = 4;//删除通知
}
