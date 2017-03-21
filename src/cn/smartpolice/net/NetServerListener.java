package cn.smartpolice.net;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class NetServerListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		//释放定时器
		//释放mina网络通信
		System.out.println("初始化信息销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/*System.out.println("网络通信服务启动");*/
		
		ReadSysCfgInfo readSysCfgInfo = new ReadSysCfgInfo();
		//获取web.xml中配置的SysCfgInfo.xml的位置
		String sysCfgInfoLocation = arg0.getServletContext().getInitParameter("SysCfgInfoLocation");
		String path = arg0.getServletContext().getRealPath(sysCfgInfoLocation);
		readSysCfgInfo.loadSysCfgInfo(path);  //读取配置文件内容
		
		//每秒执行一次定时器线程
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(
				new ProtocolTimer(),
				0,
				1000*10, //10秒
				TimeUnit.MILLISECONDS);
		
		//初始化mina框架
		/*MinaServer minaServer = new MinaServer();*/
		MinaServer.InitMinaServer();
	}

}
