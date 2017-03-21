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
				//�������Э�鴦��
				SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
			}
		}
	}
	

	// ���������ײ�
	public Boolean ParsePktHead(byte[] message) {
		// TODO Auto-generated method stub
		System.out.println("�����ʼ");
		if (message.length >= ConstParam.MASSAGE_LEN) {
			packetInfo.setMessage(message); // Ϊ������Ϣ��ֵ
			packetInfo.setDate(new Date());
			packetInfo.setLength(message.length);// ���ĳ���
			StringBuffer strb = new StringBuffer();
			for (int i = 0; i < 4; i++)
				strb.append((char) message[i]);
			String znaf = strb.toString();

			if (znaf.equals("ZNAF")) {// ������ZNAF��ʱ����
				packetInfo.setCmd(message[4]);
				packetInfo.setType(message[5]);
				packetInfo.setOpt(message[6]);
				packetInfo.setSort(message[7]);

				if (packetInfo.getCmd() <= ConstParam.MAX_CMD) {// С��13ʱ����
					int sid; // ����byte[] message��8~12���ֽ�Ϊsid
					sid = (int) (message[8]) | (message[9] << 8)
							| (message[10] << 16) | (message[11] << 24);
					packetInfo.setSid(sid);
					int seq; // ����byte[] message��12~15���ֽ�Ϊseq
					seq = (int) ((message[12]) | (message[13] << 8)
							| (message[14] << 16) | message[15] << 24);
					packetInfo.setSeq(seq);
					int ack; // ����byte[] message��16~19���ֽ�Ϊack
					ack = (int) ((message[16]) | (message[17] << 8)
							| (message[18] << 16) | message[19] << 24);
					packetInfo.setAck(ack);

					// ��ע�ᱨ���Ƿ�sid>0
					if (!(packetInfo.getCmd() == ConstParam.CMD_2
							&& packetInfo.getType() == ConstParam.TYPE_1) && sid > 0) {
						// ����opt�������ײ�ѡ��
						StringBuffer buffer = new StringBuffer();
						for (int i = 20; i < message.length; i++) {
							buffer.append((char) message[i]);
						}
						String datas = buffer.toString();
						int datapos = datas.indexOf("{\"DATA\"", 20)
								+ (ConstParam.MASSAGE_LEN + 1);
						packetInfo.setDatapos(datapos);// ���������ʼλ��
						byte opt = packetInfo.getOpt();
						if ((opt & 0x01) != 0) { // ��0λ����1ʱ����ʾ�˱����ڷ���������ת������
							String hopt1 = new JsonAnalysis().getValue(datas,
									"HOPT");
							if (hopt1 != null) {
								String did = new JsonAnalysis().getValue(hopt1,
										"DID");
								System.out.println("DID:"+did);
								packetInfo.setDid(Integer.parseInt(did));
							}
						}
						if ((opt & 0x02) != 0) { // ��1λ����2ʱ����ʾ�˱��ļ��ܴ����
							String hopt2 = new JsonAnalysis().getValue(datas,
									"HOPT");
							if (hopt2 != null) {
								String keyseq = new JsonAnalysis().getValue(
										hopt2, "KEYSEQ");
								System.out.println("KEYSEQ:"+keyseq);
								packetInfo.setDid(Integer.parseInt(keyseq));
							}
						}
						if ((opt & 0x04) != 0) { // ��2λ����4ʱ����ʾ�˱���Я��Դ��ַ������NAT��͸����
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
						if ((opt & 0x08) != 0) { // ��3λ����8ʱ����ʾ�˱����������Ļ�����Ҫȷ�ϵı���

						}
						if ((opt & 0x10) != 0) { // ��4λ����16ʱ����ʾ�˱�����Ӧ���ģ�ȷ�Ϻ���Ч

						}
						if ((opt & 0x20) != 0) { // ��5λ����32ʱ����ʾ�˱��ĺ����з�json��ʽ����Ҫ���ڴ����ж����Ƹ�ʽ���ݣ�������ȫʹ��json���������н���

						}
					
					return true;
					} else
						System.out.println("��ע�ᱨ��sid<0");
					return false;
				} else
					System.out.println("cmd>12");
				return false;
			} else
				System.out.println("����ͷ��������ZNAF");
			return false;
		} else
			System.out.println("���ĳ���С��20�ֽ�");
		return false;
	}
	
	// ��鱨����Ч�ԣ�����Э�鶼��Ҫ��һ���Դ���������ʵʩ��
	public Boolean CheckPktValid() {
//		// ע������
//		if (packetInfo.getCmd() == ConstParam.CMD_2
//				&& packetInfo.getType() == ConstParam.TYPE_1) {
//			SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//		}
//		// ��ͨ���Ա���
//		if (packetInfo.getCmd() == ConstParam.CMD_0
//				&& packetInfo.getType() == ConstParam.TYPE_0
//				&& packetInfo.getOpt() == ConstParam.OPT_8) {
//			SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//		}

		// ����SID�ҵ��û���Ϣ�ڵ�
		// dev��app�Ĵ���
		UserNode userNode = null;
		// ͨ������sort�ж���dev����app
		if (packetInfo.getSort() == ConstParam.SORT_2) {
			userNode = SysInfo.getInstance()
					.getDevNodeById(packetInfo.getSid());
//			System.out.println("ǰ���豸");
		}
		if (packetInfo.getSort() == ConstParam.SORT_0) {
			userNode = SysInfo.getInstance()
					.getAppNodeById(packetInfo.getSid());
			System.out.println("app�û�");
		}
		
		// cmd=1ʱ�����ڽڵ㶼�����������һ�ε�½ȫ�ֶ����л������ڸýڵ㣩
		if (userNode == null ) {
			if(packetInfo.getCmd() == ConstParam.CMD_1 && packetInfo.getType() == ConstParam.TYPE_1){
			// �����dev��½�򴴽��µ�dev�ڵ�
			// System.out.println("�����е�sid" + packetInfo.getSid());
			if (packetInfo.getSort() == ConstParam.SORT_2) {
				DeviceDao devDao = new DeviceDao();
				DeviceInf devinf = devDao.findDevByID(packetInfo.getSid());
				if (devinf != null) {
					// ip��port��Ҫ�Ǿֲ��������õ�Ӧ��ı��Ļ�仯�����ߵ������
					String ip = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getAddress().getHostAddress();
					int port = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getPort();
					DevNode devNode = new DevNode(); // �����ڵ�
					devNode.setIp(ip);
					devNode.setPort(port);
					devNode.setAccount(devinf.getUsername());
					// System.out.println("�����½ڵ�ʱ���ݿ��е�devinf��deviceid:"+
					// devinf.getDeviceid());
					devNode.setId(devinf.getDeviceid());
					devNode.setRevPktDate(new Date());
					devNode.setRevPktId(packetInfo.getSeq());
					devNode.setIoSession(packetInfo.getIoSession());
					// �մ����Ľڵ�ĵ�¼״̬��Ϊ0����ʾ��δ��֤
					devNode.setState(ConstParam.LOGIN_STATE_0);
					// devNode.setSntPktId(0); //û�з��ͱ���
					// ���ڵ���Ϣ��ӵ������� ����Ϊ����ProtocolLogin��ȡ��ȡ�ڵ�id (���Ż�) ��ͬ
					packetInfo.setDevNode(devNode);
					// ���ڵ���ӵ����� �ڵ�¼�����д�ȫ�ֶ�����ȡ ��ͬ
					SysInfo.getInstance().addUserNode(devNode);
					return true;
				}else
					return false;
				
			}
			// ������app��½ �򴴽�app�ڵ�
			else if (packetInfo.getSort() == ConstParam.SORT_0) {
				UserDao appDao = new UserDao();
				UserInf appInf = appDao.findAppuserByID(packetInfo.getSid());
				if (appInf != null) {
					String ip = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getAddress().getHostAddress();
					int port = ((InetSocketAddress) packetInfo.getIoSession()
							.getRemoteAddress()).getPort();
					AppNode appNode = new AppNode(); // �����ڵ�
					appNode.setIp(ip);
					appNode.setPort(port);
					// �û��˺� ProtocolLogin��ȡ�����Աȣ����û�����������ʵ������
					appNode.setAccount(appInf.getUserName());
					// System.out.println("�����½ڵ�ʱ���ݿ��е�devinf��deviceid:"+
					// appInf.getUserID());
					appNode.setId(appInf.getUserID());
					appNode.setRevPktDate(new Date());
					appNode.setRevPktId(packetInfo.getSeq());
					appNode.setIoSession(packetInfo.getIoSession());
					appNode.setState(ConstParam.LOGIN_STATE_0);
					// appNode.setSntPktId(0);
					packetInfo.setAppNode(appNode);
					SysInfo.getInstance().addUserNode(appNode); // ���ڵ���ӵ�����
					System.out.println("�����½ڵ��id��" + packetInfo.getSid());
					return true;
				}else
					return false;
				
			}
			else{
				System.out.print("��֧��");
				return false;
			}
			}else{
				System.out.print("����");
				return false;
			}
		}
		// 1.������֤����¼����󷵻�������֤�ı��ģ���2.��¼���󣨵��� ��û���ü�ɾ��ȫ�ֶ����еĽڵ㣩��3.����Ĵ���������ڵ����ʱ��
		if (userNode != null) {
			// ���ԴIP�Ͷ˿ڣ����Ƿ�仯
			String ip = ((InetSocketAddress) packetInfo.getIoSession()
					.getRemoteAddress()).getAddress().getHostAddress();
			int port = ((InetSocketAddress) packetInfo.getIoSession()
					.getRemoteAddress()).getPort();
			// System.out.println("userNode!=null");
			if (!userNode.getIp().equals(ip) || userNode.getPort() != port) {
				userNode.setIp(ip);
				userNode.setPort(port);
				// userNode.setIoSession(packetInfo.getIoSession());
				// //����Ҫ����iosession
				// ��Ϊ�ڽ��յ�����ʱ������£���ʹ���������ip��port��һ��Ҳ��Ӱ��iosession�ĸ���
			}

			// Date revPktDate = new Date();
			userNode.setRevPktDate(new Date()); // ��¼������ձ���ʱ��
			// dev.setRevPktId(packetInfo.getSeq()); �����������¼ ���滹Ҫ�Ƚ�
			if (userNode instanceof DevNode) { // Ҳ����ͨ������sort�ж������ֽڵ�
				DevNode devNode = (DevNode) userNode;
				packetInfo.setDevNode(devNode);
				// ����devnode��Ϣ����ȫ����Ϣ������ҲӦ�ø��£����洦���ʱ��ֱ�ӴӶ�����ȥ�ڵ�
				SysInfo.getInstance().addUserNode(devNode);
				return true;
			}
			if (userNode instanceof AppNode) {
				AppNode appNode = (AppNode) userNode;
				packetInfo.setAppNode(appNode);
				// ����devnode��Ϣ����ȫ����Ϣ������ҲӦ�ø��£����洦���ʱ��ֱ�ӴӶ�����ȥ�ڵ�
				SysInfo.getInstance().addUserNode(appNode);
				return true;
			}
			
//			// ������Ǳ���ĺ�ת�����ģ��������´��� ������
//			if ((packetInfo.getOpt() & 0x01) == 0
//					&& !(packetInfo.getCmd() == ConstParam.CMD_1 && (packetInfo
//							.getType() == ConstParam.TYPE_6 || packetInfo
//							.getType() == ConstParam.TYPE_7))) {
//				System.out.println("opt="+packetInfo.getOpt());
//				
//				if (packetInfo.getSeq() > userNode.getRevPktId()) {
//					
//					System.out.println("��һ�ε�¼��֤Ӧ�� ���������");
//					userNode.setRevPktId(packetInfo.getSeq());
//					System.out.println("���2");
//					SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//				}
//			} else {
//				//����Ĵ���������ڵ����ʱ�� �ǳ�����  �ǳ�����������				
//				System.out.println("����Э�鴦��������");
//				SysInfo.getPrtocolBases()[packetInfo.getCmd()].ParsePktProto(packetInfo);
//			}
		}
		return null;
	}
	
}
