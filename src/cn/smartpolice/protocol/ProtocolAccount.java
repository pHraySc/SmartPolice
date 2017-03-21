package cn.smartpolice.protocol;
/**
 * cmd=2
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.mina.core.buffer.IoBuffer;
import sun.misc.BASE64Decoder;
import cn.smartpolice.netdao.DevLog;
import cn.smartpolice.netdao.DevLogDao;
import cn.smartpolice.netdao.DeviceDao;
import cn.smartpolice.netdao.DeviceInf;
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
public class ProtocolAccount extends ProtocolBase {

	String snum;
	String sver;
	String user;
	String password;
	String info;
	int errorPktState;// 标记返回错误报文类型

	int sid; // 查询时需要返回的sid
	// dev的info需要的信息
	String devSerial;
	String devCode;
	String devLongitude;
	String devLatitude;
	int devMaxConLimit;
	String devType;
	String devMphone;
	Boolean devInfComplete = false; // 标记dev报文中Info信息完整
	// app的info需要的信息
	String appAuthority;
	String appName;
	Date appBirth;
	Boolean appSex;
	String appMail;
	String appType;
	String appMphone;
	Boolean appInfComplete = false; // 标记appInfo信息完整

	//注销成功返回日期
	Date offDate;
	Date date2;
	Date date1;
	
	void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		System.out.print("Account报文解析");
		super.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");

		snum = jsonAnalysis.getValue(data, "SNUM");
		sver = jsonAnalysis.getValue(data, "SVER");
		user = jsonAnalysis.getValue(data, "USER");
		password = jsonAnalysis.getValue(data, "PASSWORD");
		info = jsonAnalysis.getValue(data, "INFO");
		// dev注册信息解析
		if (revPacket.getSort() == ConstParam.SORT_2 && info != null) {
			String[] devInfo = info.split(",");
			if (revPacket.getType() == 3) {
				if (devInfo[0] == "") {
				devSerial = null;
			} else {
				devSerial = devInfo[0];
			}
			if (devInfo[1] == "") {
				devCode = null;
			} else {
				devCode = devInfo[1];
			}
			if (devInfo[2] == "") {
				devLongitude = null;
			} else {
				devLongitude = devInfo[2];
			}
			if (devInfo[3] == "") {
				devLatitude = null;
			} else {
				devLatitude = devInfo[3];
			}
			if (devInfo[4] == "") {
				devMaxConLimit = 0;
			} else {
				devMaxConLimit = Integer.parseInt(devInfo[4]);
			}
			if (devInfo[5] == "") {
				devType = null;
			} else {
				devType = devInfo[5];
			}
			if (devInfo[6] == "") {
				devMphone = null;
			} else {
				devMphone = devInfo[6];
			}
			
			if (devInfo.length == 7) // 信息完整
				devInfComplete = true;
			// System.out.println("info!=null");
		
		// app注册信息解析
		if (revPacket.getSort() == ConstParam.SORT_0 && info != null) {
			String[] appInfo = info.split(",");
			if (appInfo[0] == "") {
				appAuthority = null;
			} else {
				appAuthority = appInfo[0];
			}
			if (appInfo[1] == "") {
				appName = null;
			} else {
				appName = appInfo[1];
			}
			if (appInfo[2] == "") {
				appBirth = null;
			} else {
				try {
					appBirth = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(appInfo[2]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// String birthday = birth+date;
			// try {
			// appBirth = new
			// SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(birthday);
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			if (Integer.parseInt(appInfo[3]) != 0) {
				appSex = true;
			} else {
				appSex = false;
			}
			if (appInfo[4] == "") {
				appMail = null;
			} else {
				appMail = appInfo[4];
			}
			if (appInfo[5] == "") {
				appType = null;
			} else {
				appType = appInfo[5];
			}
			if (appInfo[6] == "") {
				appMphone = null;
			} else {
				appMphone = appInfo[6];
			}
			if (appInfo.length == 7)
				appInfComplete = true;
		}
		}else {

			devSerial = devInfo[0];
			devCode = devInfo[1];
			devLongitude = devInfo[2];
			devLatitude = devInfo[3];
			devMaxConLimit = Integer.parseInt(devInfo[4]);
			devType = devInfo[5];
			devMphone = devInfo[6];
			if (devInfo.length == 7) // 信息完整
				devInfComplete = true;
			// System.out.println("info!=null");
		}
		// app注册信息解析
		if (revPacket.getSort() == ConstParam.SORT_0 && info != null) {
			String[] appInfo = info.split(",");
			appAuthority = appInfo[0];
			appName = appInfo[1];
			try {
					appBirth = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(appInfo[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// String birthday = birth+date;
			// try {
			// appBirth = new
			// SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(birthday);
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			if (Integer.parseInt(appInfo[3]) != 0) {
				appSex = true;
			} else {
				appSex = false;
			}
			appMail = appInfo[4];
			appType = appInfo[5];
			appMphone = appInfo[6];
			if (appInfo.length == 7)
				appInfComplete = true;
		}
		}
		this.ExecProto();
	}

	public void ExecProto() {
		// TODO Auto-generated method stub

		ShowPacket(revPacket);
		System.out.println("info=" + info);
		// 注册协议
		if (revPacket.getType() == ConstParam.TYPE_1) {
			// info==null 获得以前注册用户的id
			if (info == null) {
				// dev
				if (revPacket.getSort() == ConstParam.SORT_2) {
					// 通过user查询数据库是否存在该用户
					DeviceDao deviceDao = new DeviceDao();
					DeviceInf deviceInf = deviceDao.findDevByName(user);

					// 不存在该username
					if (deviceInf == null) {
						errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文（数据库中不存在该记录）
						byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
						SendPkt(errorPacket);
					} else {
						sid = deviceInf.getDeviceid();
						if (password.equals(deviceInf.getPassword())) {
							errorPktState = ConstParam.ERROR_PKT_STATE_1; // 存在该记录
																			// 但是密码不正确
							byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
							SendPkt(errorPacket);
						} else {
							byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2);
							SendPkt(successPacket);
						}
					}
				}
				// app
				if (revPacket.getSort() == ConstParam.SORT_0) {
					// 通过user查询数据库是否存在该用户
					UserDao userDao = new UserDao();
					UserInf userInf = userDao.findAppuserByName(user);
					// 不存在该username
					if (userInf == null) {
						errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文（数据库中不存在该记录）
						byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
						SendPkt(errorPacket);
					} else {
						sid = userInf.getUserID();
						if (password.equals(userInf.getPassword())) {
							errorPktState = ConstParam.ERROR_PKT_STATE_1; // 存在该记录
																			// 但是密码不正确
							byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
							SendPkt(errorPacket);
						} else {
							byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2);
							SendPkt(successPacket);
						}
					}
				}
			} else {
				// info信息完整
				if (revPacket.getSort() == 2) {
					if (devInfComplete) { // dev信息完整
						DeviceInf dev = new DeviceInf();
						dev.setSerial(devSerial);
						dev.setCode(devCode);
						dev.setUsername(user);
						dev.setPassword(password);
						dev.setState("1");
						dev.setLongitude(devLongitude);
						dev.setLatitude(devLatitude);
						dev.setDate(new Date());
						dev.setType(devType);
						dev.setMaxconlimit(devMaxConLimit);
						dev.setMphone(devMphone);
						new DeviceDao().insertDevInf(dev);
						sid = new DeviceDao().findDevByName(user).getDeviceid(); // 这里需要新建dao去做查询
																					// 如果在插入数据的session中去查询会报错transnation.commit()的问题
						System.out.println(sid);
						byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2);
						SendPkt(successPacket);
					} else { // 信息不完整
						errorPktState = ConstParam.ERROR_PKT_STATE_2;
						byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
						SendPkt(errorPacket);
					}
				}
				if (revPacket.getSort() == 0) {
					if (appInfComplete) { // app信息完整
						UserInf app = new UserInf();
						app.setUserName(user);
						app.setPassword(password);
						app.setSet("1");
						app.setRegDate(new Date());
						app.setAuthority(appAuthority);
						app.setState("1");
						app.setName(appName);
						app.setBirth(appBirth);
						app.setSex(appSex);
						app.setType(appType);
						app.setMail(appMail);
						app.setMphone(appMphone);
						new UserDao().insertAppuser(app);
						sid = new UserDao().findAppuserByName(user).getUserID();
					} else { // 信息不完整
						errorPktState = ConstParam.ERROR_PKT_STATE_2;
						byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
						SendPkt(errorPacket);
					}
				}
			}
		}
		//注销协议
		if (revPacket.getType() == ConstParam.TYPE_5) {

			try {
				//解码得到info中的phone
				BASE64Decoder decoder = new BASE64Decoder();
				String mphone = new String(decoder.decodeBuffer(info));
				byte[] phoneBytes = mphone.getBytes();
				
				// dev
				if (revPacket.getSort() == ConstParam.SORT_2) {
					DeviceInf dev = new DeviceDao().findDevByID(revPacket
							.getSid());
					//存在dev且state！=0
					if (dev != null && Integer.parseInt(dev.getState())!=0) {
						String password = dev.getPassword();
						String phone = dev.getMphone();
						byte[] passwordBytes = password.getBytes();
						for (int i = 0; i < phoneBytes.length; i++) {
							phoneBytes[i] = (byte) (phoneBytes[i]^passwordBytes[i]);
						}
						String phonenum = new String(phoneBytes);
						System.out.println("phonenum="+phonenum);
						if(phonenum.equals(phone)){
							//记录到devlog表中
							DevLog devLog = new DevLog();
							devLog.setDeviceid(revPacket.getSid());
							devLog.setDate(new Date());
							String ip = ((InetSocketAddress) revPacket.getIoSession()
									.getRemoteAddress()).getAddress().getHostAddress();
							int port = ((InetSocketAddress) revPacket.getIoSession()
									.getRemoteAddress()).getPort();
							devLog.setIp(ip);
							devLog.setPort(port);
							devLog.setOperate(ConstParam.LOGIN_OPERATE_2);
							new DevLogDao().insertDevLogInf(devLog);
							//置device_inf表中state=0
							new DeviceDao().changeStateToZero(revPacket.getSid()); 
							//删除全局数据中的节点
							DevNode devNode = (DevNode) SysInfo.getInstance().getDevNodeById(revPacket.getSid());
							SysInfo.getInstance().removeUserNode(devNode);
							//获取注销成功日期
							offDate = new Date();
							//返回注销成功报文
							byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3);
							SendPkt(successPacket);
						}else{ //电话不对 验证失败
							byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
							SendPkt(errorPacket);
						}
					}else{
						//dev == null不处理  state=0返回已注销
						if(Integer.parseInt(dev.getState())==0){
							//通过查找operate=-1和deviceid 两个条件一起查找
							offDate = new DevLogDao().findDevLogByDevIdAndOperate(revPacket.getSid()).getDate();
							byte[] packet = PackPkt(ConstParam.SENT_PKT_TYPE_2);
							SendPkt(packet);
						}
					}
				}
				//app
				if(revPacket.getSort() == ConstParam.SORT_0){
					UserInf app = new UserDao().findAppuserByID(revPacket
							.getSid());
					//存在dev且state！=0
					if (app != null && Integer.parseInt(app.getState())!=0) {
						String password = app.getPassword();
						String phone = app.getMphone();
						byte[] passwordBytes = password.getBytes();
						for (int i = 0; i < phoneBytes.length; i++) {
							phoneBytes[i] = (byte) (phoneBytes[i]^passwordBytes[i]);
						}
						String phonenum = new String(phoneBytes);
						System.out.println("phonenum="+phonenum);
						if(phonenum.equals(phone)){
							//记录到userlog表中
							UserLog userLog = new UserLog();
							userLog.setUserid(revPacket.getSid());
							userLog.setDate(new Date());
							String ip = ((InetSocketAddress) revPacket.getIoSession()
									.getRemoteAddress()).getAddress().getHostAddress();
							int port = ((InetSocketAddress) revPacket.getIoSession()
									.getRemoteAddress()).getPort();
							userLog.setIp(ip);
							userLog.setPort(port);
							userLog.setOperate(ConstParam.LOGIN_OPERATE_2);
							new UserLogDao().insertUserLogInf(userLog);
							//置device_inf表中state=0
							new UserDao().changeStateToZero(revPacket.getSid()); 
							//删除全局数据中的节点
							AppNode appNode = (AppNode) SysInfo.getInstance().getAppNodeById(revPacket.getSid());
							SysInfo.getInstance().removeUserNode(appNode);
							//获取注销成功日期
							offDate = new Date();
							//返回注销成功报文
							byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3);
							SendPkt(successPacket);
						}else{ //电话不对 验证失败
							byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
							SendPkt(errorPacket);
						}
					}else{
						//app == null不处理  state=0返回已注销
						if(Integer.parseInt(app.getState())==0){
							//通过查找operate=-1和deviceid 两个条件一起查找
							offDate = new UserLogDao().findDevLogByAppIdAndOperate(revPacket.getSid()).getDate();
							byte[] packet = PackPkt(ConstParam.SENT_PKT_TYPE_2);
							SendPkt(packet);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (revPacket.getType() == ConstParam.TYPE_3) {
			System.out.println("修改账号请求报文");
			// dev
			if (revPacket.getSort() == ConstParam.SORT_2) {
				// 通过ID查询数据库是否存在该用户
				DeviceDao deviceDao = new DeviceDao();
				DeviceInf deviceInf = deviceDao.findDevByID(revPacket.getSid());
				// 不存在该账号
				if (deviceInf == null) {
					date1 = new Date();
					errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文（数据库中不存在该记录）
					byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
					SendPkt(errorPacket);
				} else {

					if (info != null) {/*
										 * 在info不为空的情况下，将数据库中的数据与报文中的对比，如果不相同，
										 * 说明已更改，更改进从数据库中取出的对象，最后将对象更新到数据库中
										 */
						if (devSerial != null) {
							deviceInf.setSerial(devSerial);
						}
						if (devCode != null) {
							deviceInf.setCode(devCode);
						}
						if (devLongitude != null) {
							deviceInf.setLongitude(devLongitude);
						}
						if (devLatitude != null) {
							deviceInf.setLatitude(devLatitude);
						}
						if (devType != null) {
							deviceInf.setType(devType);
						}
						if (devMaxConLimit != 0 && deviceInf.getMaxconlimit() != devMaxConLimit) {
							deviceInf.setMaxconlimit(devMaxConLimit);
						}
						if (devMphone != null) {
							deviceInf.setMphone(devMphone);
						}
					}
					if (password != null) {
						deviceInf.setPassword(password);
						// deviceInf.setState("1");//密码已更改，需要密码验证
					}
					deviceDao.updateDevInf(deviceInf);
					sid = new DeviceDao().findDevByName(user).getDeviceid();
					byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3);// 通过变更
					SendPkt(successPacket);
				}
				DeviceDao Dao = new DeviceDao();
				DeviceInf Inf = Dao.findDevByID(revPacket.getSid());
				if (Inf.getUsername().equals(user)) {
					byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2);// 变更成功
					SendPkt(successPacket);
				} else {
					date2 = new Date();
					errorPktState = ConstParam.ERROR_PKT_STATE_1; // 写入数据库失败
					byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
					SendPkt(errorPacket);
				}

			}
			// app
			if (revPacket.getSort() == ConstParam.SORT_0) {
				// 通过user查询数据库是否存在该用户
				UserDao userDao = new UserDao();
				UserInf userInf = userDao.findAppuserByID(revPacket.getSid());
				// 不存在该用户
				if (userInf == null) {
					date1 = new Date();
					errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文（数据库中不存在该记录）
					byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
					SendPkt(errorPacket);
				} else {
					if (info != null) {
						if (appAuthority != null) {
							userInf.setAuthority(appAuthority);
						}
						if (appBirth != null) {
							userInf.setBirth(appBirth);
						}
						if (appMail != null) {
							userInf.setMail(appMail);
						}
						if (appMphone != null) {
							userInf.setMphone(appMphone);
						}
						if (appName != null) {
							userInf.setName(appName);
						}
						if (appType != null) {
							userInf.setType(appType);
						}
						if (appSex != null) {
							userInf.setSex(appSex);
						}
					}
					if (password != null) {
						userInf.setPassword(password);
					}
					userDao.updateAppuser(userInf);
					sid = new UserDao().findAppuserByName(user).getUserID();
					byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3);// 通过变更
					SendPkt(successPacket);
				}
				UserDao Dao = new UserDao();// 新建DAO做查询，判断信息是否更新到数据库成功，成功返回1
				UserInf Inf = Dao.findAppuserByID(revPacket.getSid());
				if (Inf.getUserName().equals(user)) {
					byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2);// 变更成功
					SendPkt(successPacket);
				} else {
					date2 = new Date();
					errorPktState = ConstParam.ERROR_PKT_STATE_1; // 写入数据库失败
					byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
					SendPkt(errorPacket);
				}

			}

		}


	}

	public byte[] PackPkt(int i) {
		// TODO Auto-generated method stub
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		byteChar[0] = ConstParam.CMD_2; // cmd=2
		// 不同的请求报文 返回的应答应答不一样
		//注册请求
		if (revPacket.getType() == ConstParam.TYPE_1) {
			byteChar[1] = ConstParam.TYPE_2; // type=2
		}
		//注销请求
		if(revPacket.getType() == ConstParam.TYPE_5){
			byteChar[1] = ConstParam.TYPE_6;
		}
		// 账号修改
		if (revPacket.getType() == ConstParam.TYPE_3) {
					byteChar[1] = ConstParam.TYPE_4;
		}

		byteChar[2] = ConstParam.OPT_16; // opt=16 应答报文
		byteChar[3] = ConstParam.SORT_3; // sort=3
		int sendseq = revPacket.getSeq(); // ??
		int ackseq = revPacket.getSeq(); // ??
		String packetBody = null;
		switch (i) {
		case 1: // ret=-1
			if(revPacket.getType() == ConstParam.TYPE_1){
				if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'not exist in database'}}"; // 报文内容
				}
				if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'password is wrong'}}"; // 报文内容
				}
				if (errorPktState == ConstParam.ERROR_PKT_STATE_2) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'INFO is not complete'}}"; // 报文内容
				}
			}
			if(revPacket.getType() == ConstParam.TYPE_5){
				packetBody = "{'DATA':{'RET':'-1','INFO':'log off confirmate fail'}}";
			}
			if (revPacket.getType() == ConstParam.TYPE_3) {
				if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'not exist in database,'" + date1 + "}}";
				}
				if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'Database update failed !" + date2 + "'}}";
				}
			}
			break;

		case 2: // ret=1
			if(revPacket.getType() == ConstParam.TYPE_1){
				packetBody = "{'DATA':{'RET':'1','INFO':'" + sid + "'}}"; // 报文内容
			}
			if(revPacket.getType() == ConstParam.TYPE_5){
				packetBody = "{'DATA':{'RET':'1','INFO':'" + offDate + "'}}"; // 报文内容
			}
			if (revPacket.getType() == ConstParam.TYPE_3) {
				packetBody = "{'DATA':{'RET':'1','INFO':'" + sid + "'}}"; // 变更成功
			}
			break;

		case 3: // ret=0
			if(revPacket.getType() == ConstParam.TYPE_1){   //注册成功
				packetBody = "{'DATA':{'RET':'0','INFO':'" + sid + "'}}"; // ??该sid是请求报文发送过来的      设备从哪来的
			}
			if(revPacket.getType() == ConstParam.TYPE_5){   //注销成功
				packetBody = "{'DATA':{'RET':'0','INFO':'" + offDate+ "'}}";
			}
			if (revPacket.getType() == ConstParam.TYPE_3) { // 通过变更
				packetBody = "{'DATA':{'RET':'0','INFO':'" + sid + "'}}";
			}
			break;

		}
		ByteArrayProc byteArrayProc = new ByteArrayProc();
		packet.add(byte1);
		packet.add(byteChar);
		packet.add(byteArrayProc.int2bytes(ConstParam.SERVER_ID));
		packet.add(byteArrayProc.int2bytes(++sendseq));
		packet.add(byteArrayProc.int2bytes(ackseq));
		String packetBodyJson = new JsonAnalysis().getJsonByObject(packetBody);
		byte[] packetBodyJsonByte = packetBodyJson.getBytes();
		packet.add(packetBodyJsonByte);
		byte[] packets = byteArrayProc.sysCopy(packet); // 将多个byte[]拼接
		return packets;
	}

	public void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub
		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));// 发送报文
	}

	public void ShowPacket(PacketInfo packetInfo) {
		System.out.print("cmd=" + packetInfo.getCmd());
		System.out.print("type=" + packetInfo.getType());
		System.out.print("opt=" + packetInfo.getOpt());
		System.out.print("sort=" + packetInfo.getSort());
		System.out.print("sid=" + packetInfo.getSid());
		System.out.print("seq=" + packetInfo.getSeq());
		System.out.println("ack=" + packetInfo.getAck());
	}

}
