package cn.smartpolice.protocol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.netdao.ContactInf;
import cn.smartpolice.netdao.ContactDao;
import cn.smartpolice.netdao.ContactWait;
import cn.smartpolice.netdao.ContactWaitDao;
import cn.smartpolice.netdao.UserDao;
import cn.smartpolice.netdao.UserInf;
import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.PacketInfo;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

/**
 * cmd = 10;
 * ��ϵ�˹���Э��
 */
public class ProtocolContact extends ProtocolBase{
		String user;//�������˺�
		String cuser;//��ϵ���˺�
		String allas;//��ϵ�˱���
		String group; //����
		String message;//����
		String info;
		String contact;//��������
		String pass;
		byte[] from;//û�õ� ��֪����ô��
		byte[] kind;//
		int errorPktState;// ���û�ɹ���ӵı���

		Date contactDate;//���ʱ��
		Date deleteDate;//����ʱ��
		
	void ParsePktProto(PacketInfo packetInfo) {
		System.out.print("Contact���Ľ���");
		super.revPacket = packetInfo;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = revPacket.getDatapos(); i < revPacket.getMessage().length; i++) {
			stringBuffer.append((char)revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
			user = jsonAnalysis.getValue(data, "USER");
			cuser = jsonAnalysis.getValue(data, "CUSER");
			allas = jsonAnalysis.getValue(data, "ALLAS");
			group = jsonAnalysis.getValue(data, "CROUP");
			message = jsonAnalysis.getValue(data, "MESSAGE");
			pass = jsonAnalysis.getValue(data, "PASS");
			info = jsonAnalysis.getValue(data, "INFO");		
			kind = jsonAnalysis.getValue(data, "KIND").getBytes();
	}
	
	public void ExecProto() {	

			//�����ϵ������
			if(revPacket.getType() == ConstParam.TYPE_1){
				
				//��ѯ��ϵ�˱��Ƿ����
					ContactDao contactDao = new ContactDao();
					ContactInf contactInf = contactDao.findContactByuser(user, cuser);
					
				if(contactInf == null){
					// ͨ����ѯ�û���Ϣ��
					UserDao userDao = new UserDao();
					UserInf cuserInf = userDao.findAppuserByName(cuser);//��ϵ�û�
					
					if(cuserInf == null){
						errorPktState = ConstParam.ERROR_PKT_STATE_0; // ��Ƿ��͵�һ�ִ����ģ��û���Ϣ���в����ڸ�cuser��
						byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
						SendPkt(errorPacket);
					}else{
						//��ȡset���ж�set&8
						if(Integer.parseInt(cuserInf.getSet())==8){
							errorPktState = ConstParam.SENT_PKT_TYPE_1; // ��Ƿ��͵�һ�ִ�����,�Է���ֹ�����ϵ��
							byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
							SendPkt(errorPacket);
						}else{
							UserDao userdao = new UserDao();
							UserInf userInf = userdao.findAppuserByName(user);//���û�
							
							ContactWait contactWait = new ContactWait();
							Date applytime = new Date();
							contactWait.setMasterId(userInf.getUserID());
							contactWait.setContactedId(cuserInf.getUserID());
							contactWait.setgroup(group);
							contactWait.setApplyTime(applytime);
							contactWait.getState();
							contactWait.setMessage(message);
							ContactWaitDao contactWaitDao = new ContactWaitDao();
							contactWaitDao.insertContactWait(contactWait);
							
							UserNode cuserNode = null;
							cuserNode = SysInfo.getInstance().getDevNodeById(cuserInf.getUserID());
							
							
							//��ѯcuser�Ƿ����ߣ��нڵ���state = 2
							if(cuserNode != null && Integer.parseInt(cuserInf.getState()) == 2){
								if(Integer.parseInt(userInf.getSet())==2){
									errorPktState = ConstParam.SENT_PKT_TYPE_2;
									byte[] waitPkt = PackPkt(ConstParam.SENT_PKT_TYPE_2);//�ȴ�ȷ��
									SendPkt(waitPkt);
									
								}
							}else{
								ContactInf contactinf = new ContactInf();
								contactDate = new Date();
								contactinf.setMasterId(userInf.getUserID());
								contactinf.setMasterId(cuserInf.getUserID());
								contactinf.setGroup(group);
								contactinf.setAliasid(allas);
								contactinf.setsetTime(contactDate);
								contactDao.insertUserCon(contactInf);
								byte[] successPkt = PackPkt(ConstParam.CONTACT_0);
								SendPkt(successPkt);
								
							}
						}
					}					
				}else{
					byte[] successPacket = PackPkt(ConstParam.CONTACT_1);//�˺��ѹ�����
					SendPkt(successPacket);
				}
		}
			
		//������ϵ����
		if(revPacket.getType() == ConstParam.TYPE_3){
			
				//��ѯ�Ƿ�����ϵ��
				ContactDao contactDao = new ContactDao();
				ContactInf contactInf = contactDao.findContactByuser(user,cuser);
				
				if(contactInf == null){
					errorPktState = ConstParam.ERROR_PKT_STATE_0; // ��Ƿ��͵�һ�ִ����ģ���ϵ����Ϣ���в����ڸ�cuser��
					byte[] errorPacket = PackPkt(ConstParam.CONTACT_2);
					SendPkt(errorPacket);
				}else{
					
					UserDao userdao = new UserDao();
					UserInf userInf = userdao.findAppuserByName(user);//���û�
					
					if(pass == userInf.getPassword()){
						//��֤�����Ƿ���ȷ
						contactDao.deleteContactInf(contactInf);//ɾ�������¼
						byte[] deleteSuccessPacket = PackPkt(ConstParam.CONTACT_0);
						SendPkt(deleteSuccessPacket);
						deleteDate = new Date();
					}else{
						byte[] errorPacket = PackPkt(ConstParam.ERROR_PKT_STATE_1);//���벻��ȷ
						SendPkt(errorPacket);
					}
				}
		}	
				
		//��ȡ��ϵ������
		if(revPacket.getType() == ConstParam.TYPE_5){
			int n;//��ϵ�˸���
			ContactDao contactDao = new ContactDao();
			//ContactInf contactInf = (ContactInf) contactDao.findallContactByuser(user);
			List<ContactInf> contactInf = contactDao.findallContactByuser(user);
			if(contactInf != null){
				n = contactInf.size();
				int i;
				
				for(i = 0;i < contactInf.size();i ++){
					ContactInf cuserInf = contactInf.get(i);
					String cuser = Integer.toString(cuserInf.getContactId());
					String allas = cuserInf.getAliasid();
					String group = cuserInf.getGroup();
					System.out.printf(cuser + "" + allas + "" + group);
					System.out.println();//��һ��
				}
			}else{
				errorPktState = ConstParam.SENT_PKT_TYPE_1; //û����ϵ��
				byte[] errorPacket = PackPkt(ConstParam.ERROR_PKT_STATE_1);//���벻��ȷ
				SendPkt(errorPacket);
			}
		}

		//��ϵ��ͨ������
		if(revPacket.getType() == ConstParam.TYPE_7){
			//ContactDao contactDao = new ContactDao();
			//ContactInf contactInf = contactDao.findallContactByuser(user);
			
			ByteArrayProc byteArrayProc = new ByteArrayProc();
			if(kind == byteArrayProc.int2bytes(1)){
				byte[] informPkt = PackPkt(ConstParam.REQUEST_INFORM_0);
				SendPkt(informPkt);
			}
			if(kind == byteArrayProc.int2bytes(1)){
				byte[] informPkt = PackPkt(ConstParam.REQUEST_INFORM_1);
				SendPkt(informPkt);
			}
			if(kind == byteArrayProc.int2bytes(2)){
				byte[] informPkt = PackPkt(ConstParam.REQUEST_INFORM_2);
				SendPkt(informPkt);
			}
			if(kind == byteArrayProc.int2bytes(3)){
				byte[] informPkt = PackPkt(ConstParam.REQUEST_INFORM_3);
				SendPkt(informPkt);
			}
			if(kind == byteArrayProc.int2bytes(4)){
				byte[] informPkt = PackPkt(ConstParam.REQUEST_INFORM_4);
				SendPkt(informPkt);
			}
		}

		//�����ϵ��
		if(revPacket.getType() == ConstParam.TYPE_9){
			
			//��ѯ��ϵ�˱�
			ContactDao contactDao = new ContactDao();
			ContactInf contactInf = contactDao.findContactByuser(user, cuser);
			
			ByteArrayProc byteArrayProc = new ByteArrayProc();
			
			//�������
			if(kind == byteArrayProc.int2bytes(1)){
				contactDao.updateContactInf(contactInf, 1);	
				byte[] updatesuccessPacket = PackPkt(ConstParam.CONTACT_0);//���سɹ�����
				SendPkt(updatesuccessPacket);
			}else{
					byte[] updatefailurePacket = PackPkt(ConstParam.CONTACT_1);//����ʧ�ܱ���
					SendPkt(updatefailurePacket);
			}
			//�������
			if(kind == byteArrayProc.int2bytes(1)){
				contactDao.updateContactInf(contactInf, 2);
				byte[] updateSuccessPacket = PackPkt(ConstParam.CONTACT_0);//���سɹ�����
				SendPkt(updateSuccessPacket);
			}else{
					byte[] updatefailurePacket = PackPkt(ConstParam.CONTACT_1);//����ʧ�ܱ���
					SendPkt(updatefailurePacket);
			}
		}

	}		

	public byte[] PackPkt(int i) {
		List<byte[]> packet = new ArrayList<byte[]>();
		byte[] byte1 = "ZNAF".getBytes();
		byte[] byteChar = new byte[4];
		
		//
		int sendseq;
		int ackseq;
		
		sendseq = revPacket.getAppNode().getSntPktId(); // ��ȡ���ͱ���id
		ackseq = revPacket.getSeq();// ACK��ֵ���յ������ĵ���ţ����������
		
		byteChar[0] = ConstParam.CMD_10;//cmd = 10
		
		if(revPacket.getType() == ConstParam.TYPE_1){
			byteChar[1] = ConstParam.TYPE_2; // type=2
		}
		if(revPacket.getType() == ConstParam.TYPE_3){
			byteChar[1] = ConstParam.TYPE_4; // type=4
		}
		if(revPacket.getType() == ConstParam.TYPE_5){
			byteChar[1] = ConstParam.TYPE_6; // type=6
		}
		if(revPacket.getType() == ConstParam.TYPE_9){
			byteChar[1] = ConstParam.TYPE_10; // type=10
		}
		byteChar[2] = ConstParam.OPT_8; // opt=8 ������
		byteChar[3] = ConstParam.SORT_0; // sort=0 app
		
		String packetBody = null; // ����������
		switch(i){
		case 1: //ret = 0    
			if(revPacket.getType() == ConstParam.TYPE_1){
				packetBody = "{'DATA':{'RET':'0','INFO':'" + contactDate +"'}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_3){
				packetBody = "{'DATA':{'RET':'0','INFO':'" + deleteDate +"'}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_7){
				packetBody = "{'DATA':{'RET':'0','INFO':''}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_9){
				packetBody = "{'DATA':{'RET':'0','INFO':''}}";
			}
			break;
		case 2://ret = 1 
			if(revPacket.getType() == ConstParam.TYPE_1){
				packetBody = "{'DATA':{'RET':'1','INFO':'" + contactDate +"'}}";
			}
			break;
		case 3://ret = -1 
			if(revPacket.getType() == ConstParam.TYPE_1){
				if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'There is no this cuser'}}";
				}
				if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'This user refused to add anyone'}}";
				}
				if (errorPktState == ConstParam.SENT_PKT_TYPE_2) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'The other party refuses to add'}}"; //�Է��ܾ����
				}
			}
			if(revPacket.getType() == ConstParam.TYPE_3){
				if (errorPktState == ConstParam.ERROR_PKT_STATE_0){
					packetBody = "{'DATA':{'RET':'-1','INFO':'There is no this cuser'}}"; // ��������
				}
				if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'pass is wrong'}}"; // ��������
				}
			}
			if(revPacket.getType() == ConstParam.TYPE_5){
				packetBody = "{'DATA':{'RET':'-1','INFO':'û����ϵ��'}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_7){
				packetBody = "{'DATA':{'RET':'-1','INFO':''}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_9){
				packetBody = "{'DATA':{'RET':'-1','INFO':'���ʧ��'}}";//�е����� 
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
		byte[] packets = byteArrayProc.sysCopy(packet); // �����byte[]ƴ��
		return packets;
		
	}

	public void SendPkt(byte[] sendPacket) {
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


