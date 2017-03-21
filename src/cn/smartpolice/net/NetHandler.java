package cn.smartpolice.net;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.SocketSessionConfig;

import cn.smartpolice.protocol.ProtocolProc;
import cn.smartpolice.protocol.ProtocolProc;

public class NetHandler extends IoHandlerAdapter{

	ProtocolProc ProtocolProc = new ProtocolProc();
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		SocketAddress remoteAddress = session.getRemoteAddress(); //��ȡԶ�̿ͻ��˵�ַ��Ϣ
		Date date = new Date();
		System.out.println(remoteAddress+"���ӽ���������:"+date.toString());
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Session Opened -->");
		SocketAddress remoteAddress = session.getRemoteAddress(); //��ȡԶ�̿ͻ��˵�ַ��Ϣ
		System.out.println(remoteAddress);
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("���ӿ���");
		session.close(true);
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println("...>Զ���������ӱ��ر�ʱ��:"+date.toString());
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("--> ��Ϣ���� ...");
		/*String msg = message.toString();
		System.out.println("ԭʼ���ݣ�"+msg); */
		IoBuffer ioBuffer = (IoBuffer) message; //��IoBuffer��ȡ�������Ϣ�ֽ�
		
		byte[] b = new byte [ioBuffer.limit()];
		ioBuffer.get(b);
		
		ProtocolProc.RecvPktProc(session,b);
		
		System.out.println("----�ȴ���Ϣ����----");
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("��Ϣ�Ѿ����͸��ͻ���");  
		if(message instanceof IoBuffer){
			IoBuffer ioBuffer = (IoBuffer)message;
			byte[] b= new byte [ioBuffer.limit()];
			ioBuffer.get(b);
			StringBuffer buffer = new StringBuffer();
			for(int i=0;i<b.length;i++){
				 buffer.append((char) b [i]);
			}
			System.out.println("������ɣ�"+buffer.toString()); 
		}
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("--���������׳��쳣--:"+cause);
		System.out.println("�ͻ���"+session.getRemoteAddress()+"�ر�������");
		
	}
}
