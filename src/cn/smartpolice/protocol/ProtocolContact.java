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
 * 联系人管理协议
 */
public class ProtocolContact extends ProtocolBase{
		String user;//申请者账号
		String cuser;//联系人账号
		String allas;//联系人别名
		String group; //组名
		String message;//留言
		String info;
		String contact;//关联的人
		String pass;
		byte[] from;//没用到 不知道怎么用
		byte[] kind;//
		int errorPktState;// 标记没成功添加的报文

		Date contactDate;//添加时间
		Date deleteDate;//脱离时间
		
	void ParsePktProto(PacketInfo packetInfo) {
		System.out.print("Contact报文解析");
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

			//添加联系人请求
			if(revPacket.getType() == ConstParam.TYPE_1){
				
				//查询联系人表，是否存在
					ContactDao contactDao = new ContactDao();
					ContactInf contactInf = contactDao.findContactByuser(user, cuser);
					
				if(contactInf == null){
					// 通过查询用户信息表
					UserDao userDao = new UserDao();
					UserInf cuserInf = userDao.findAppuserByName(cuser);//联系用户
					
					if(cuserInf == null){
						errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文（用户信息表中不存在该cuser）
						byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
						SendPkt(errorPacket);
					}else{
						//获取set，判断set&8
						if(Integer.parseInt(cuserInf.getSet())==8){
							errorPktState = ConstParam.SENT_PKT_TYPE_1; // 标记发送第一种错误报文,对方禁止添加联系人
							byte[] errorPacket = PackPkt(ConstParam.SENT_PKT_TYPE_1);
							SendPkt(errorPacket);
						}else{
							UserDao userdao = new UserDao();
							UserInf userInf = userdao.findAppuserByName(user);//主用户
							
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
							
							
							//查询cuser是否在线，有节点且state = 2
							if(cuserNode != null && Integer.parseInt(cuserInf.getState()) == 2){
								if(Integer.parseInt(userInf.getSet())==2){
									errorPktState = ConstParam.SENT_PKT_TYPE_2;
									byte[] waitPkt = PackPkt(ConstParam.SENT_PKT_TYPE_2);//等待确认
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
					byte[] successPacket = PackPkt(ConstParam.CONTACT_1);//账号已关联过
					SendPkt(successPacket);
				}
		}
			
		//脱离联系请求
		if(revPacket.getType() == ConstParam.TYPE_3){
			
				//查询是否是联系人
				ContactDao contactDao = new ContactDao();
				ContactInf contactInf = contactDao.findContactByuser(user,cuser);
				
				if(contactInf == null){
					errorPktState = ConstParam.ERROR_PKT_STATE_0; // 标记发送第一种错误报文（联系人信息表中不存在该cuser）
					byte[] errorPacket = PackPkt(ConstParam.CONTACT_2);
					SendPkt(errorPacket);
				}else{
					
					UserDao userdao = new UserDao();
					UserInf userInf = userdao.findAppuserByName(user);//主用户
					
					if(pass == userInf.getPassword()){
						//验证密码是否正确
						contactDao.deleteContactInf(contactInf);//删除这个记录
						byte[] deleteSuccessPacket = PackPkt(ConstParam.CONTACT_0);
						SendPkt(deleteSuccessPacket);
						deleteDate = new Date();
					}else{
						byte[] errorPacket = PackPkt(ConstParam.ERROR_PKT_STATE_1);//密码不正确
						SendPkt(errorPacket);
					}
				}
		}	
				
		//获取联系人请求
		if(revPacket.getType() == ConstParam.TYPE_5){
			int n;//联系人个数
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
					System.out.println();//换一行
				}
			}else{
				errorPktState = ConstParam.SENT_PKT_TYPE_1; //没有联系人
				byte[] errorPacket = PackPkt(ConstParam.ERROR_PKT_STATE_1);//密码不正确
				SendPkt(errorPacket);
			}
		}

		//联系人通告请求
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

		//变更联系人
		if(revPacket.getType() == ConstParam.TYPE_9){
			
			//查询联系人表
			ContactDao contactDao = new ContactDao();
			ContactInf contactInf = contactDao.findContactByuser(user, cuser);
			
			ByteArrayProc byteArrayProc = new ByteArrayProc();
			
			//变更别名
			if(kind == byteArrayProc.int2bytes(1)){
				contactDao.updateContactInf(contactInf, 1);	
				byte[] updatesuccessPacket = PackPkt(ConstParam.CONTACT_0);//返回成功报文
				SendPkt(updatesuccessPacket);
			}else{
					byte[] updatefailurePacket = PackPkt(ConstParam.CONTACT_1);//返回失败报文
					SendPkt(updatefailurePacket);
			}
			//变更组名
			if(kind == byteArrayProc.int2bytes(1)){
				contactDao.updateContactInf(contactInf, 2);
				byte[] updateSuccessPacket = PackPkt(ConstParam.CONTACT_0);//返回成功报文
				SendPkt(updateSuccessPacket);
			}else{
					byte[] updatefailurePacket = PackPkt(ConstParam.CONTACT_1);//返回失败报文
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
		
		sendseq = revPacket.getAppNode().getSntPktId(); // 获取发送报文id
		ackseq = revPacket.getSeq();// ACK的值是收到请求报文的序号，具有相关性
		
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
		byteChar[2] = ConstParam.OPT_8; // opt=8 请求报文
		byteChar[3] = ConstParam.SORT_0; // sort=0 app
		
		String packetBody = null; // 报文数据域
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
					packetBody = "{'DATA':{'RET':'-1','INFO':'The other party refuses to add'}}"; //对方拒绝添加
				}
			}
			if(revPacket.getType() == ConstParam.TYPE_3){
				if (errorPktState == ConstParam.ERROR_PKT_STATE_0){
					packetBody = "{'DATA':{'RET':'-1','INFO':'There is no this cuser'}}"; // 报文内容
				}
				if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
					packetBody = "{'DATA':{'RET':'-1','INFO':'pass is wrong'}}"; // 报文内容
				}
			}
			if(revPacket.getType() == ConstParam.TYPE_5){
				packetBody = "{'DATA':{'RET':'-1','INFO':'没有联系人'}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_7){
				packetBody = "{'DATA':{'RET':'-1','INFO':''}}";
			}
			if(revPacket.getType() == ConstParam.TYPE_9){
				packetBody = "{'DATA':{'RET':'-1','INFO':'变更失败'}}";//有点问题 
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


