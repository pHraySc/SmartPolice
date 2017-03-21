package cn.smartpolice.protocol;
/**
 * cmd=1
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.mina.core.buffer.IoBuffer;






import cn.smartpolice.netdao.DevLog;
import cn.smartpolice.netdao.DevLogDao;
import cn.smartpolice.netdao.DeviceDao;
import cn.smartpolice.netdao.DeviceInf;
import cn.smartpolice.netdao.ServerDao;
import cn.smartpolice.netdao.ServerInf;
import cn.smartpolice.netdao.UserDao;
import cn.smartpolice.netdao.UserInf;
import cn.smartpolice.netdao.UserLog;
import cn.smartpolice.netdao.UserLogDao;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.AppNode;
import cn.smartpolice.workbean.DevNode;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;
import sun.misc.BASE64Decoder;

public class ProtocolLogin extends ProtocolBase {

	//PacketInfo revPacket = null;
	String snum; // 软件编号
	String sver;
	String user; // 用户账号
	String code; //加密编码后的随机数
	int errorPktState; // 标记不同错误报文（一种是登陆请求信息不完整或错误(errorState=0)，一种是密码验证错误(errorState=1)）
	// String ret; // 返回代码
	int random; // 随机数


	public void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		super.revPacket = packetInfo;
		System.out.println("login报文解析");
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
		// 登录请求
		if (revPacket.getType() == ConstParam.TYPE_1) { 
			snum = jsonAnalysis.getValue(data, "SNUM");
			sver = jsonAnalysis.getValue(data, "SVER");
			user = jsonAnalysis.getValue(data, "USER");
		}
		// 登录验证     登出验证只有user
		if (revPacket.getType() == ConstParam.TYPE_3 || revPacket.getType() == ConstParam.TYPE_5) {
			code = jsonAnalysis.getValue(data, "CODE");
			user = jsonAnalysis.getValue(data, "USER");
		}
		this.ExecProto();
	}

	public void ExecProto() {
		// TODO Auto-generated method stub
		this.ShowPacket(revPacket);
		System.out.println("snum="+snum);
		System.out.println("sver="+sver);
		System.out.println("user="+user);
		System.out.println("code="+code);
		UserNode userNode = null;
		// dev设备处理
		if (revPacket.getSort() == ConstParam.SORT_2) {
			// 从节点队列中获取节点 当第一次登陆不成功时 以后登录会存在节点
			userNode = SysInfo.getInstance().getDevNodeById(revPacket.getSid());
			//System.out.println(userNode);
		}
		// App
		if (revPacket.getSort() == ConstParam.SORT_0) {
			userNode = SysInfo.getInstance().getAppNodeById(revPacket.getSid());
			//System.out.println(userNode);
		}
		System.out.println(userNode);
		// 登录请求报文处理
		if (revPacket.getType() == ConstParam.TYPE_1) {
			// System.out.println("state：" + devNode.getState());
			// 当第一次登陆不成功时 以后登录会存在节点,所以在这儿将state设为1，不然后面收到登录请求报文时会直接返回错误报文
			// 通过节点状态判断节点登录状态
			if (userNode.getState() == ConstParam.LOGIN_STATE_0
					|| userNode.getState() == ConstParam.LOGIN_STATE_1) {
				// 报文信息有错或报文中的用户名和数据库中的用户名不一致
				if (snum == null && sver == null
						&& !user.equals(userNode.getAccount())) { // 比较节点中account和报文中的user
					System.out.print("报文信息不正确");
					if (revPacket.getSort() == ConstParam.SORT_2) {
						DevNode devNode = (DevNode) userNode;
						SysInfo.getInstance().removeUserNode(devNode); // 删除队列中的节点
					}
					if (revPacket.getSort() == ConstParam.SORT_0) {
						AppNode appNode = (AppNode) userNode;
						SysInfo.getInstance().removeUserNode(appNode); // 删除队列中的节点
					}
					errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文
					byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1); // 构造需要的信息不完整或登录帐号和user不相同的出错报文
					SendPkt(errorPacket); // 返回一个出错报文
					return;
				} else {
					// 如果系统配置文件中配置了不需要验证，直接返回成功报文
					//dev
					if (SysInfo.getSysCfgInfo().getState() == ConstParam.CHECK_STATE_0 && revPacket.getSort() == ConstParam.SORT_2) {
						// 节点登录状态置为2 直接从0置为2
						userNode.setState(ConstParam.LOGIN_STATE_2);
						// 在用户登录信息表插入一条记录 构造一个userLog对象插入
						DevLog devLog = new DevLog();
						// 将Date类型的LoginDate转换为date类型
						// SimpleDateFormat sdf = new
						// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						Date logindate = new Date();
						devLog.setDeviceid(userNode.getId());
						devLog.setDate(logindate);
						devLog.setIp(userNode.getIp());
						devLog.setPort(userNode.getPort());
						devLog.setOperate(ConstParam.LOGIN_OPERATE_1);
						DevLogDao devLogDao = new DevLogDao();
						devLogDao.insertDevLogInf(devLog); // 在用户登录信息表插入
						byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // 构造登陆成功的报文
						SendPkt(successPacket);// 如果当前用户状态为2（已经登录成功）直接返回登录成功报文
						return;
					}
					//app 同上
					if(SysInfo.getSysCfgInfo().getState() == ConstParam.CHECK_STATE_0 && revPacket.getSort() == ConstParam.SORT_0){
						// 节点登录状态置为2 直接从0置为2
						userNode.setState(ConstParam.LOGIN_STATE_2);
						// 在用户登录信息表插入一条记录 构造一个userLog对象插入
						UserLog userLog = new UserLog();
						// 将Date类型的LoginDate转换为date类型
						// SimpleDateFormat sdf = new
						// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						Date logindate = new Date();
						userLog.setUserid(userNode.getId());
						userLog.setDate(logindate);
						userLog.setIp(userNode.getIp());
						userLog.setPort(userNode.getPort());
						userLog.setOperate(ConstParam.LOGIN_OPERATE_1);
						UserLogDao userLogDao = new UserLogDao();
						userLogDao.insertUserLogInf(userLog); // 在用户登录信息表插入
						byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // 构造登陆成功的报文
						SendPkt(successPacket);// 如果当前用户状态为2（已经登录成功）直接返回登录成功报文
						return;
					}
					// 如果系统配置文件中配置了需要验证，返回验证请求报文
					if (SysInfo.getSysCfgInfo().getState() == ConstParam.CHECK_STATE_1) {
						System.out.println("snum=" + snum + "   user=" + user);
						// 随机数置于登录时间域
						Random rando = new Random();
						random = rando.nextInt(99999) % 90000 + 10000; // 产生一个随机数
						
						System.out.print("随机数放入logindate中");
						userNode.setLoginDate(random);
						userNode.setState(ConstParam.LOGIN_STATE_1); // 将用户状态置为1
						// 更新后的devNode要放入全局队列中
						if (revPacket.getSort() == ConstParam.SORT_2) {
							DevNode devNode = (DevNode) userNode;
							SysInfo.getInstance().addUserNode(devNode);
						}
						if (revPacket.getSort() == ConstParam.SORT_0) {
							AppNode appNode = (AppNode) userNode;
							SysInfo.getInstance().addUserNode(appNode);
						}
						byte[] checkPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2); // 构造让对方发送验证请求报文de报文
						SendPkt(checkPacket); // 对方发送验证请求报文
						return;
					}
				}
			}
			// 节点掉线时，节点发登陆请求时，队列中的节点状态state=2时直接返回登录成功
			if (userNode.getState() == ConstParam.LOGIN_STATE_2 && revPacket.getSort() == ConstParam.SORT_2) {
				// System.out.println("TYPE=1  code=" + code + "   user=" +
				// user);
				// 在用户登录信息表插入一条记录 构造一个userLog对象插入
				DevLog devLog = new DevLog();
				// 将Date类型的LoginDate转换为date类型
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date logindate = new Date();
				devLog.setDeviceid(userNode.getId());
				devLog.setDate(logindate);
				devLog.setIp(userNode.getIp());
				devLog.setPort(userNode.getPort());
				devLog.setOperate(ConstParam.LOGIN_OPERATE_1);
				DevLogDao devLogDao = new DevLogDao();
				devLogDao.insertDevLogInf(devLog); // 在用户登录信息表插入
				byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // 构造登陆成功的报文
				SendPkt(successPacket);// 如果当前用户状态为2（已经登录成功）直接返回登录成功报文
				return;
			}
			if(userNode.getState() == ConstParam.LOGIN_STATE_2 && revPacket.getSort() == ConstParam.SORT_0){
				// 在用户登录信息表插入一条记录 构造一个userLog对象插入
				UserLog userLog = new UserLog();
				// 将Date类型的LoginDate转换为date类型
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date logindate = new Date();
				userLog.setUserid(userNode.getId());
				userLog.setDate(logindate);
				userLog.setIp(userNode.getIp());
				userLog.setPort(userNode.getPort());
				userLog.setOperate(ConstParam.LOGIN_OPERATE_1);
				UserLogDao userLogDao = new UserLogDao();
				userLogDao.insertUserLogInf(userLog); // 在用户登录信息表插入
				byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // 构造登陆成功的报文
				SendPkt(successPacket);// 如果当前用户状态为2（已经登录成功）直接返回登录成功报文
				return;
			}
		}
		
		// 验证请求报文
		if (revPacket.getType() == ConstParam.TYPE_3) { 
			if (userNode.getState() == ConstParam.LOGIN_STATE_0
					|| userNode.getState() == ConstParam.LOGIN_STATE_2) { // 直接出错，返回出错报文
				// System.out.println("验证请求节点state不对");
				byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1); // 构造验证失败应答报文
				SendPkt(errorPacket);
				return;
			}
			if (userNode.getState() == ConstParam.LOGIN_STATE_1) {
				// System.out.println("TYPE=3  code=" + code + "   user=" +
				// user);
				//System.out.println("验证用户名");
				if (user.equals(userNode.getAccount())) {
					//System.out.println("异或解密");
					try {
						BASE64Decoder decoder = new BASE64Decoder();
						String pass = new String(decoder.decodeBuffer(code)); // base64解码
						byte[] password;
						if(revPacket.getSort() == ConstParam.SORT_2){ //dev
							DeviceDao devDao = new DeviceDao();
							DeviceInf deviceInf = devDao.findDevByID(revPacket
									.getSid()); // 数据库查询
							// 数据库中的密码需要是数字
							password = deviceInf.getPassword().getBytes(); // 秘钥
						} else{ //app
							UserDao userDao = new UserDao();
							UserInf userInf =userDao.findAppuserByID(revPacket.getSid());
							password = userInf.getPassword().getBytes();
						}
						//将随机数加密
						byte[] passs = String.valueOf(userNode.getLoginDate()).getBytes(); 
						byte[] rondByte = passs;
						System.out.println("随机数解码后："+pass);
						//System.out.println("解码随机数："+pass.toCharArray());
						for (int i = 0; i < passs.length; i++) {
							
							rondByte[i] = (byte) (passs[i] ^ password[i]);
							if(rondByte[i] == 0){
								rondByte[i] = (byte) (passs[i] + 0 ^ password[i]);
							}
						}
						//System.out.println("异或解密结束");
						// System.out.println(passs);
						String ron = new String(rondByte);
						//System.out.println("异或正确");
						//System.out.println("解密后的随机数：" + passs);
						System.out.println("异或解密后的code:" + ron);
						
						if (ron.equals(pass)) { 
							long loginDate = System.currentTimeMillis() / 1000;
							userNode.setLoginDate(loginDate); // 置登录时间为当前时间
							userNode.setState(2); // 将用户状态置2
							//app
							if (revPacket.getSort() == ConstParam.SORT_0) {
								System.out.println(revPacket.getSort());
								AppNode appNode = (AppNode) userNode;
								// 将更新后的devnode更新到全局数据队列中
								SysInfo.getInstance().addUserNode(appNode);
								// 在用户登录信息表插入一条记录 构造一个userLog对象插入
								UserLog userLog = new UserLog();
								// 将Date类型的LoginDate转换为date类型
								// SimpleDateFormat sdf = new
								// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
								Date logindate = new Date();
								userLog.setUserid(userNode.getId());
								userLog.setDate(logindate);
								userLog.setIp(userNode.getIp());
								userLog.setPort(userNode.getPort());
								userLog.setOperate(ConstParam.LOGIN_OPERATE_1);
								UserLogDao userLogDao = new UserLogDao();
								userLogDao.insertUserLogInf(userLog); // 在用户登录信息表插入
								byte[] checkSuccessPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // 构造验证成功应答报文
								SendPkt(checkSuccessPacket);
								return;
							}
							//dev
							if (revPacket.getSort() == ConstParam.SORT_2) {
								DevNode devNode = (DevNode) userNode;
								SysInfo.getInstance().addUserNode(devNode);
								// 在用户登录信息表插入一条记录 构造一个userLog对象插入
								DevLog devLog = new DevLog();
								// 将Date类型的LoginDate转换为date类型
								// SimpleDateFormat sdf = new
								// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
								Date logindate = new Date();
								devLog.setDeviceid(userNode.getId());
								devLog.setDate(logindate);
								devLog.setIp(userNode.getIp());
								devLog.setPort(userNode.getPort());
								devLog.setOperate(ConstParam.LOGIN_OPERATE_1);
								DevLogDao devLogDao = new DevLogDao();
								devLogDao.insertDevLogInf(devLog); // 在用户登录信息表插入
								byte[] checkSuccessPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // 构造验证成功应答报文
								SendPkt(checkSuccessPacket);
								return;
							}
							
						} else {
							// 如果验证失败 删除节点
							if (revPacket.getSort() == ConstParam.SORT_2) {
								DevNode devNode = (DevNode) userNode;
								SysInfo.getInstance().removeUserNode(devNode); // 删除队列中的节点
							}
							if (revPacket.getSort() == ConstParam.SORT_0) {
								AppNode appNode = (AppNode) userNode;
								SysInfo.getInstance().removeUserNode(appNode);
							}
							errorPktState = ConstParam.ERROR_PKT_STATE_1; //发送验证失败的报文
							byte[] checkFailPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1); // 构造验证失败应答报文
							SendPkt(checkFailPacket);
							return;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		//登出报文
		if(revPacket.getType() == ConstParam.TYPE_5){
			if(user.equals(userNode.getAccount())){
				
				if(revPacket.getSort() == ConstParam.SORT_2){
					DevNode devNode = (DevNode) userNode;
					SysInfo.getInstance().removeUserNode(devNode);
					DevLog devLog = new DevLog();
					devLog.setDeviceid(userNode.getId());
					devLog.setDate(new Date());
					devLog.setIp(userNode.getIp());
					devLog.setPort(userNode.getPort());
					devLog.setOperate(ConstParam.LOGIN_OPERATE_0);
					DevLogDao devLogDao = new DevLogDao();
					devLogDao.insertDevLogInf(devLog);
				}
				if(revPacket.getSort() == ConstParam.SORT_0){
					AppNode appNode = (AppNode) userNode;
					SysInfo.getInstance().removeUserNode(appNode);
					UserLog userLog = new UserLog();
					// 将Date类型的LoginDate转换为date类型
					// SimpleDateFormat sdf = new
					// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date logindate = new Date();
					userLog.setUserid(userNode.getId());
					userLog.setDate(logindate);
					userLog.setIp(userNode.getIp());
					userLog.setPort(userNode.getPort());
					userLog.setOperate(ConstParam.LOGIN_OPERATE_0);
					UserLogDao userLogDao = new UserLogDao();
					userLogDao.insertUserLogInf(userLog); // 在用户登录信息表插入
				}
				System.out.println("信息正确  删除节点");
			}else{
				System.out.println("信息不正确  未处理");
			}
		}	
		
		//保活报文
		if(revPacket.getType() == ConstParam.TYPE_6) {
			if(userNode != null){
				// 更新接收到报文的时间
				userNode.setRevPktDate(new Date());
				if(revPacket.getType() == ConstParam.SORT_2){ // dev
					DevNode devNode = (DevNode) userNode;
					SysInfo.getInstance().addUserNode(devNode);
				}
				if(revPacket.getType() == ConstParam.SORT_0){ //app
					AppNode appNode = (AppNode) userNode;
					SysInfo.getInstance().addUserNode(appNode);
				}
				byte[] keepAlivePkt = PackPkt(ConstParam.SENT_PKT_TYPE_4);
				System.out.print(keepAlivePkt.toString());
				SendPkt(keepAlivePkt);
				return;
			}
//			else{
//				System.out.print("节点离线，保活应答报文不返回");
//			}
			
		}
	}


	public byte[] PackPkt(int i) {
		// TODO Auto-generated method stub

		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		// 从数据库中获取

		ServerDao serverDao = new ServerDao();
		ServerInf server = serverDao.findServer(1);

		System.out.println(server);
		// int sid = server.getServerId();
		int sendseq;
		int ackseq;
		// 当报文来自appuser或者dev是的不同处理
		if (revPacket.getSort() == ConstParam.SORT_0) {
			sendseq = revPacket.getAppNode().getSntPktId(); // 获取发送报文id
			ackseq = revPacket.getSeq(); // ACK的值是收到请求报文的序号，具有相关性
		} else {
			sendseq = revPacket.getDevNode().getSntPktId(); // 获取发送报文id
			ackseq = revPacket.getSeq();
		}
		String packetBody = null; // 报文数据域

		byteChar[0] = ConstParam.CMD_1; // cmd =1
		if (revPacket.getType() == ConstParam.TYPE_1) {
			byteChar[1] = ConstParam.TYPE_2; // type=2
		}
		if (revPacket.getType() == ConstParam.TYPE_3) {
			byteChar[1] = ConstParam.TYPE_4; // type=4
		}
		if(revPacket.getType() == ConstParam.TYPE_6){
			byteChar[1] = ConstParam.TYPE_7;
		}
		byteChar[2] = ConstParam.OPT_16; // opt=16 表示此报文是应答报文，确认号有效；
		byteChar[3] = ConstParam.SORT_3; // sort=3 SORT表示发送方类别 3代表web服务器类

		System.out.println(i);
		switch (i) {
		case 1: // 需要的信息不完整或登录帐号和user不相同时 返回的出错报文，密码验证失败时 返回错误报文
			System.out.println("case1");

			if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
				packetBody = "{'DATA':{'RET':'-1','INFO':'需要的信息不完整或登录帐号和user不相同'}}"; // 报文内容
			}
			if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
				packetBody = "{'DATA':{'RET':'-1','INFO':'状态错误或密码不正确'}}"; // 报文内容
			}
			break;
		case 2: // 构造让对方发送验证请求报文de报文
			System.out.println("case2");
			packetBody = "{'DATA':{'RET':'1','INFO':'" + random + "'}}"; // 报文内容
			break;
		case 3: // 通过验证后返回的登录成功报文 ， 请求登录时节点状态state=2时或系统文件配置不需要验证时返回的登录成功报文
			System.out.println("case3");
			packetBody = "{'DATA':{'RET':'0','INFO':'" + revPacket.getSid()
					+ "'}}"; // 报文内容
			break;
		case 4:
			System.out.println("case4保活应答报文没内容");
			
			break;
		}
		
		ByteArrayProc byteArrayProc = new ByteArrayProc();
		packet.add(byte1);
		packet.add(byteChar);
		packet.add(byteArrayProc.int2bytes(server.getServerId()));
		packet.add(byteArrayProc.int2bytes(++sendseq));
		packet.add(byteArrayProc.int2bytes(ackseq));
		if(revPacket.getType()!=6){
			String packetBodyJson = new JsonAnalysis().getJsonByObject(packetBody);
			byte[] packetBodyJsonByte = packetBodyJson.getBytes();
			packet.add(packetBodyJsonByte);
		}
		byte[] packets = byteArrayProc.sysCopy(packet); // 将多个byte[]拼接
		return packets;
	}


	public void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub
		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));// 发送报文
	}

	//显示报文信息
	public void ShowPacket(PacketInfo packetInfo){
		System.out.print("cmd="+packetInfo.getCmd());
		System.out.print("type="+packetInfo.getType());
		System.out.print("opt="+packetInfo.getOpt());
		System.out.print("sort="+packetInfo.getSort());
		System.out.print("sid="+packetInfo.getSid());
		System.out.print("seq="+packetInfo.getSeq());
		System.out.println("ack="+packetInfo.getAck());
	}
	

}
