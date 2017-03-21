package cn.smartpolice.protocol;

import java.util.ArrayList;
import java.util.List;



import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.PacketInfo;

/*cmd=11*/
public  class ProtocoUpgrade extends ProtocolBase {

	    String ver;//���汾��
	    char  md5;//Md5ժҪ��Ϣ
	    byte size; //�����С���ֽ�
	    char  url;//�����ŵ�ַ
		int errorPktState;// ��Ƿ��ش���������
	public void ParsePktProto(PacketInfo packetInfo){
		
		System.out.println("upgrade���Ľ���");
		super.revPacket = packetInfo;
		StringBuffer  stringBuffer = new StringBuffer();
		for(int i = revPacket.getDatapos();i < revPacket.getMessage().length;i++){
			stringBuffer.append((char)revPacket.getMessage()[i]);
		}
		String datas = stringBuffer.toString();
		JsonAnalysis jsonAnalysis = new JsonAnalysis();
		String data = jsonAnalysis.getValue(datas, "DATA");
		
		if (revPacket.getType() == ConstParam.TYPE_1){
	    String serial = jsonAnalysis.getValue(data, "SERIAL");
		String version = jsonAnalysis.getValue(data, "VERSION");
		this.ExecProto();
		}
	}
	    public void ExecProto(){
	    this.ShowPacket(revPacket);
		}
		private void ShowPacket(PacketInfo revPacket) {
			// TODO Auto-generated method stub
			//���SERIAL,VERSION��ѡ����Ϣ
			Upgrade  SERIAL =  new  Upgrade();
			Upgrade   VERSION = new  Upgrade();
			SERIAL.getSerial();
			VERSION.getVersion();
			//����SERIAL��ѯ���汾��ver
			SerialDao  serialDao = new SerialDao();
			Softlnf  verlnf =  serialDao.findMaxVerBySERIAL(ver);
			System.out.println(verlnf );
			//�Ƚ�ver�Ƿ����VERSION(����compareTo())
			int result= ver.compareTo(VERSION.getVersion());
				
			if(result > 0){
					//����ver��MD5��size,url
			Softlnf  md5lnf = serialDao.findMd5ByVer(md5);
			Softlnf  sizelnf = serialDao.findSizeByVer(size);
			Softlnf  urllnf = serialDao.findUrlByVer(url);
			System.out.println( md5lnf );
			System.out.println(sizelnf);
			System.out.println(  urllnf);
			}else{
				System.out.println(	VERSION.getVersion() );
			}
			
			
		}
		public byte[] PackPkt(int i){
			List<byte[]> packet = new ArrayList<byte[]>();
			byte[] byte1 = "ZNAF".getBytes();
			byte[] byteChar = new byte[4];
			
			int sendseq = revPacket.getSeq();;
			int ackseq = revPacket.getSeq();
			// ����������appuser����dev�ǵĲ�ͬ����
			if (revPacket.getSort() == ConstParam.SORT_0) {
				sendseq = revPacket.getAppNode().getSntPktId(); // ��ȡ���ͱ���id
				ackseq = revPacket.getSeq(); // ACK��ֵ���յ������ĵ���ţ����������
			} else {
				sendseq = revPacket.getDevNode().getSntPktId(); // ��ȡ���ͱ���id
				ackseq = revPacket.getSeq();
			}
			

			byteChar[0] = 11;//cmd=11
			if (revPacket.getType() == ConstParam.TYPE_1) {
				byteChar[1] = ConstParam.TYPE_2; // type=2
				String packetBody = null; // ����������
				switch (i) {
				case 1: // ret=-1����ʧ��
					System.out.println("case1");
					if(revPacket.getType() == ConstParam.TYPE_1){
						if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
							packetBody = "{'DATA':{'RET':'-1','INFO':'�������'}}"; // ��������
						}
						if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
							packetBody = "{'DATA':{'RET':'-1','INFO':'������Ϣ'}}"; // ��������
						}
						if (errorPktState == ConstParam.ERROR_PKT_STATE_2) {
							packetBody = "{'DATA':{'RET':'-1','INFO':'��ǰ�������°汾'}}"; // ��������
						}
					}
					
		
				
				case 2: // ret=0�Ѿ������°汾
					System.out.println("case2");
					packetBody = "{'DATA':{'RET':'1','INFO':'" + ver +size+ "'}}"; // ��������
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
			return byteChar;
		}

			public void SendPkt(byte[] sendPacket) {
				// TODO Auto-generated method stub
				revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));// ���ͱ���
			}
			public void ShowPack(PacketInfo packetInfo) {
				System.out.print("cmd=" + packetInfo.getCmd());
				System.out.print("type=" + packetInfo.getType());
				System.out.print("opt=" + packetInfo.getOpt());
				System.out.print("sort=" + packetInfo.getSort());
				System.out.print("sid=" + packetInfo.getSid());
				System.out.print("seq=" + packetInfo.getSeq());
				System.out.println("ack=" + packetInfo.getAck());
			}

		

			
		}
	   
		

        

    