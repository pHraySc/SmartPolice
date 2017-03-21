package cn.smartpolice.net;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class NetServerListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		//�ͷŶ�ʱ��
		//�ͷ�mina����ͨ��
		System.out.println("��ʼ����Ϣ����");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/*System.out.println("����ͨ�ŷ�������");*/
		
		ReadSysCfgInfo readSysCfgInfo = new ReadSysCfgInfo();
		//��ȡweb.xml�����õ�SysCfgInfo.xml��λ��
		String sysCfgInfoLocation = arg0.getServletContext().getInitParameter("SysCfgInfoLocation");
		String path = arg0.getServletContext().getRealPath(sysCfgInfoLocation);
		readSysCfgInfo.loadSysCfgInfo(path);  //��ȡ�����ļ�����
		
		//ÿ��ִ��һ�ζ�ʱ���߳�
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(
				new ProtocolTimer(),
				0,
				1000*10, //10��
				TimeUnit.MILLISECONDS);
		
		//��ʼ��mina���
		/*MinaServer minaServer = new MinaServer();*/
		MinaServer.InitMinaServer();
	}

}
