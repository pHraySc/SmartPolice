package cn.smartpolice.test;



import java.util.ArrayList;
import java.util.List;

import cn.smartpolice.net.ReadSysCfgInfo;
import cn.smartpolice.tools.JsonAnalysis;
import cn.smartpolice.workbean.SysCfgInfo;
import javassist.compiler.ast.NewExpr;

public class testXML {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				List<byte[]> packet =new ArrayList<byte[]>();
				byte[] byteChar=new byte[4];
				byteChar[0]=1;
				byteChar[1]=2;
				byteChar[2]=8;
				byteChar[3]=3;
				int sid = 1;
				int sendseq = 108;
				int ackseq = 0;
				byte[] byte1 =  "ZNAF".getBytes();
				packet.add(byte1);
				packet.add(byteChar);
				
				packet.add(int2bytes(sid));
				packet.add(int2bytes(sendseq));
				packet.add(int2bytes(ackseq));
				String packetBody = "{'DATA':{'RET':'0','INFO':'密码错误'}}"; //报文内容
				String pB = new JsonAnalysis().getJsonByObject(packetBody);
				byte[] pBbyte = pB.getBytes();
				packet.add(pBbyte);
				byte[] packets = sysCopy(packet); //将多个byte[]拼接
		byte[] newByte = sysCopy(packet);
		System.out.println(newByte.length);
		for (int i = 0; i < newByte.length; i++) {
			   System.out.print(newByte[i]+" ");
			  }
		
	
				
	}

		
		//int 转换为byte
		public static byte[] int2bytes(int i) {  
	        byte[] b = new byte[4]; 
	        b[0] = (byte) (0xff&i);  
	        b[1] = (byte) ((0xff00&i) >> 8);  
	        b[2] = (byte) ((0xff0000&i) >> 16);  
	        b[3] = (byte) ((0xff000000&i) >> 24);  
	        return b;
		} 
	
	public static byte[] sysCopy(List<byte[]> srcArrays) {
		int len = 0;
		for (byte[] srcArray : srcArrays) {
			len += srcArray.length;
		}
		byte[] destArray = new byte[len];
		int destLen = 0;
		for (byte[] srcArray : srcArrays) {
			System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
			destLen += srcArray.length;
		}
		return destArray;
	}
}
