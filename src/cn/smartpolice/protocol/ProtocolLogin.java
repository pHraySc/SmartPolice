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
	String snum; // ������
	String sver;
	String user; // �û��˺�
	String code; //���ܱ����������
	int errorPktState; // ��ǲ�ͬ�����ģ�һ���ǵ�½������Ϣ�����������(errorState=0)��һ����������֤����(errorState=1)��
	// String ret; // ���ش���
	int random; // �����


	public void ParsePktProto(PacketInfo packetInfo) {
		// TODO Auto-generated method stub
		super.revPacket = packetInfo;
		System.out.println("login���Ľ���");
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char) revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
		// ��¼����
		if (revPacket.getType() == ConstParam.TYPE_1) { 
			snum = jsonAnalysis.getValue(data, "SNUM");
			sver = jsonAnalysis.getValue(data, "SVER");
			user = jsonAnalysis.getValue(data, "USER");
		}
		// ��¼��֤     �ǳ���ֻ֤��user
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
		// dev�豸����
		if (revPacket.getSort() == ConstParam.SORT_2) {
			// �ӽڵ�����л�ȡ�ڵ� ����һ�ε�½���ɹ�ʱ �Ժ��¼����ڽڵ�
			userNode = SysInfo.getInstance().getDevNodeById(revPacket.getSid());
			//System.out.println(userNode);
		}
		// App
		if (revPacket.getSort() == ConstParam.SORT_0) {
			userNode = SysInfo.getInstance().getAppNodeById(revPacket.getSid());
			//System.out.println(userNode);
		}
		System.out.println(userNode);
		// ��¼�����Ĵ���
		if (revPacket.getType() == ConstParam.TYPE_1) {
			// System.out.println("state��" + devNode.getState());
			// ����һ�ε�½���ɹ�ʱ �Ժ��¼����ڽڵ�,�����������state��Ϊ1����Ȼ�����յ���¼������ʱ��ֱ�ӷ��ش�����
			// ͨ���ڵ�״̬�жϽڵ��¼״̬
			if (userNode.getState() == ConstParam.LOGIN_STATE_0
					|| userNode.getState() == ConstParam.LOGIN_STATE_1) {
				// ������Ϣ�д�����е��û��������ݿ��е��û�����һ��
				if (snum == null && sver == null
						&& !user.equals(userNode.getAccount())) { // �ȽϽڵ���account�ͱ����е�user
					System.out.print("������Ϣ����ȷ");
					if (revPacket.getSort() == ConstParam.SORT_2) {
						DevNode devNode = (DevNode) userNode;
						SysInfo.getInstance().removeUserNode(devNode); // ɾ�������еĽڵ�
					}
					if (revPacket.getSort() == ConstParam.SORT_0) {
						AppNode appNode = (AppNode) userNode;
						SysInfo.getInstance().removeUserNode(appNode); // ɾ�������еĽڵ�
					}
					errorPktState = ConstParam.ERROR_PKT_STATE_0; // ��Ƿ��͵�һ�ִ�����
					byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1); // ������Ҫ����Ϣ���������¼�ʺź�user����ͬ�ĳ�����
					SendPkt(errorPacket); // ����һ��������
					return;
				} else {
					// ���ϵͳ�����ļ��������˲���Ҫ��֤��ֱ�ӷ��سɹ�����
					//dev
					if (SysInfo.getSysCfgInfo().getState() == ConstParam.CHECK_STATE_0 && revPacket.getSort() == ConstParam.SORT_2) {
						// �ڵ��¼״̬��Ϊ2 ֱ�Ӵ�0��Ϊ2
						userNode.setState(ConstParam.LOGIN_STATE_2);
						// ���û���¼��Ϣ�����һ����¼ ����һ��userLog�������
						DevLog devLog = new DevLog();
						// ��Date���͵�LoginDateת��Ϊdate����
						// SimpleDateFormat sdf = new
						// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						Date logindate = new Date();
						devLog.setDeviceid(userNode.getId());
						devLog.setDate(logindate);
						devLog.setIp(userNode.getIp());
						devLog.setPort(userNode.getPort());
						devLog.setOperate(ConstParam.LOGIN_OPERATE_1);
						DevLogDao devLogDao = new DevLogDao();
						devLogDao.insertDevLogInf(devLog); // ���û���¼��Ϣ�����
						byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // �����½�ɹ��ı���
						SendPkt(successPacket);// �����ǰ�û�״̬Ϊ2���Ѿ���¼�ɹ���ֱ�ӷ��ص�¼�ɹ�����
						return;
					}
					//app ͬ��
					if(SysInfo.getSysCfgInfo().getState() == ConstParam.CHECK_STATE_0 && revPacket.getSort() == ConstParam.SORT_0){
						// �ڵ��¼״̬��Ϊ2 ֱ�Ӵ�0��Ϊ2
						userNode.setState(ConstParam.LOGIN_STATE_2);
						// ���û���¼��Ϣ�����һ����¼ ����һ��userLog�������
						UserLog userLog = new UserLog();
						// ��Date���͵�LoginDateת��Ϊdate����
						// SimpleDateFormat sdf = new
						// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						Date logindate = new Date();
						userLog.setUserid(userNode.getId());
						userLog.setDate(logindate);
						userLog.setIp(userNode.getIp());
						userLog.setPort(userNode.getPort());
						userLog.setOperate(ConstParam.LOGIN_OPERATE_1);
						UserLogDao userLogDao = new UserLogDao();
						userLogDao.insertUserLogInf(userLog); // ���û���¼��Ϣ�����
						byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // �����½�ɹ��ı���
						SendPkt(successPacket);// �����ǰ�û�״̬Ϊ2���Ѿ���¼�ɹ���ֱ�ӷ��ص�¼�ɹ�����
						return;
					}
					// ���ϵͳ�����ļ�����������Ҫ��֤��������֤������
					if (SysInfo.getSysCfgInfo().getState() == ConstParam.CHECK_STATE_1) {
						System.out.println("snum=" + snum + "   user=" + user);
						// ��������ڵ�¼ʱ����
						Random rando = new Random();
						random = rando.nextInt(99999) % 90000 + 10000; // ����һ�������
						
						System.out.print("���������logindate��");
						userNode.setLoginDate(random);
						userNode.setState(ConstParam.LOGIN_STATE_1); // ���û�״̬��Ϊ1
						// ���º��devNodeҪ����ȫ�ֶ�����
						if (revPacket.getSort() == ConstParam.SORT_2) {
							DevNode devNode = (DevNode) userNode;
							SysInfo.getInstance().addUserNode(devNode);
						}
						if (revPacket.getSort() == ConstParam.SORT_0) {
							AppNode appNode = (AppNode) userNode;
							SysInfo.getInstance().addUserNode(appNode);
						}
						byte[] checkPacket = PackPkt(ConstParam.SENT_PKT_TYPE_2); // �����öԷ�������֤������de����
						SendPkt(checkPacket); // �Է�������֤������
						return;
					}
				}
			}
			// �ڵ����ʱ���ڵ㷢��½����ʱ�������еĽڵ�״̬state=2ʱֱ�ӷ��ص�¼�ɹ�
			if (userNode.getState() == ConstParam.LOGIN_STATE_2 && revPacket.getSort() == ConstParam.SORT_2) {
				// System.out.println("TYPE=1  code=" + code + "   user=" +
				// user);
				// ���û���¼��Ϣ�����һ����¼ ����һ��userLog�������
				DevLog devLog = new DevLog();
				// ��Date���͵�LoginDateת��Ϊdate����
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date logindate = new Date();
				devLog.setDeviceid(userNode.getId());
				devLog.setDate(logindate);
				devLog.setIp(userNode.getIp());
				devLog.setPort(userNode.getPort());
				devLog.setOperate(ConstParam.LOGIN_OPERATE_1);
				DevLogDao devLogDao = new DevLogDao();
				devLogDao.insertDevLogInf(devLog); // ���û���¼��Ϣ�����
				byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // �����½�ɹ��ı���
				SendPkt(successPacket);// �����ǰ�û�״̬Ϊ2���Ѿ���¼�ɹ���ֱ�ӷ��ص�¼�ɹ�����
				return;
			}
			if(userNode.getState() == ConstParam.LOGIN_STATE_2 && revPacket.getSort() == ConstParam.SORT_0){
				// ���û���¼��Ϣ�����һ����¼ ����һ��userLog�������
				UserLog userLog = new UserLog();
				// ��Date���͵�LoginDateת��Ϊdate����
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date logindate = new Date();
				userLog.setUserid(userNode.getId());
				userLog.setDate(logindate);
				userLog.setIp(userNode.getIp());
				userLog.setPort(userNode.getPort());
				userLog.setOperate(ConstParam.LOGIN_OPERATE_1);
				UserLogDao userLogDao = new UserLogDao();
				userLogDao.insertUserLogInf(userLog); // ���û���¼��Ϣ�����
				byte[] successPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // �����½�ɹ��ı���
				SendPkt(successPacket);// �����ǰ�û�״̬Ϊ2���Ѿ���¼�ɹ���ֱ�ӷ��ص�¼�ɹ�����
				return;
			}
		}
		
		// ��֤������
		if (revPacket.getType() == ConstParam.TYPE_3) { 
			if (userNode.getState() == ConstParam.LOGIN_STATE_0
					|| userNode.getState() == ConstParam.LOGIN_STATE_2) { // ֱ�ӳ������س�����
				// System.out.println("��֤����ڵ�state����");
				byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1); // ������֤ʧ��Ӧ����
				SendPkt(errorPacket);
				return;
			}
			if (userNode.getState() == ConstParam.LOGIN_STATE_1) {
				// System.out.println("TYPE=3  code=" + code + "   user=" +
				// user);
				//System.out.println("��֤�û���");
				if (user.equals(userNode.getAccount())) {
					//System.out.println("������");
					try {
						BASE64Decoder decoder = new BASE64Decoder();
						String pass = new String(decoder.decodeBuffer(code)); // base64����
						byte[] password;
						if(revPacket.getSort() == ConstParam.SORT_2){ //dev
							DeviceDao devDao = new DeviceDao();
							DeviceInf deviceInf = devDao.findDevByID(revPacket
									.getSid()); // ���ݿ��ѯ
							// ���ݿ��е�������Ҫ������
							password = deviceInf.getPassword().getBytes(); // ��Կ
						} else{ //app
							UserDao userDao = new UserDao();
							UserInf userInf =userDao.findAppuserByID(revPacket.getSid());
							password = userInf.getPassword().getBytes();
						}
						//�����������
						byte[] passs = String.valueOf(userNode.getLoginDate()).getBytes(); 
						byte[] rondByte = passs;
						System.out.println("����������"+pass);
						//System.out.println("�����������"+pass.toCharArray());
						for (int i = 0; i < passs.length; i++) {
							
							rondByte[i] = (byte) (passs[i] ^ password[i]);
							if(rondByte[i] == 0){
								rondByte[i] = (byte) (passs[i] + 0 ^ password[i]);
							}
						}
						//System.out.println("�����ܽ���");
						// System.out.println(passs);
						String ron = new String(rondByte);
						//System.out.println("�����ȷ");
						//System.out.println("���ܺ���������" + passs);
						System.out.println("�����ܺ��code:" + ron);
						
						if (ron.equals(pass)) { 
							long loginDate = System.currentTimeMillis() / 1000;
							userNode.setLoginDate(loginDate); // �õ�¼ʱ��Ϊ��ǰʱ��
							userNode.setState(2); // ���û�״̬��2
							//app
							if (revPacket.getSort() == ConstParam.SORT_0) {
								System.out.println(revPacket.getSort());
								AppNode appNode = (AppNode) userNode;
								// �����º��devnode���µ�ȫ�����ݶ�����
								SysInfo.getInstance().addUserNode(appNode);
								// ���û���¼��Ϣ�����һ����¼ ����һ��userLog�������
								UserLog userLog = new UserLog();
								// ��Date���͵�LoginDateת��Ϊdate����
								// SimpleDateFormat sdf = new
								// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
								Date logindate = new Date();
								userLog.setUserid(userNode.getId());
								userLog.setDate(logindate);
								userLog.setIp(userNode.getIp());
								userLog.setPort(userNode.getPort());
								userLog.setOperate(ConstParam.LOGIN_OPERATE_1);
								UserLogDao userLogDao = new UserLogDao();
								userLogDao.insertUserLogInf(userLog); // ���û���¼��Ϣ�����
								byte[] checkSuccessPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // ������֤�ɹ�Ӧ����
								SendPkt(checkSuccessPacket);
								return;
							}
							//dev
							if (revPacket.getSort() == ConstParam.SORT_2) {
								DevNode devNode = (DevNode) userNode;
								SysInfo.getInstance().addUserNode(devNode);
								// ���û���¼��Ϣ�����һ����¼ ����һ��userLog�������
								DevLog devLog = new DevLog();
								// ��Date���͵�LoginDateת��Ϊdate����
								// SimpleDateFormat sdf = new
								// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
								Date logindate = new Date();
								devLog.setDeviceid(userNode.getId());
								devLog.setDate(logindate);
								devLog.setIp(userNode.getIp());
								devLog.setPort(userNode.getPort());
								devLog.setOperate(ConstParam.LOGIN_OPERATE_1);
								DevLogDao devLogDao = new DevLogDao();
								devLogDao.insertDevLogInf(devLog); // ���û���¼��Ϣ�����
								byte[] checkSuccessPacket = PackPkt(ConstParam.SENT_PKT_TYPE_3); // ������֤�ɹ�Ӧ����
								SendPkt(checkSuccessPacket);
								return;
							}
							
						} else {
							// �����֤ʧ�� ɾ���ڵ�
							if (revPacket.getSort() == ConstParam.SORT_2) {
								DevNode devNode = (DevNode) userNode;
								SysInfo.getInstance().removeUserNode(devNode); // ɾ�������еĽڵ�
							}
							if (revPacket.getSort() == ConstParam.SORT_0) {
								AppNode appNode = (AppNode) userNode;
								SysInfo.getInstance().removeUserNode(appNode);
							}
							errorPktState = ConstParam.ERROR_PKT_STATE_1; //������֤ʧ�ܵı���
							byte[] checkFailPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1); // ������֤ʧ��Ӧ����
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
		
		//�ǳ�����
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
					// ��Date���͵�LoginDateת��Ϊdate����
					// SimpleDateFormat sdf = new
					// SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date logindate = new Date();
					userLog.setUserid(userNode.getId());
					userLog.setDate(logindate);
					userLog.setIp(userNode.getIp());
					userLog.setPort(userNode.getPort());
					userLog.setOperate(ConstParam.LOGIN_OPERATE_0);
					UserLogDao userLogDao = new UserLogDao();
					userLogDao.insertUserLogInf(userLog); // ���û���¼��Ϣ�����
				}
				System.out.println("��Ϣ��ȷ  ɾ���ڵ�");
			}else{
				System.out.println("��Ϣ����ȷ  δ����");
			}
		}	
		
		//�����
		if(revPacket.getType() == ConstParam.TYPE_6) {
			if(userNode != null){
				// ���½��յ����ĵ�ʱ��
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
//				System.out.print("�ڵ����ߣ�����Ӧ���Ĳ�����");
//			}
			
		}
	}


	public byte[] PackPkt(int i) {
		// TODO Auto-generated method stub

		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		// �����ݿ��л�ȡ

		ServerDao serverDao = new ServerDao();
		ServerInf server = serverDao.findServer(1);

		System.out.println(server);
		// int sid = server.getServerId();
		int sendseq;
		int ackseq;
		// ����������appuser����dev�ǵĲ�ͬ����
		if (revPacket.getSort() == ConstParam.SORT_0) {
			sendseq = revPacket.getAppNode().getSntPktId(); // ��ȡ���ͱ���id
			ackseq = revPacket.getSeq(); // ACK��ֵ���յ������ĵ���ţ����������
		} else {
			sendseq = revPacket.getDevNode().getSntPktId(); // ��ȡ���ͱ���id
			ackseq = revPacket.getSeq();
		}
		String packetBody = null; // ����������

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
		byteChar[2] = ConstParam.OPT_16; // opt=16 ��ʾ�˱�����Ӧ���ģ�ȷ�Ϻ���Ч��
		byteChar[3] = ConstParam.SORT_3; // sort=3 SORT��ʾ���ͷ���� 3����web��������

		System.out.println(i);
		switch (i) {
		case 1: // ��Ҫ����Ϣ���������¼�ʺź�user����ͬʱ ���صĳ����ģ�������֤ʧ��ʱ ���ش�����
			System.out.println("case1");

			if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
				packetBody = "{'DATA':{'RET':'-1','INFO':'��Ҫ����Ϣ���������¼�ʺź�user����ͬ'}}"; // ��������
			}
			if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
				packetBody = "{'DATA':{'RET':'-1','INFO':'״̬��������벻��ȷ'}}"; // ��������
			}
			break;
		case 2: // �����öԷ�������֤������de����
			System.out.println("case2");
			packetBody = "{'DATA':{'RET':'1','INFO':'" + random + "'}}"; // ��������
			break;
		case 3: // ͨ����֤�󷵻صĵ�¼�ɹ����� �� �����¼ʱ�ڵ�״̬state=2ʱ��ϵͳ�ļ����ò���Ҫ��֤ʱ���صĵ�¼�ɹ�����
			System.out.println("case3");
			packetBody = "{'DATA':{'RET':'0','INFO':'" + revPacket.getSid()
					+ "'}}"; // ��������
			break;
		case 4:
			System.out.println("case4����Ӧ����û����");
			
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
		byte[] packets = byteArrayProc.sysCopy(packet); // �����byte[]ƴ��
		return packets;
	}


	public void SendPkt(byte[] sendPacket) {
		// TODO Auto-generated method stub
		revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));// ���ͱ���
	}

	//��ʾ������Ϣ
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
