package cn.smartpolice.protocol;

import java.util.ArrayList;
import java.util.List;



import org.apache.mina.core.buffer.IoBuffer;

import cn.smartpolice.tools.ByteArrayProc;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.PacketInfo;

/*cmd=11*/
public  class ProtocoUpgrade extends ProtocolBase {

	    String ver;//最大版本号
	    char  md5;//Md5摘要信息
	    byte size; //软件大小，字节
	    char  url;//软件存放地址
		int errorPktState;// 标记返回错误报文类型
	public void ParsePktProto(PacketInfo packetInfo){
		
		System.out.println("upgrade报文解析");
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
			//获得SERIAL,VERSION等选项信息
			Upgrade  SERIAL =  new  Upgrade();
			Upgrade   VERSION = new  Upgrade();
			SERIAL.getSerial();
			VERSION.getVersion();
			//根据SERIAL查询最大版本号ver
			SerialDao  serialDao = new SerialDao();
			Softlnf  verlnf =  serialDao.findMaxVerBySERIAL(ver);
			System.out.println(verlnf );
			//比较ver是否大于VERSION(利用compareTo())
			int result= ver.compareTo(VERSION.getVersion());
				
			if(result > 0){
					//根据ver查MD5，size,url
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
			// 当报文来自appuser或者dev是的不同处理
			if (revPacket.getSort() == ConstParam.SORT_0) {
				sendseq = revPacket.getAppNode().getSntPktId(); // 获取发送报文id
				ackseq = revPacket.getSeq(); // ACK的值是收到请求报文的序号，具有相关性
			} else {
				sendseq = revPacket.getDevNode().getSntPktId(); // 获取发送报文id
				ackseq = revPacket.getSeq();
			}
			

			byteChar[0] = 11;//cmd=11
			if (revPacket.getType() == ConstParam.TYPE_1) {
				byteChar[1] = ConstParam.TYPE_2; // type=2
				String packetBody = null; // 报文数据域
				switch (i) {
				case 1: // ret=-1请求失败
					System.out.println("case1");
					if(revPacket.getType() == ConstParam.TYPE_1){
						if (errorPktState == ConstParam.ERROR_PKT_STATE_0) {
							packetBody = "{'DATA':{'RET':'-1','INFO':'错误代码'}}"; // 报文内容
						}
						if (errorPktState == ConstParam.ERROR_PKT_STATE_1) {
							packetBody = "{'DATA':{'RET':'-1','INFO':'错误信息'}}"; // 报文内容
						}
						if (errorPktState == ConstParam.ERROR_PKT_STATE_2) {
							packetBody = "{'DATA':{'RET':'-1','INFO':'当前已是最新版本'}}"; // 报文内容
						}
					}
					
		
				
				case 2: // ret=0已经是最新版本
					System.out.println("case2");
					packetBody = "{'DATA':{'RET':'1','INFO':'" + ver +size+ "'}}"; // 报文内容
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
			return byteChar;
		}

			public void SendPkt(byte[] sendPacket) {
				// TODO Auto-generated method stub
				revPacket.getIoSession().write(IoBuffer.wrap(sendPacket));// 发送报文
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
	   
		

        

    