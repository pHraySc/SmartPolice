package cn.smartpolice.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import cn.smartpolice.workbean.SysCfgInfo;
import cn.smartpolice.workbean.SysInfo;
/**
 * ��ʼ��mina���
 * @author ����
 *
 */
public class MinaServer {

	public static void InitMinaServer() {
		//����һ����������server��Socket����NIO
		NioDatagramAcceptor acceptor = new NioDatagramAcceptor();  
        acceptor.setHandler(new NetHandler());  
  
        /*Executor threadPool = Executors.newCachedThreadPool();  */
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();  
        //û�б��Ĵ����10�������ӿ���  session.close()
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        
        /*chain.addLast("logger", new LoggingFilter());*/
        /*acceptor.getSessionConfig().setIdleTime(arg0, arg1);*/
        /*chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));*/  
        /*chain.addLast("threadPool", new ExecutorFilter(threadPool));*/
		//�󶨷������˿�
		int bindPort = SysInfo.getSysCfgInfo().getPort();
		/*String addr = "192.168.1.109";*/
		//�󶨶˿ڣ�����������
		try {
			acceptor.unbind();
			acceptor.bind(new InetSocketAddress(bindPort));
		} catch (IOException e) {
			System.out.println("Mina Server start for error!"+bindPort);
			e.printStackTrace();
		}
		System.out.println("Mina Server run done!!! on port:"+bindPort); 
		
		/*try {
			Thread.sleep(6);
			acceptor.unbind();
			acceptor.getFilterChain().clear();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}*/
	}
}
