package cn.smartpolice.protocol;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;

import org.apache.mina.core.session.IoSession;

import cn.smartpolice.netdao.DeviceDao;
import cn.smartpolice.netdao.DeviceInf;
import cn.smartpolice.netdao.UserDao;
import cn.smartpolice.netdao.UserInf;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.AppNode;
import cn.smartpolice.workbean.DevNode;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

public class ProtocolProc {


	PacketInfo packetInfo = new PacketInfo();
	// IoSession ioSession;
	
	
	public void RecvPktProc(IoSession ios, byte[] message) {

		// ioSession = ios;
		packetInfo.setIoSession(ios);
		if(ParsePktHead(message) == true){
			if(CheckPktValid() == true){
				//进入具体协议处理
				SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
			}
		}
	}
	

	// 解析报文首部
	public Boolean ParsePktHead(byte[] message) {
		// TODO Auto-generated method stub
		System.out.println("解包开始");
		if (message.length >= ConstParam.MASSAGE_LEN) {
			packetInfo.setMessage(message); // 为报文信息赋值
			packetInfo.setDate(new Date());
			packetInfo.setLength(message.length);// 报文长度
			StringBuffer strb = new StringBuffer();
			for (int i = 0; i < 4; i++)
				strb.append((char) message[i]);
			String znaf = strb.toString();

			if (znaf.equals("ZNAF")) {// 包含“ZNAF”时解析
				packetInfo.setCmd(message[4]);
				packetInfo.setType(message[5]);
				packetInfo.setOpt(message[6]);
				packetInfo.setSort(message[7]);

				if (packetInfo.getCmd() <= ConstParam.MAX_CMD) {// 小于13时解析
					int sid; // 解析byte[] message第8~12个字节为sid
					sid = (int) (message[8]) | (message[9] << 8)
							| (message[10] << 16) | (message[11] << 24);
					packetInfo.setSid(sid);
					int seq; // 解析byte[] message第12~15个字节为seq
					seq = (int) ((message[12]) | (message[13] << 8)
							| (message[14] << 16) | message[15] << 24);
					packetInfo.setSeq(seq);
					int ack; // 解析byte[] message第16~19个字节为ack
					ack = (int) ((message[16]) | (message[17] << 8)
							| (message[18] << 16) | message[19] << 24);
					packetInfo.setAck(ack);

					// 非注册报文是否sid>0
					if (!(packetInfo.getCmd() == ConstParam.CMD_2
							&& packetInfo.getType() == ConstParam.TYPE_1) && sid > 0) {
						// 根据opt，解析首部选项
						StringBuffer buffer = new StringBuffer();
						for (int i = 20; i < message.length; i++) {
							buffer.append((char) message[i]);
						}
						String datas = buffer.toString();
						int datapos = datas.indexOf("{\"DATA\"", 20)
								+ (ConstParam.MASSAGE_LEN + 1);
						packetInfo.setDatapos(datapos);// 数据域的起始位置
						byte opt = packetInfo.getOpt();
						if ((opt & 0x01) != 0) { // 第0位，即1时，表示此报文在服务器进行转发处理
							String hopt1 = new JsonAnalysis().getValue(datas,
									"HOPT");
							if (hopt1 != null) {
								String did = new JsonAnalysis().getValue(hopt1,
										"DID");
								System.out.println("DID:"+did);
								packetInfo.setDid(Integer.parseInt(did));
							}
						}
						if ((opt & 0x02) != 0) { // 第1位，即2时，表示此报文加密处理过
							String hopt2 = new JsonAnalysis().getValue(datas,
									"HOPT");
							if (hopt2 != null) {
								String keyseq = new JsonAnalysis().getValue(
										hopt2, "KEYSEQ");
								System.out.println("KEYSEQ:"+keyseq);
								packetInfo.setDid(Integer.parseInt(keyseq));
							}
						}
						if ((opt & 0x04) != 0) { // 第2位，即4时，表示此报文携带源地址，便于NAT穿透处理
							String hopt3 = new JsonAnalysis().getValue(datas,
									"HOPT");
							if (hopt3 != null) {
								String sip = new JsonAnalysis().getValue(hopt3,
										"SIP");
								String sport = new JsonAnalysis().getValue(
										hopt3, "SPORT");
								System.out.println("SIP:"+sip);
								System.out.println("SPORT:"+sport);
								packetInfo.setSip(sip);
								packetInfo.setSport(Integer.parseInt(sport));
							}
						}
						if ((opt & 0x08) != 0) { // 第3位，即8时，表示此报文是请求报文或者需要确认的报文

						}
						if ((opt & 0x10) != 0) { // 第4位，即16时，表示此报文是应答报文，确认号有效

						}
						if ((opt & 0x20) != 0) { // 第5位，即32时，表示此报文后面有非json格式，主要用于传输有二进制格式数据，不能完全使用json解析器进行解析

						}
					
					return true;
					} else
						System.out.println("非注册报文sid<0");
					return false;
				} else
					System.out.println("cmd>12");
				return false;
			} else
				System.out.println("报文头部不包含ZNAF");
			return false;
		} else
			System.out.println("报文长度小于20字节");
		return false;
	}
	
	// 检查报文有效性，所有协议都需要的一般性处理在这里实施。
	public Boolean CheckPktValid() {
//		// 注册请求
//		if (packetInfo.getCmd() == ConstParam.CMD_2
//				&& packetInfo.getType() == ConstParam.TYPE_1) {
//			SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//		}
//		// 连通测试报文
//		if (packetInfo.getCmd() == ConstParam.CMD_0
//				&& packetInfo.getType() == ConstParam.TYPE_0
//				&& packetInfo.getOpt() == ConstParam.OPT_8) {
//			SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//		}

		// 根据SID找到用户信息节点
		// dev和app的处理
		UserNode userNode = null;
		// 通过报文sort判断是dev还是app
		if (packetInfo.getSort() == ConstParam.SORT_2) {
			userNode = SysInfo.getInstance()
					.getDevNodeById(packetInfo.getSid());
//			System.out.println("前端设备");
		}
		if (packetInfo.getSort() == ConstParam.SORT_0) {
			userNode = SysInfo.getInstance()
					.getAppNodeById(packetInfo.getSid());
			System.out.println("app用户");
		}
		
		// cmd=1时不存在节点都从这儿进（第一次登陆全局队列中还不存在该节点）
		if (userNode == null ) {
			if(packetInfo.getCmd() == ConstParam.CMD_1 && packetInfo.getType() == ConstParam.TYPE_1){
			// 如果是dev登陆则创建新的dev节点
			// System.out.println("报文中的sid" + packetInfo.getSid());
			if (packetInfo.getSort() == ConstParam.SORT_2) {
				DeviceDao devDao = new DeviceDao();
				DeviceInf devinf = devDao.findDevByID(packetInfo.getSid());
				if (devinf != null) {
					// ip和port需要是局部变量，得到应答的报文会变化（掉线的情况）
					String ip = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getAddress().getHostAddress();
					int port = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getPort();
					DevNode devNode = new DevNode(); // 创建节点
					devNode.setIp(ip);
					devNode.setPort(port);
					devNode.setAccount(devinf.getUsername());
					// System.out.println("创建新节点时数据库中的devinf的deviceid:"+
					// devinf.getDeviceid());
					devNode.setId(devinf.getDeviceid());
					devNode.setRevPktDate(new Date());
					devNode.setRevPktId(packetInfo.getSeq());
					devNode.setIoSession(packetInfo.getIoSession());
					// 刚创建的节点的登录状态置为0，表示还未验证
					devNode.setState(ConstParam.LOGIN_STATE_0);
					// devNode.setSntPktId(0); //没有发送报文
					// 将节点信息添加到报文中 仅仅为了在ProtocolLogin中取获取节点id (可优化) 下同
					packetInfo.setDevNode(devNode);
					// 将节点添加到队列 在登录处理中从全局队列中取 下同
					SysInfo.getInstance().addUserNode(devNode);
					return true;
				}else
					return false;
				
			}
			// 若果是app登陆 则创建app节点
			else if (packetInfo.getSort() == ConstParam.SORT_0) {
				UserDao appDao = new UserDao();
				UserInf appInf = appDao.findAppuserByID(packetInfo.getSid());
				if (appInf != null) {
					String ip = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getAddress().getHostAddress();
					int port = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getPort();
					AppNode appNode = new AppNode(); // 创建节点
					appNode.setIp(ip);
					appNode.setPort(port);
					// 用户账号 ProtocolLogin会取出来对比（是用户名而不是真实姓名）
					appNode.setAccount(appInf.getUserName());
					// System.out.println("创建新节点时数据库中的devinf的deviceid:"+
					// appInf.getUserID());
					appNode.setId(appInf.getUserID());
					appNode.setRevPktDate(new Date());
					appNode.setRevPktId(packetInfo.getSeq());
					appNode.setIoSession(packetInfo.getIoSession());
					appNode.setState(ConstParam.LOGIN_STATE_0);
					// appNode.setSntPktId(0);
					packetInfo.setAppNode(appNode);
					SysInfo.getInstance().addUserNode(appNode); // 将节点添加到队列
					System.out.println("创建新节点的id：" + packetInfo.getSid());
					return true;
				}else
					return false;
				
			}
			else{
				System.out.print("不支持");
				return false;
			}
			}else{
				System.out.print("错误");
				return false;
			}
		}
		// 1.请求验证（登录请求后返回请求验证的报文），2.登录请求（掉线 还没来得及删除全局队列中的节点），3.保活报文从这儿进（节点存在时）
		if (userNode != null) {
			// 检查源IP和端口，看是否变化
			String ip = ((InetSocketAddress) packetInfo.getIoSession()
					.getRemoteAddress()).getAddress().getHostAddress();
			int port = ((InetSocketAddress) packetInfo.getIoSession()
					.getRemoteAddress()).getPort();
			// System.out.println("userNode!=null");
			if (!userNode.getIp().equals(ip) || userNode.getPort() != port) {
				userNode.setIp(ip);
				userNode.setPort(port);
				// userNode.setIoSession(packetInfo.getIoSession());
				// //不需要更新iosession
				// 因为在接收到报文时都会更新，即使在这儿检查出ip和port不一样也不影响iosession的更新
			}

			// Date revPktDate = new Date();
			userNode.setRevPktDate(new Date()); // 记录最近接收报文时间
			// dev.setRevPktId(packetInfo.getSeq()); 不能在这儿记录 下面还要比较
			if (userNode instanceof DevNode) { // 也可以通过报文sort判断是哪种节点
				DevNode devNode = (DevNode) userNode;
				packetInfo.setDevNode(devNode);
				// 更新devnode信息，在全局信息队列中也应该更新，后面处理的时候直接从队列中去节点
				SysInfo.getInstance().addUserNode(devNode);
				return true;
			}
			if (userNode instanceof AppNode) {
				AppNode appNode = (AppNode) userNode;
				packetInfo.setAppNode(appNode);
				// 更新devnode信息，在全局信息队列中也应该更新，后面处理的时候直接从队列中去节点
				SysInfo.getInstance().addUserNode(appNode);
				return true;
			}
			
//			// 如果不是保活报文和转发报文，进行如下处理 ？？？
//			if ((packetInfo.getOpt() & 0x01) == 0
//					&& !(packetInfo.getCmd() == ConstParam.CMD_1 && (packetInfo
//							.getType() == ConstParam.TYPE_6 || packetInfo
//							.getType() == ConstParam.TYPE_7))) {
//				System.out.println("opt="+packetInfo.getOpt());
//				
//				if (packetInfo.getSeq() > userNode.getRevPktId()) {
//					
//					System.out.println("第一次登录验证应答 从这儿进入");
//					userNode.setRevPktId(packetInfo.getSeq());
//					System.out.println("入口2");
//					SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//				}
//			} else {
//				//保活报文从这儿进（节点存在时） 登出报文  登出请求从这儿进				
//				System.out.println("其他协议处理从这儿进");
//				SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//			}
		}
		return null;
	}
	
}
